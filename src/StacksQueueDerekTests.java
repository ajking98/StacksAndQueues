import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author DEREK WEILER
 * @version 1.0
 */
public class StacksQueueDerekTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPushPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 26; i++) {
            stack.push(i);
        }
        assertEquals(26, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[26];
        for (int i = 0; i < 26; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, backingArray);

        for (int i = 25; i >= 0; i--) {
            assertEquals((Integer) i, stack.pop());
        }
        assertEquals(0, stack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLLStackPushPop() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());

        for (int i = 0; i < 26; i++) {
            stack.push(i);
        }
        assertEquals(26, stack.size());

        Object[] data = new Object[26];
        for (int i = 25; i >= 0; i--) {
            data[i] = stack.pop();
        }

        Object[] expected = new Object[26];
        for (int i = 0; i < 26; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, data);
    }

    @Test(timeout =  TIMEOUT)
    public void testArrayEnqueueDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        for (int i = 0; i < 26; i++) {
            queue.enqueue(i);
        }
        assertEquals(26, queue.size());

        for (int i = 0; i < 26; i++) {
            assertEquals((Integer) i, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLLEnqueueDequeue() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());

        for (int i = 0; i < 26; i++) {
            queue.enqueue(i);
        }
        assertEquals(26, queue.size());

        for (int i = 0; i < 26; i++) {
            assertEquals((Integer) i, queue.dequeue());
        }
        assertEquals(0, queue.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayPushNullData() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        stack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopEmpty() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        stack.pop();
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLLPushNullData() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());
        stack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLLPopEmpty() {
        stack = new LinkedStack<>();
        assertEquals(0, stack.size());
        stack.pop();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayEnqueueNullData() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());
        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayDequeueEmpty() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());
        queue.dequeue();
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLLEnqueueNullData() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());
        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLLDequeueEmpty() {
        queue = new LinkedQueue<>();
        assertEquals(0, queue.size());
        queue.dequeue();
    }

    @Test
    public void testArrayQueueWrapAround() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        for (int i = 0; i < 6; i++) {
            queue.enqueue(i);
        }
        assertEquals(6, queue.size());

        for (int i = 0; i < 3; i++) {
            assertEquals((Integer) i, queue.dequeue());
        }
        assertEquals(3, queue.size());

        for (int i = 6; i < 13; i++) {
            queue.enqueue(i);
        }
        assertEquals(10, queue.size());

        for (int i = 3; i < 10; i++) {
            assertEquals((Integer) i, queue.dequeue());
        }
        assertEquals(3, queue.size());

        for (int i = 13; i < 19; i++) {
            queue.enqueue(i);
        }
        assertEquals(9, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        Integer[] expected = new Integer[13];
        for (int i = 10; i < 19; i++) {
            expected[i % expected.length] = i;
        }
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], backingArray[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueRegrowShift() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        for (int i = 0; i < 13; i++) {
            queue.enqueue(i);
        }
        assertEquals(13, queue.size());

        for (int i = 0; i < 7; i++) {
            assertEquals((Integer) i, queue.dequeue());
        }
        assertEquals(6, queue.size());

        for (int i = 13; i < 19; i++) {
            queue.enqueue(i);
        }
        assertEquals(12, queue.size());

        for (int i = 19; i < 26; i++) {
            queue.enqueue(i);
        }
        assertEquals(19, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        Integer[] expected = new Integer[26];
        for (int i = 0; i < 19; i++) {
            expected[i] = i + 7;
        }
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], backingArray[i]);
        }

    }
}