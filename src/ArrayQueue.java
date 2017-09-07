

/**
 * Your implementation of an array-backed queue.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        T removedObj = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return removedObj;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length) {
            expandCapacity();
        }
        backingArray[back] = data;
        back = (back + 1) % backingArray.length;
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
    /**
     * Creates a new array to store the contents of the list with twice
     * the capacity of the old one.
     */
    private void expandCapacity() {
        T[] newList = createNewArray();
        for (int i = front; i < backingArray.length; i++) {
            newList[i - front] = backingArray[i];
        }
        if (front >= back) {
            for (int i = 0; i < back; i++) {
                newList[i + backingArray.length - front] = backingArray[i];
            }
        }
        backingArray = newList;
        front = 0;
        back = size;
    }

    /**
     *
     * @return
     */
    private T[] createNewArray() {
        return (T[]) (new Object[backingArray.length * 2]);
    }
}