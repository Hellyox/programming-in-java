package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueDLLBImplTest {
    MyQueue<Integer> queueOfInts = MyQueue.create();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void enqueue() {
        this.queueOfInts.enqueue(1);
        this.queueOfInts.enqueue(2);
        this.queueOfInts.enqueue(3);
        assertEquals(1, this.queueOfInts.dequeue());
    }

    @Test
    void dequeue() {
        this.queueOfInts.enqueue(1);
        this.queueOfInts.enqueue(2);
        this.queueOfInts.enqueue(3);
        this.queueOfInts.dequeue();
        assertEquals(2,this.queueOfInts.numOfElems());
    }

    @Test
    void numOfElems() {
        this.queueOfInts.enqueue(1);
        this.queueOfInts.enqueue(2);
        this.queueOfInts.enqueue(3);
        this.queueOfInts.dequeue();
        assertEquals(2,this.queueOfInts.numOfElems());
    }

    @Test
    void peek() {
        this.queueOfInts.enqueue(1);
        this.queueOfInts.enqueue(5);
        this.queueOfInts.enqueue(8);
        assertEquals(8, this.queueOfInts.peek());

    }
}