package alg02stacksqueues;

import static alg02stacksqueues.ArrayUtils.resize;

public class ArrayList<E> implements List<E>, Stack<E> {

    private E[] array;
    private int cursor;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (E[]) new Object[32];
        cursor = 0;
    }

    @Override
    public void add(E element) {
        array[cursor++] = element;
        if (cursor == array.length) {
            array = resize(array, array.length * 2, 0, 0, cursor);
        }
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return cursor == 0 && array[0] == null;
    }

    @Override
    public int size() {
        return cursor;
    }

    public E[] toArray() {
        return resize(array, cursor, 0, 0, cursor);
    }

    @Override
    public void push(E element) {
        array[cursor++] = element;
        if (cursor == array.length) {
            array = resize(array, array.length * 2, 0, 0, cursor);
        }
    }

    @Override
    public E pop() {
        if (this.isEmpty())
            throw new IllegalStateException("pop from an empty ArrayList");
        E result = array[--cursor];
        array[cursor] = null;
        return result;
    }

}
