package ex03;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private Integer amount;
    private Category category;
    private Status status;

    public Transaction(User sender, User recipient, Integer amount,
                       Category category, UUID identifier) {
        if (!((category == Category.OUTCOME && amount < 0)
                || (category == category.INCOME && amount > 0))) {
            System.out.println("Invalid category and amount");
            return;
        }
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.category = category;
        this.identifier = identifier;
    }

    public void setIdentifier(UUID newId) {
        this.identifier = newId;
    }

    public void setSender(User newSender) {
        this.sender = newSender;
    }

    public void setRecipient(User newRecipient) {
        this.recipient = newRecipient;
    }

    public void setAmount(Integer newAmount) {
        this.amount = newAmount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UUID getIdentifier() {
        return this.identifier;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public User getSender() {
        return this.sender;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Category getCategory() {
        return this.category;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "Transaction{" + "sender=" + sender.getName()
                + ", recipient=" + recipient.getName()
                + ", amount=" + amount
                + ", category=" + category
                + ", identifier=" + identifier
                + ", status=" + status + "}";
    }
}

enum Category {

    OUTCOME("OUTCOME"),
    INCOME("INCOME");

    private String name;

    Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

enum Status {

    FAILED("FAILED"),
    SUCCESS("SUCCESS");

    private String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

