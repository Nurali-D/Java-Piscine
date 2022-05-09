package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        for (int i = 0; i < 20; ++i) {
            User user = new User(i*2, i + 10, Integer.toString(i));
            System.out.println(user);
            list.addUser(user);
        }
        System.out.println("===============================");
        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(list.getUserByIndex(i));
        }

        System.out.println("===============================");
        System.out.println(list.getSize());
        System.out.println(list.getUserByIndex(20));
    }
}
