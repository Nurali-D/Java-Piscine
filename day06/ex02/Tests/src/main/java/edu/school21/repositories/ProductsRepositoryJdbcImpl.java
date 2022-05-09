package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("select * from product;");

            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Product product = null;
        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE id = " + id + ";");

            if (!rs.next()) {
                throw new RuntimeException("Object with specified id not found");
            }
            product = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {
        try (Connection connection = ds.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE product " + "SET name = ?, price = ? " + "WHERE id = ?;")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO product VALUES (?, ?, ?);")) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM product " + "WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
