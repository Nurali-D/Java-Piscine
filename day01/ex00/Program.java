package ex00;

import java.util.UUID;

public class Program {
	public static void main(String[] args) {
		User John = new User(1234, 200, "John");
		User Mike = new User(1235, 300, "Mike");
		System.out.println(John);
		System.out.println(Mike);
		Transaction tr1 = new Transaction(John, Mike, 100,
				Category.INCOME, new UUID(11, 20));
		Transaction tr2 = new Transaction(Mike, John, -100,
				Category.OUTCOME, new UUID(22,30));
		System.out.println(tr1);
		System.out.println(tr2);
	}
}
