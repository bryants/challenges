enum Type {

    FULL("Queue is full."),
    EMPTY("Queue is empty.");

    private final String message;

    private Type(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

class BoundedQueueException extends Exception {

    private final Type type;

    public BoundedQueueException(Type type) {
        super(type.getMessage());
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public String toString() {
        return "BoundedQueueException{" +
            "type=" + this.type +
        '}';
    }

}

public class BoundedIntQueue {

    /*
     * CANDIDATE:  Place your implementation below.  Do not modify code above.
     */
    private int enIndex;
    private int deIndex;
    private int counter;
    private int[] data;
    private BoundedQueueException bqeFull = new BoundedQueueException(Type.FULL);
    private BoundedQueueException bqeEmpty = new BoundedQueueException(Type.EMPTY);

    public BoundedIntQueue(int size) {
        data = new int[size];
        enIndex = 0;
        deIndex = 0;
    }

    public void enqueue(int newInt) throws BoundedQueueException {
        if (counter == data.length) { throw bqeFull; }

        data[enIndex] = newInt;
        if (++enIndex == data.length) { enIndex = 0; }
        else { /*do nothing*/ }
        ++counter;
    }

    public int dequeue() throws BoundedQueueException {
        if (counter == 0) { throw bqeEmpty; }

        int item = data[deIndex];
        data[deIndex] = 0;
        if (++deIndex == data.length) { deIndex = 0; }
        else { /*do nothing*/ }
        --counter;
        return item;
    }
	
	
    /*
     * End candidate implementation.  Do not modify code below here.
     */

    public static void main(String args[]) {
        int queueSize = 30;
        BoundedIntQueue queue = new BoundedIntQueue(queueSize);
        int x;
        try {
            x = queue.dequeue();
            fail("Empty Dequeue Test #1 Failed.");
            return;
        } catch (BoundedQueueException e) {
            switch (e.getType()) {
                case EMPTY:
                    System.out.println("Empty Dequeue Test #1 Passed.");
                    break;
                case FULL:
                    fail("Empty Dequeue Test #1 Failed; unexpected exception.", e);
                    return;
            }
        }

        try {
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            int y = queue.dequeue();
            y = queue.dequeue();
            queue.enqueue(5);
            y = queue.dequeue();
            if(y != 4) {
                fail("Alternate Test Failure.");
                return;
            }
            System.out.println("Alternate Test Passed.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception in Alternate Test.", e);
            return;
        }

        try {
            while(true) {
                queue.dequeue();
            }
        } catch (BoundedQueueException e) {
            if(e.getType() != Type.EMPTY) {
                fail("Unexpected Exception while emptying queue after Alternate Test.", e);
                return;
            }
        }

        int[] values = new int[queueSize];
        try {
            x = 7;
            for(int i = 0; i < queueSize; i++) {
                queue.enqueue(x);
                values[i] = x++;
            }
            System.out.println("Load #1 Complete.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while enqueueing " + queueSize + " items into empty queue during Load #1.", e);
            return;
        }

        try {
            queue.enqueue(x);
            fail("Full Enqueue Test #1 Failed.");
            return;
        } catch (BoundedQueueException e) {
            switch (e.getType()) {
                case EMPTY:
                    fail("Full Enqueue Test #1 Failed; unexpected exception.", e);
                    return;
                case FULL:
                    System.out.println("Full Enqueue Test #1 Passed.");
                    break;
            }
        }

        try {
            for(int i = 0; i < queueSize; i++) {
                int result = queue.dequeue();
                if(result != values[i]) {
                    fail("Queue Order Error during Order Test #1.");
                    return;
                }
            }
            System.out.println("Order Test #1 Passed.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while dequeueing " + queueSize + " items from queue full queue during Order Test #1.", e);
            return;
        }

        try {
            x = queue.dequeue();
            fail("Empty Dequeue Test #2 Failed.");
            return;
        } catch (BoundedQueueException e) {
            switch (e.getType()) {
                case EMPTY:
                    System.out.println("Empty Dequeue Test #2 Passed.");
                    break;
                case FULL:
                    fail("Empty Dequeue Test #2 Failed; unexpected exception.", e);
                    return;
            }
        }

        try {
            x = 70;
            for(int i = 0; i < (queueSize - (queueSize / 2)); i++) {
                queue.enqueue(x);
                values[i] = x++;
            }
            System.out.println("Load #2 Complete.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while enqueueing " + (queueSize - (queueSize / 2)) + " items into empty queue during Load #2.", e);
            return;
        }

        try {
            for(int i = 0; i < (queueSize - (queueSize / 2)); i++) {
                int result = queue.dequeue();
                if(result != values[i]) {
                    fail("Queue Order Error during Order Test #2.");
                    return;
                }
            }
            System.out.println("Order Test #2 Passed.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while dequeueing " + (queueSize - (queueSize / 2)) + " items from queue during Order Test #2.", e);
            return;
        }        

        try {
            x = 700;
            for(int i = 0; i < queueSize; i++) {
                queue.enqueue(x);
                values[i] = x++;
            }
            System.out.println("Load #3 Complete.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while enqueueing " + queueSize + " items into empty queue during Load #3.", e);
            return;
        }

        try {
            for(int i = 0; i < queueSize; i++) {
                int result = queue.dequeue();
                if(result != values[i]) {
                    fail("Queue Order Error during Order Test #3.");
                    return;
                }
            }
            System.out.println("Order Test #3 Passed.");
        } catch (BoundedQueueException e) {
            fail("Unexpected Exception while dequeueing " + queueSize + " items from queue during Order Test #3.", e);
            return;
        }

        System.out.println("All Tests Passed.");
        System.exit(0);
    }

    private static void fail(String message) {
        System.err.println(message);
        System.exit(-1);
    }

    private static void fail(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        System.exit(-1);
    }

}


