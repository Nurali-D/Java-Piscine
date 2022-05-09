package edu.school21.main;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ClassReader {
    private static final String CLASSES_PACKAGE = "edu.school21.classes.";
    public static final String DELIMITER = "---------------------";
    private static final String JAVA_LANG = "java.lang.";
    private static final String PUBLIC_MODIFIER = "public ";
    private static final String PRIVATE_MODIFIER = "private ";
    private static final char PKG_SEPARATOR = '.';
    private static final char DIR_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".class";

    private List<Class<?>> classes;
    private Class<?> inputClass;
    private Object object;
    private int fieldsCount;
    private int methodsCount;

    public boolean showClasses() {
        String classesPackage = CLASSES_PACKAGE.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource(classesPackage);
        if (classesUrl == null) {
            System.err.println("Not found classes!");
            return false;
        }
        File classesDir = new File(classesUrl.getFile());
        if (!classesDir.isDirectory()) {
            System.err.println("Error with package!");
            return false;
        }

        List<String> classesNames = Arrays.stream(Objects.requireNonNull(classesDir
                        .listFiles(file -> !file.isDirectory() && file.getName().endsWith(CLASS_FILE_SUFFIX))))
                .map(file -> CLASSES_PACKAGE + file.getName().replace(CLASS_FILE_SUFFIX, ""))
                .collect(Collectors.toList());

        System.out.println("Classes:");
        classes = new ArrayList<>();
        for (String name : classesNames) {
            try {
                Class<?> aClass = Class.forName(name);
                classes.add(aClass);
                System.out.println("\t-" + aClass.getSimpleName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void readClass() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter class name\n->");
            if (scanner.hasNext()) {
                String name = scanner.next();
                inputClass = classes.stream()
                        .filter(c -> c.getSimpleName().equals(name)).findFirst().orElse(null);
                if (inputClass != null) {
                    return;
                } else {
                    System.err.println("Class not found!");
                    System.out.println("Enter class name from list above");
                }
            }
        }
    }

    public void showClassStructure() {
        fieldsCount = inputClass.getDeclaredFields().length;
        if (fieldsCount > 0) {
            System.out.println("Fields:");
            Arrays.stream(inputClass.getDeclaredFields()).forEach(f ->
                    System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName())
            );
        }

        methodsCount = inputClass.getDeclaredMethods().length;
        if (methodsCount > 0) {
            System.out.println("Methods:");
            Arrays.stream(inputClass.getDeclaredMethods())
                    .forEach(m -> System.out.println("\t" + m.toString()
                            .replace(PUBLIC_MODIFIER, "")
                            .replace(PRIVATE_MODIFIER, "")
                            .replace(JAVA_LANG, "")
                            .replace(CLASSES_PACKAGE, "")
                            .replace(inputClass.getSimpleName() + PKG_SEPARATOR, "")));
        }
    }

    public void createdObject() {
        System.out.println("Let`s create an object.");
        try {
            Scanner scanner = new Scanner(System.in);

            object = inputClass.newInstance();
            for (Field field : inputClass.getDeclaredFields()) {
                Object value = null;
                do {
                    System.out.print(field.getName() + "\n->");
                    value = convertValue(field.getType().getSimpleName(), scanner.nextLine());
                    field.setAccessible(true);
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } while (value == null);
            }
            System.out.println("Object created: " + object);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object convertValue(String typeName, String value) {
        try {
            switch (typeName) {
                case "int":
                case "Integer":
                    return (Integer.parseInt(value));
                case "long":
                case "Long":
                    return (Long.parseLong(value));
                case "boolean":
                case "Boolean":
                    return (Boolean.parseBoolean(value));
                case "float":
                case "Float":
                    return (Float.parseFloat(value));
                case "double":
                case "Double":
                    return (Double.parseDouble(value));
                case "short":
                case "Short":
                    return (Short.parseShort(value));
                case "String":
                    return (value);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Can't cast " + value + " to " + typeName);
            return null;
        }
    }

    public void modifyFiledObject() {
        if (fieldsCount == 0) {
            System.out.println("The object " + object.getClass().getSimpleName() + " has no fields for modify!");
        }
        System.out.print("Enter name of the field for changing:\n->");
        Scanner scanner = new Scanner(System.in);
        String fieldName = scanner.next();

        Field field = Arrays.stream(inputClass.getDeclaredFields())
                .filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        if (field == null) {
            System.err.println("Field not found!");
            return;
        }
        scanner.nextLine();

        Object value = null;
        do {
            System.out.print("Enter " + field.getType().getSimpleName() + " value \n->");
            value = convertValue(field.getType().getSimpleName(), scanner.nextLine());
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } while (value == null);

        System.out.println("Object updated:" + object);
    }

    public void callMethod() {
        if (methodsCount == 0) {
            System.out.println("The object " + object.getClass().getSimpleName() + " has no methods for call!");
            return;
        }

        System.out.print("Enter name of the method for call:\n->");
        Scanner scanner = new Scanner(System.in);
        String methodName = scanner.next();

        Method method = Arrays.stream(inputClass.getDeclaredMethods())
                .filter(m -> m.getName().equals(methodName)).findFirst().orElse(null);
        if (method == null) {
            System.err.println("Method not found!");
            return;
        }
        scanner.nextLine();

        Parameter[] parameters = method.getParameters();
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            String typeName = parameters[i].getType().getSimpleName();
            do {
                System.out.print("Enter " + typeName + " value \n->");
                arguments[i] = convertValue(typeName, scanner.nextLine());
            } while (arguments[i] == null);
        }

        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(object, arguments);
        } catch (IllegalArgumentException e) {
            System.out.println("The method was not called due to incorrect arguments!");
            return;
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        if (!(method.getReturnType().getSimpleName().equals("void"))) {
            System.out.println("Method returned:");
            System.out.println(result);
        }
    }

}
