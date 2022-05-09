package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private TransactionNode start;
    private TransactionNode end;
    private int size;

    public TransactionsLinkedList() {
        start = new TransactionNode(null, null, null);
        end = new TransactionNode(null, null, null);
        start.setNext(end);
        end.setPrevious(start);
        this.size = 0;
    }

    @Override
    public void addTransaction(Transaction tr)
    {
        if (size == 0) {
            this.start.setData(tr);
            ++size;
        } else if (size == 1) {
            this.end.setData(tr);
            ++size;
        } else {
            TransactionNode newNode = new TransactionNode(tr, this.end, null);
            this.end.setNext(newNode);
            this.end = newNode;
            ++size;
        }
    }

    @Override
    public void removeTransactionById(UUID id)
    {
        TransactionNode tmp = this.start;

        for (int i = 0; i < size; ++i) {
            UUID tmpId = tmp.getData().getIdentifier();
            if (tmpId.equals(id)) {
                deleteNode(tmp);
                return;
            }
            tmp = tmp.getNext();
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray()
    {
        Transaction[] array = new Transaction[size];
        TransactionNode tmp = start;

        for (int i = 0; i < size; ++i) {
            array[i] = tmp.getData();
            tmp = tmp.getNext();
        }
        return array;
    }

    private void deleteNode(TransactionNode tn) {
        if (tn.getPrevious() == null && size > 2) {
            start = start.getNext();
            start.setPrevious(null);
            --size;
        } else if (tn.getNext() == null && size > 2) {
            end = end.getPrevious();
            end.setNext(null);
            --size;
        } else if (size <= 2) {
            tn.setData(null);
            --size;
        } else {
            TransactionNode next = tn.getNext();
            TransactionNode previous = tn.getPrevious();
            next.setPrevious(previous);
            previous.setNext(next);
            --size;
        }
    }
}

class TransactionNode {

    private Transaction data;
    private TransactionNode previous;
    private TransactionNode next;

    public TransactionNode(Transaction data,
            TransactionNode previous, TransactionNode next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public void setData(Transaction data) {
        this.data = data;
    }

    public void setPrevious(TransactionNode previous) {
        this.previous = previous;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public Transaction getData() {
        return this.data;
    }

    public TransactionNode getPrevious() {
        return this.previous;
    }

    public TransactionNode getNext() {
        return this.next;
    }
}
