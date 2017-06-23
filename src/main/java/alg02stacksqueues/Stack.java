package alg02stacksqueues;


public interface Stack<E> {

    void push(E element);

    E pop();

    boolean isEmpty();

    int size();

    E[] toArray();

}
