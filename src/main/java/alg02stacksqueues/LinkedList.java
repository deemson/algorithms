package alg02stacksqueues;


public class LinkedList<E> implements List<E>, Stack<E> {

    private class Node {
        E value;
        Node next;
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(E element) {
        Node node = head;
        if (head == null) {
            head = new Node();
            tail = head;
            head.value = element;
        } else {
            while ((node.next != null)) {
                node = node.next;
            }
            node.next = new Node();
            node.next.value = element;
        }
        size += 1;
    }

    @Override
    public E get(int index) {
        int i = 0;
        Node node = head;
        while (i < index) {
            i++;
            node = node.next;
        }
        return node.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        if (!this.isEmpty()) {
            Node node = head;
            for (int i = 0; i < size; i++) {
                array[i] = node.value;
                node = node.next;
            }
        }
        return array;
    }

    @Override
    public void push(E element) {

    }

    @Override
    public E pop() {
        return null;
    }

}
