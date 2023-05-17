import java.util.Arrays;

public class    AList<T> {
    private T[] list;
    private int numberOfEntries;
    private boolean initialized=false;
    private static final int Default_Capasity=25;
    private static final int MAX_CAPASİTY=10000;
    public AList(){
        this(Default_Capasity);
    }
    public AList(int initialCapasity){
        if(initialCapasity<Default_Capasity){
            initialCapasity=Default_Capasity;
        }else{
            checkCapasity(initialCapasity);
        }
        @SuppressWarnings("unchecked")
        T[] tempList=(T[])new  Object[initialCapasity+1];
        list=tempList;
        numberOfEntries=0;
        initialized=true;
    }

    public int getLength() {
        return numberOfEntries;
    }

    public void add(T newEntry){
        checkInitialization();
        list[numberOfEntries+1]=newEntry;
        numberOfEntries++;
        ensureCapasity();
    }
    void ensureCapasity(){
        int capasity=list.length-1;
        if(numberOfEntries>=capasity){
            int newCapasity=2*capasity;
            checkCapasity(newCapasity);
            list= Arrays.copyOf(list,newCapasity+1);
        }
    }
    private void checkInitialization(){
        if(!initialized){
            throw new SecurityException("Something went wrong...");
        }
    }
    public void checkCapasity(int newLength){
        if(newLength>=MAX_CAPASİTY){
            throw new ExceptionInInitializerError();
        }
    }
    public void add(int newPosition, T newEntry){
        checkInitialization();
        if((newPosition>=1)&&(newPosition<=numberOfEntries+1)){
            if(newPosition<=numberOfEntries){
                makeRoom(newPosition);
                list[newPosition]=newEntry;
                numberOfEntries++;
                ensureCapasity();
            }
        }else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of the bounds.");
        }
    }
    private void makeRoom(int newPosition){
        assert (newPosition>=1)&&(newPosition<=numberOfEntries+1);
        int newIndex=newPosition;
        int lastIndex=numberOfEntries;
        for(int index=lastIndex;index>=newIndex;index--){
            list[index+1]=list[index];
        }
    }
    public T remove(int givenPosition){
        checkInitialization();
        if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            T result=list[givenPosition];
            if(givenPosition<numberOfEntries){
                removeGap(givenPosition);
            }numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
            // Add a return statement here if needed
        }
    }

    private void removeGap(int givenPositiom){
        assert (givenPositiom<=1)&&(givenPositiom<numberOfEntries);
        int removedIndex=givenPositiom;
        int lastIndex=numberOfEntries;
        for(int index=removedIndex;index<lastIndex;index++){
            list[index]=list[index+1];
        }
    }
    private boolean isEmpty(){
        return  numberOfEntries==0;
    }
    public T[] toArray(){
        checkInitialization();
        @SuppressWarnings("Unchecked")
        T[] result=(T[]) new Object[numberOfEntries];
        return null;//////////////////////////////////////

    }

    public boolean replace(int givenPosition, T newEntry){
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry=list[givenPosition];
            list[givenPosition]=newEntry;
            return true;

        }else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    }
    public T getEntry(int givenPosition){
        checkInitialization();
        if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            return list[givenPosition];
        }else{
            throw new IndexOutOfBoundsException("aed");
        }
    }
    public boolean contains(T anEntry){
        checkInitialization();
        boolean found=false;
        int index=1;
        while(!found&&(index<=numberOfEntries)){
            if(anEntry.equals(list[index])){
                found=true;
            }index++;
        }return found;
    }
}
