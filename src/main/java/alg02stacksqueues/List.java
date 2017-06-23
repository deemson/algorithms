package alg02stacksqueues;


public interface List<E> {
    void add(E element);

    E get(int index);

    boolean isEmpty();

    int size();
}
