package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private Integer id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime timestamp;

    public Message(Integer id, User author, Chatroom chatroom, String text, LocalDateTime timestamp) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author.equals(message.author)
                && chatroom.equals(message.chatroom)
                && text.equals(message.text)
                && timestamp.equals(message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, timestamp);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", messageDateTime=" + timestamp +
                '}';
    }
}

