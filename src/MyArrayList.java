import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T> {

    private final int INIT_SIZE = 16;
    private int capacity;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.capacity = INIT_SIZE;
        this.size = 0;
        this.array = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new Object[capacity];
    }

    public MyArrayList(List<T> list) {
        this.capacity = list.size();
        this.size = list.size();
        array = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = list.get(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object object: array){
            if(object.equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return (T) array[currentIndex++];
            }
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    @Override
    public boolean add(T t) {
        if(size == capacity){
            array = increaseArray(array);
        }
        array[size++] = t;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size)
            return;
        if(size == capacity){
            array = increaseArray(array);
        }
        for (int i = size++; i >= index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o) == false)
            return false;
        else {
            int index = indexOf(o);
            remove(index);
            return true;
        }
    }

    @Override
    public T remove(int index) {
        if(size <= 0 || index < 0 || index >= size )
            return null;
        T removeElement = (T) array[index];
        for(int i = index; i < size - 1; i++){
            array[i]=array[i + 1];
        }
        array = reduceArray(array);
        size--;
        return removeElement;
    }

    @Override
    public T get(int index) {
        if(size <= 0 || index < 0 || index >= size )
            return null;
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if(size <= 0 || index < 0 || index >= size )
            return null;
        array[index] = element;
        return (T) array[index];
    }

    @Override
    public int indexOf(Object o) {
       for (int i = 0; i < size; i++) {
            if(array[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o)){
                index = i;
            }
        }
        return index;
    }
    public Object[] increaseArray(Object[] o){
        capacity = capacity + capacity / 2 + 1;
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    public Object[] reduceArray(Object[] o){
        Object[] newArray = new Object[size-1];
        for (int i = 0; i < size-1; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }


    //unimplemented methods

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
