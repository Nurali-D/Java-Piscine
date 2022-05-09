package ex03;

public class User {

    private Integer identifier;
    private Integer balance;
    private String name;
    private TransactionsList list;

    public User() {}

    public User(Integer id, Integer balance, String name) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Invalid balance");
        }
        this.name = name;
        this.list = new TransactionsLinkedList();
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setIdentifier(Integer newId) {
        this.identifier = newId;
    }

    public void setBalance(Integer newBalance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public void setList(TransactionsList list) {
        this.list = list;
    }

    public void addToTransactionList(Transaction tr) {
        this.list.addTransaction(tr);
    }

    public TransactionsList getList() {
        return this.list;
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

