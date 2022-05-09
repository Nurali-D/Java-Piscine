package ex03;

import java.util.UUID;

public interface TransactionsList {

    void addTransaction(Transaction tr);
    void removeTransactionById(UUID id);
    Transaction[] toArray();
}