package edu.school21.app;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;

public class OrmManager {
    private String packageName;
    private Connection connection;


    public OrmManager(String packageName, DataSource dataSource) {
        this.packageName = packageName;
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        String packagePath = packageName.replace('.', '/');
        URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(packagePath);
        if (packageUrl == null) { System.out.println("Not found classes!"); }
        File modelsDir = new File(packageUrl.getFile());
        try {
            PreparedStatement statement;
            for (File file : modelsDir.listFiles()) {
                String className = file.getName().replace(".class", "");
                Class<?> aClass = Class.forName(packageName + className);

                if (aClass.isAnnotationPresent(OrmEntity.class)) {
                    OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                    String sqlDropTable = "DROP TABLE " + entity.table() + " IF EXISTS;";
                    statement = connection.prepareStatement(sqlDropTable);
                    statement.execute();
                    System.out.println(sqlDropTable);
                    String sqlCreateTable = "CREATE TABLE " + entity.table() + "(\n";
                    for (Field field : aClass.getDeclaredFields()) {
                        if (field.isAnnotationPresent(OrmColumnId.class)) {
                            String typeName = field.getType().getSimpleName();
                            if (typeName.equals("Long")) {
                                sqlCreateTable += "\t" + field.getName() + " BIGINT PRIMARY KEY,\n";
                            } else {
                                throw new IllegalArgumentException(aClass.getName()
                                        + " PRIMARY KEY field \"" + field.getName()
                                        + "\" must be a Long type!");
                            }
                        } else if (field.isAnnotationPresent(OrmColumn.class)) {
                            OrmColumn column = field.getAnnotation(OrmColumn.class);
                            sqlCreateTable += "\t" + column.name() + " " +
                                    sqlType(field.getType().getSimpleName(), column.length(),
                                            aClass.getName() + "." + field.getName()) + ",\n";
                        }
                    }
                    sqlCreateTable += ");";
                    statement = connection.prepareStatement(sqlCreateTable);
                    statement.execute();
                    System.out.println(sqlCreateTable);
                }
            }
        } catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private String sqlType(String typeName, int length, String classWithField) {
        if (typeName.equals("Integer")) {
            return "INT";
        } else if (typeName.equals("Long")) {
            return "BIGINT";
        } else if (typeName.equals("String")) {
            return "VARCHAR(" + length + ")";
        } else if (typeName.equals("Double")) {
            return "DOUBLE";
        } else if (typeName.equals("Boolean")) {
            return "BOOLEAN";
        } else {
            throw new IllegalArgumentException(classWithField + " contains not valid type \"" + typeName+"\"!");
        }
    }

    public void save(Object object) {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            try {
                OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                String sqlSave = "INSERT INTO " + entity.table() + "(id, ";
                String sqlFields = "";
                String sqlValues = "";
                String id = "1, ";
                for (Field field : aClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        id = field.get(object).toString() + ", ";
                    } else if (field.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = field.getAnnotation(OrmColumn.class);
                        sqlFields += column.name() + ", ";
                        if (field.getType().getSimpleName().equals("String")) {
                            sqlValues += "'" + field.get(object) + "', ";
                        } else {
                            sqlValues += field.get(object) + ", ";
                        }
                    }
                }
                if (sqlFields.equals("") && sqlValues.equals("")) {
                    System.out.println("Not data for save!");
                    return;
                }
                sqlFields = sqlFields.substring(0, sqlFields.length() - 2);
                sqlValues = sqlValues.substring(0, sqlValues.length() - 2);
                sqlSave += sqlFields + ") VALUES (" + id + sqlValues + ");";
                PreparedStatement statement = connection.prepareStatement(sqlSave);
                statement.execute();
                System.out.println(sqlSave);
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("The id field must be an unique! The record was not saved!");
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            try {
                OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                String sqlFindById = "SELECT * FROM " + entity.table() + " WHERE id = " + id + ";";
                PreparedStatement statement = connection.prepareStatement(sqlFindById);
                statement.execute();
                System.out.println(sqlFindById);
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) {
                    T object = aClass.newInstance();
                    for (Field field : aClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(OrmColumnId.class)) {
                            field.set(object, resultSet.getLong("id"));
                        } else if (field.isAnnotationPresent(OrmColumn.class)) {
                            OrmColumn column = field.getAnnotation(OrmColumn.class);
                            field.set(object, resultSet.getObject(column.name()));
                        }
                    }
                    return object;
                }
                System.out.println("The record was not found!");
                return null;
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println("Not found constructor without parameters!");
                return null;
            }
        }
        System.out.println("The class is not associated with a database!");
        return null;
    }

    public void update(Object object) {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            try {
                OrmEntity entity = aClass.getAnnotation(OrmEntity.class);
                String sqlUpdate = "UPDATE " + entity.table() + " SET ";
                String id = "1";
                for (Field field : aClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        id = field.get(object).toString();
                    } else if (field.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn column = field.getAnnotation(OrmColumn.class);
                        sqlUpdate += column.name() + " = ";
                        if (field.getType().getSimpleName().equals("String")) {
                            sqlUpdate += "'" + field.get(object) + "', ";
                        } else {
                            sqlUpdate += field.get(object) + ", ";
                        }
                    }
                }
                sqlUpdate = sqlUpdate.substring(0, sqlUpdate.length() - 2);
                sqlUpdate += " WHERE id = " + id + ";";
                PreparedStatement statement = connection.prepareStatement(sqlUpdate);
                statement.execute();
                System.out.println(sqlUpdate);
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
