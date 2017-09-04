import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed stack.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T poppedValue = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return poppedValue;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length) {
            expandCapacity();
        }
        backingArray[size] = data;
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
     * Returns the backing array of this stack.
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
        T[] newList = (T[]) (new Object[size * 2]);

        for (int i = 0; i < size; i++) {
            newList[i] = backingArray[i];
        }

        createBackingArray(newList);
    }

    /**
     * @param newList the newList to set to the old arrayList
     */
    private void createBackingArray(T[] newList) {
        backingArray = newList;
    }
}
