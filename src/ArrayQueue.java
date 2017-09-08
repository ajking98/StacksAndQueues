

/**
 * Your implementation of an array-backed queue.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
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
        checkForNoSuchElement();

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
        checkForIllegalArgument(data);

        regrowCapacityIfFull();

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

        loopFromFrontToLength(newList);

        iterateFrontToBack(newList);

        assignVariablesWhenRegrowing(newList);
    }

    /**
     *
     * @return a new array
     */
    private T[] createNewArray() {
        return (T[]) (new Object[backingArray.length * 2]);
    }

    /**
     * Checks for no Such Element and if size equals zero
     */
    private void checkForNoSuchElement() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     *
     * @param data check if the data is equal to null
     */
    private void checkForIllegalArgument(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param newList create a new list and set it to backArray
     */
    private void loopFromFrontToLength(T[] newList) {
        for (int i = front; i < backingArray.length; i++) {
            newList[i - front] = backingArray[i];
        }
    }

    /**
     * if the array is full then expand the capacity
     */
    private void regrowCapacityIfFull() {
        if (size == backingArray.length) {
            expandCapacity();
        }
    }
    /**
     *
     * @param newList iterate from front to back if front if bigger than back
     */
    private void iterateFrontToBack(T[] newList) {
        if (front >= back) {
            for (int i = 0; i < back; i++) {
                newList[i + backingArray.length - front] = backingArray[i];
            }
        }
    }

    /**
     *
     * @param newList a new list as the parameter
     */
    private void assignVariablesWhenRegrowing(T[] newList) {
        backingArray = newList;
        front = 0;
        back = size;
    }
}