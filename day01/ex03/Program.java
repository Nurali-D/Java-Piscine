package ex03;

import java.util.UUID;

public class Program {

	public static void main(String[] args) {
        User user1 = new User(0,100, "John");
        User user2 = new User(0,200, "Nick");
        User user3 = new User(0,300, "Mike");

        TransactionsList list = new TransactionsLinkedList();

        Transaction transaction1 = new Transaction(user1, user2, 50, 
			Category.INCOME, UUID.randomUUID());
        Transaction transaction2 = new Transaction(user2, user3, -20, 
			Category.OUTCOME, UUID.randomUUID());
        Transaction transaction3 = new Transaction(user3, user1, 30, 
			Category.INCOME, UUID.randomUUID());
        Transaction transaction4 = new Transaction(user3, user2, -40, 
			Category.OUTCOME, UUID.randomUUID());

        list.addTransaction(transaction1);
        list.addTransaction(transaction2);
        list.addTransaction(transaction3);
        list.addTransaction(transaction4);

        user1.setList(list);
        user2.setList(list);
        user3.setList(list);

        System.out.println("-------------------");
        System.out.println("Transactions");
        for (Transaction t : list.toArray()) {
            System.out.println(t);
        }

        System.out.println("-------------------");
        System.out.println("User list");
        for (Transaction t : user1.getList().toArray()) {
            System.out.println(t);
        }

        System.out.println("-------------------");
        System.out.println("delete");
        list.removeTransactionById(transaction2.getIdentifier());
        System.out.println("-------------------");
        System.out.println("after");
        for (Transaction t : list.toArray()) {
            System.out.println(t);
        }
        System.out.println("-------------------");
		System.out.println("Exception");
		list.removeTransactionById(UUID.randomUUID());
    }
		

}
