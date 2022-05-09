package edu.school21.chat.repositories;

import edu.school21.chat.exceptoins.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String sqlRequest = "INSERT into chat.messages (author, chatroom, text, timestamp)" +
                " VALUES (" +
                message.getAuthor().getId() + ", " +
                message.getChatroom().getId() + ", " +
                "'" + message.getText() + "'" + ", " +
                "'" + message.getTimestamp() + "'" +
                ");";

        try(Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest,
                    Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();

            ResultSet rk = statement.getGeneratedKeys();
            rk.next();
            message.setId(rk.getInt(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
        return true;
    }

    @Override
    public boolean update(Message message) throws SQLException {

        String sqlRequest = "UPDATE chat.messages " +
                "SET author = " + message.getAuthor().getId() + ", " +
                "chatroom = " + message.getChatroom().getId() + ", " +
                "text = " + "'" + message.getText() + "'" + ", " +
                "timestamp = " + "'" + message.getTimestamp() + "' " +
                "WHERE id = " + message.getId() + ";";

        try(Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.execute();
        }
        return true;
    }
}