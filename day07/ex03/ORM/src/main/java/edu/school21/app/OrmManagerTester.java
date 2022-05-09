package edu.school21.app;

import edu.school21.models.Book;
import edu.school21.models.User;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class OrmManagerTester {

    private static final String PACKAGE_NAME = "edu.school21.models.";
    private EmbeddedDatabase dataSourse;
    private OrmManager manager;

    public OrmManagerTester() {
        dataSourse = new EmbeddedDatabaseBuilder().build();
        manager = new OrmManager(PACKAGE_NAME, dataSourse);
        manager.init();
    }

    public void testSave(){
        System.out.println("--SAVE TEST--");
        User user = new User(1L, "Vasilii", "Utkin", 25);
        User user2 = new User(2L, "Sergei", "Sergeev", 30);
        manager.save(user);
        manager.save(user2);

        Book book = new Book(1L, "Deitel",
                "Programming in C", 2500.0, 2020, true);
        Book book2 = new Book(2L, "Lafore",
                "Algorithms and data structures", 3000.0, 2022, true);
        manager.save(book);
        manager.save(book2);
    }

    public void testFind() {
        System.out.println("--FIND TEST--");
        User user;
        if ((user = manager.findById(2L, User.class)) != null) {
            System.out.println(user);
        }
        Book book;
        if ((book = manager.findById(1L, Book.class)) != null) {
            System.out.println(book);
        }
    }

    public void testUpdate() {
        System.out.println("--UPDATE TEST--");
        User user;
        if ((user = manager.findById(1L, User.class)) != null) {
            System.out.println("Before update:" + user);
        }
        user = new User(1L, "Nick", "Cage", 40);
        manager.update(user);

        if ((user = manager.findById(1L, User.class)) != null) {
            System.out.println("After update:" + user);
        }

        Book book;
        if ((book = manager.findById(2L, Book.class)) != null) {
            System.out.println("Before update:" + book);
        }
        book = new Book(2L, "Horstman",
                "Java", 3000.0, 2021, true);
        manager.update(book);

        if ((book = manager.findById(2L, Book.class)) != null) {
            System.out.println("After update:" + book);
        }
    }

    public void stopDataSource() {
        dataSourse.shutdown();
    }
}
