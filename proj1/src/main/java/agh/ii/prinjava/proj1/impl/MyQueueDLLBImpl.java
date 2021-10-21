package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyQueue;

public class MyQueueDLLBImpl<E> implements MyQueue<E> {
    private DLinkList<E> elems = new DLinkList<>();


    /**
     * Removes the end of the line (first new value)
     * @return last element
     */
    @Override
    public void enqueue(E x) {
        this.elems.addLast(x);
    }

    /**
     * removes the last value (oldest asigned)
     * @return first element
     */
    @Override
    public E dequeue() {
        return this.elems.removeFirst();
    }

    /**
     * Counts the number of elements in the queue
     * @return number of elements
     */
    @Override
    public int numOfElems() {
        return this.elems.Count();
    }

    /**
     *Returns the head of the queue without removing it
     * @return head of the queue
     */
    @Override
    public E peek() {
        E last = this.elems.removeLast(); /** we use removeLast() to get the value **/
        this.elems.addLast(last);/** we get the value back to the queue**/
        return last;
    }
}
