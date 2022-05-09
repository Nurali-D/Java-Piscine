package ex02;

public class UsersArrayList implements UsersList {

    private int capacity = DEFAULT_SIZE;
    private int size = 0;
    private User[] usersList = new User[DEFAULT_SIZE];

    @Override
    public void addUser(User newUser) throws NullPointerException {
        if (size == capacity) {
            resizeUsersList();
        }
        if (newUser != null) {
            usersList[size] = newUser;
            ++size;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; ++i) {
            if (usersList[i].getIdentifier().equals(id)) {
                return usersList[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < 0 || index >= size) {
            throw new UserNotFoundException();
        } else if (usersList[index] == null) {
            throw new UserNotFoundException();
        }
        return usersList[index];
    }

    @Override
    public int getSize() {
        return size;
    }

    private void resizeUsersList() {
        int newCapacity = capacity + capacity / 2;
        User[] newList = new User[newCapacity];
        for (int i = 0; i < capacity; ++i) {
            if (usersList[i] == null) {
                break;
            }
            newList[i] = usersList[i];
        }
        capacity = newCapacity;
        usersList = newList;
    }

}

