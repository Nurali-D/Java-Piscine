package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private Integer id;
    private String login;
    private String password;
    List<Chatroom> createdChats;
    List<Chatroom> joinedChats;

    public User(Integer id, String login, String password,
                List<Chatroom> createdChats, List<Chatroom> joinedChats) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChats = createdChats;
        this.joinedChats = joinedChats;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCreatedChats(List<Chatroom> createdChats) {
        this.createdChats = createdChats;
    }

    public List<Chatroom> getCreatedChats() {
        return this.createdChats;
    }

    public void setJoinedChats(List<Chatroom> joinedChats) {
        this.joinedChats = joinedChats;
    }

    public List<Chatroom> getJoinedChats() {
        return this.joinedChats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login)
                && password.equals(user.password)
                && createdChats.equals(user.createdChats)
                && joinedChats.equals(user.joinedChats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdChats, joinedChats);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdChats=" + createdChats +
                ", joinedChats=" + joinedChats +
                '}';
    }
}