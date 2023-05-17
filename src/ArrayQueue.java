import java.util.EmptyStackException;

public final class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int frontIndex=0;
    private int backIndex;
    private boolean initialized=false;
    private static final int DEFAULT_CAPASİTY=50;
    private static final int MAX_CAPASİTY=10000;
    public ArrayQueue(){
        this(DEFAULT_CAPASİTY);
    }
    public ArrayQueue(int initialCapacity){
        checkCapasity(initialCapacity);
        @SuppressWarnings("unchecked")
        T[] tempQueue=(T[]) new Object[initialCapacity+1];
        queue=tempQueue;
        initialized=true;
        backIndex=initialCapacity;
    }
    @Override
    public void enqueue(T newEntry) {
        checkInitialization();
        ensureCapasity();
        backIndex=(backIndex+1)%queue.length;
        queue[backIndex]=newEntry;
    }
    private void checkCapasity(int capasity){
        if(capasity>MAX_CAPASİTY){
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    private void checkInitialization(){
        if(!initialized){
            throw new EmptyStackException();
        }
    }
    @Override
    public T dequeue() {
        checkInitialization();
        if(isEmpty()){
            throw new EmptyStackException();
        }else {
            T front=queue[frontIndex];
            queue[frontIndex]=null;
            frontIndex=(frontIndex+1)%queue.length;
            return front;
        }
    }
    private void ensureCapasity(){
        if(frontIndex==((backIndex+2)%queue.length)){
            T[] oldQueue=queue;
            int oldSize=oldQueue.length;
            int newSize=oldSize*2;
            checkCapasity(newSize);
            @SuppressWarnings("unchecked")
            T[] tempQueue=(T[])new Object[newSize];
            queue=tempQueue;
            for(int index=0;index<oldSize-1;index++){
                queue[index]=oldQueue[frontIndex];
                frontIndex=(frontIndex+1)%oldSize;
            }frontIndex=0;
            backIndex=oldSize-2;
        }
    }
    @Override
    public T getFront() {
        checkInitialization();
        if(isEmpty()){
            throw new EmptyStackException();
        }else{
            return queue[frontIndex];
        }
    }
    @Override
    public boolean isEmpty() {
        return frontIndex==((backIndex+1)% queue.length);
    }
    @Override
    public void clear() {
        while (!isEmpty()){
            dequeue();
        }
    }
}
