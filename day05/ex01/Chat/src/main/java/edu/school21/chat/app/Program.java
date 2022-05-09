package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl("jdbc:postgresql://localhost:5432/chatdb");
        hds.setUsername("postgres");
        hds.setPassword("postgres");


        MessagesRepository mr = new MessagesRepositoryJdbcImpl(hds);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        System.out.println(mr.findById(scanner.nextLong()).get());
    }
}