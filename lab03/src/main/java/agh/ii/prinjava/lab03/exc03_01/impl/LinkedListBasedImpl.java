package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;

public class LinkedListBasedImpl implements QueueOfInts {

    @Override
    public void enqueue(int x) {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public int dequeue() {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public int numOfElems() {
        return numOfElems;
    }

    @Override
    public int peek() {
        throw new IllegalStateException("To be implemented");
    }

    private static class Node {
        int elem;
        Node next;
        Node prev;

        public Node(int elem, Node next, Node prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node first = null;
    private final Node last = null;

    private final int numOfElems = 0;
}
