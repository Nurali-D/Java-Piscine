package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    ProductsRepository pr;
    EmbeddedDatabase eds;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "some product1", 2500),
            new Product(2, "some product2", 3500),
            new Product(3, "some product3", 4500),
            new Product(4, "some product4", 5500),
            new Product(5, "some product5", 6500)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2, "some product2", 3500);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "some new product3", 4000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6, "some product6", 7500);

    @BeforeEach
    void init() {
        eds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        pr = new ProductsRepositoryJdbcImpl(eds);
    }

    @Test
    void testFindAll() {

        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, pr.findAll());
    }

    @Test
    void testFindById() {

        Assertions.assertEquals(pr.findById(2).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void testUpdate() {
        pr.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(pr.findById(3).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void testSave() {
        pr.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(pr.findById(6).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    void testDelete() {
        pr.delete(1);
        Assertions.assertThrows(RuntimeException.class, () -> pr.findById(1));
    }

    @AfterEach
    void close() {
        eds.shutdown();
    }
}