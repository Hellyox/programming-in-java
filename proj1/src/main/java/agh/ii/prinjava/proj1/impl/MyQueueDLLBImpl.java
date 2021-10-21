package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyQueue;

public class MyQueueDLLBImpl<E> implements MyQueue<E> {
    private DLinkList<E> elems = new DLinkList<>();

    @Override
    public void enqueue(E x) {
        this.elems.addLast(x);
    }

    @Override
    public E dequeue() {
        return this.elems.removeFirst();
    }

    @Override
    public int numOfElems() {
        return this.elems.Count();
    }

    @Override
    public E peek() {
        E last = this.elems.removeLast();
        this.elems.addLast(last);
        return last;
    }
}
