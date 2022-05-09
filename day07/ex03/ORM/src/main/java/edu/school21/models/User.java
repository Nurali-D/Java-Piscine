package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

@OrmEntity(table = "users")
public class User {
    @OrmColumnId
    private Long id;

    @OrmColumn(name = "first_name", length = 20)
    private String firstName;

    @OrmColumn(name = "last_name", length = 20)
    private String lastName;

    @OrmColumn(name = "age")
    private Integer age;

    public User() {
    }

    public User(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
