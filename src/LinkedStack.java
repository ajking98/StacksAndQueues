/**
 * Your implementation of an array-backed queue.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        checkIfStackIsEmpty();

        LinkedNode nodeTORemove = head;

        head = head.getNext();

        size--;

        return (T) nodeTORemove.getData();

    }

    @Override
    public void push(T data) {
        checkForIllegalArgument(data);

        head = new LinkedNode<T>(data, head);

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
     * Returns the head of this stack.
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
     * if the stack is empty then throw a NoSuchElementException
     */
    private void checkIfStackIsEmpty() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     *
     * @param data the data to check if it is null
     */
    private void checkForIllegalArgument(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }
}