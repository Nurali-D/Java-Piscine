package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl("jdbc:postgresql://localhost:5432/chatdb");
        hds.setUsername("postgres");
        hds.setPassword("postgres");

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(hds);


        User author = new User(1, "Nick", "abcdef", new ArrayList(), new ArrayList());
        Chatroom room = new Chatroom(1, "Java", author, new ArrayList());

        Message msg = new Message(null, author, room, "new message", LocalDateTime.now());

        mr.save(msg);

        System.out.println(msg.getId());

    }
}