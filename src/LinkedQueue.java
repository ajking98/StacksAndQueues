/**
 * Your implementation of an array-backed queue.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        checkIfStackIsEmpty();
        T front = getT();
        size--;
        return front;

    }

    @Override
    public void enqueue(T data) {
        checkForIllegalArgument(data);
        LinkedNode newNode = new LinkedNode(data, null);
        assignNodeToNewPosition(newNode);
        tail = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     *
     */
    private void checkIfStackIsEmpty() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     *
     * @param data
     */
    private void checkForIllegalArgument(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @return
     */
    private T getT() {
        T front = null;
        front = head.getData();
        head = head.getNext();
        if (head == null) tail = null;
        return front;
    }

    /**
     *
     * @param newNode
     */
    private void assignNodeToNewPosition(LinkedNode newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
    }
}