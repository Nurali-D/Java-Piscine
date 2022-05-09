package ex02;

public interface UsersList {

    Integer DEFAULT_SIZE = 10;

    void addUser(User newUser);
    User getUserById(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    int getSize();
}
