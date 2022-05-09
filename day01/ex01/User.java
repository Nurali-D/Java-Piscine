package ex01;

public class User {

	private Integer identifier;
	private Integer balance;
	private String name;

	public User() {}

	public User(Integer id, Integer balance, String name) {
		this.identifier = UserIdsGenerator.getInstance().generateId();
		if (balance >= 0) {
			this.balance = balance;
		} else {
			System.out.println("Invalid balance");
		}
		this.name = name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setBalance(Integer newBalance) {
		if (balance >= 0) {
			this.balance = balance;
		}
	}

	public String getName() {
		return this.name;
	}

	public Integer getIdentifier() {
		return this.identifier;
	}

	public Integer getBalance() {
		return this.balance;
	}

	@Override
	public String toString() {
		return "User{" + "identifier=" + identifier
				+ ", name=" + name + ", balance=" + balance + "}";
	}
}
