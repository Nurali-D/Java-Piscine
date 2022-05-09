package edu.school21.app;

import edu.school21.models.Book;
import edu.school21.models.User;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class Program {

    private static final String DELIMITER = "------------------";

    public static void main(String[] args) {
        OrmManagerTester omt = new OrmManagerTester();

        System.out.println(DELIMITER);
        omt.testSave();
        System.out.println(DELIMITER);
        omt.testFind();
        System.out.println(DELIMITER);
        omt.testUpdate();
        System.out.println(DELIMITER);
        omt.stopDataSource();
    }



}