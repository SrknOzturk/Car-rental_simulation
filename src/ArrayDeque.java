public final class ArrayDeque<T> {
    private T[] dequeue;
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    private int numberOfENTRİES=0;////

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayDeque(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempDequeue = (T[])new Object[initialCapacity + 1];
        dequeue = tempDequeue;
        frontIndex = 0;
        backIndex = initialCapacity;
    }
    public void addToFront(T newEntry) {
        if (frontIndex == 0) {
            frontIndex = dequeue.length - 1;
        } else {
            frontIndex = (frontIndex - 1) % dequeue.length;
        }
        dequeue[frontIndex] = newEntry;
        numberOfENTRİES++;
    }
    public void addToBack(T newEntry) {
        backIndex = (backIndex + 1) % dequeue.length;
        dequeue[backIndex] = newEntry;
        numberOfENTRİES++;
    }
    public T removeFront() {
        T result = dequeue[frontIndex];
        dequeue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % dequeue.length;
        numberOfENTRİES--;
        return result;
    }
    public T removeBack() {
        T result = dequeue[backIndex];
        dequeue[backIndex] = null;
        if (backIndex == 0) {
            backIndex = dequeue.length - 1;
        } else {
            backIndex = (backIndex - 1) % dequeue.length;
        }numberOfENTRİES--;
        return result;
    }
    public T getFront() {
        return dequeue[frontIndex];
    }
    public T getBack() {
        return dequeue[backIndex];
    }
    public boolean isEmpty() {
        return frontIndex == (backIndex + 1) % dequeue.length;
    }
    public void clear() {
        for (T a:dequeue) {
            a = null;
 }
}
int getLength(){
        return numberOfENTRİES;
}
}
