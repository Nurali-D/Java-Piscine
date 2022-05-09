package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {

        Optional<Message> optMsg;

        Connection connection = ds.getConnection();

        Statement statement = connection.createStatement();

        String request = "SELECT * FROM chat.messages WHERE id = " + id;
        ResultSet rs = statement.executeQuery(request);
        rs.next();

        User user = new User(1, "Ben", "abcdef", null, null);
        Chatroom chatroom = new Chatroom(1, "chatroom", null, null);
        optMsg = Optional.of(new Message(
                rs.getInt(1), user, chatroom,
                rs.getString("text"),
                LocalDateTime.of(2022, 9, 19, 12, 3)));

        return optMsg;
    }
}
