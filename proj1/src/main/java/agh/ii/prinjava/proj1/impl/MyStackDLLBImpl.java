package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyStack;


public class MyStackDLLBImpl<E> implements MyStack<E> {
    private DLinkList<E> elems = new DLinkList<>();

    @Override
    public E pop() {
        return this.elems.removeLast();
    }

    @Override
    public void push(E x) {
        this.elems.addLast(x);
    }

    @Override
    public int numOfElems() {
        return this.elems.Count();
    }

    @Override
    public E peek() {
            E first = this.elems.removeFirst();
            this.elems.addFirst(first);
            return first;
    }

    @Override
    public String toString() {
        return this.elems.toString();
    }

}
