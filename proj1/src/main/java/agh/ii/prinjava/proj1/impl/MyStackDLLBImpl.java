package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyStack;


public class MyStackDLLBImpl<E> implements MyStack<E> {
    private final DLinkList<E> elems = new DLinkList<>();

    /**
     * removes the top of the stack(newest element in the stack)
     * @return top element
     */
    @Override
    public E pop() {
        return this.elems.removeLast();
    }

    /**
     * Adds element at the top of the stack
     * @param x element to add
     */
    @Override
    public void push(E x) {
        this.elems.addLast(x);
    }

    /**
     * Counts the number of elements in the stack
     * @return number of elements
     */
    @Override
    public int numOfElems() {
        return this.elems.Count();
    }

    /**
     * Return the first element of the stack/ bottom value (without removing it)
     * @return first element
     */
    @Override
    public E peek() {
            E first = this.elems.removeFirst();
            this.elems.addFirst(first);
            return first;
    }

    /**
     * methid to string the stack
     * @return String
     */
    @Override
    public String toString() {
        return this.elems.toString();
    }

}
