package agh.ii.prinjava.proj1.impl;

import agh.ii.prinjava.proj1.MyStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackDLLBImplTest {
    MyStack<Integer> stackOfInts = MyStack.create();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pop() {
        this.stackOfInts.push(1);
        this.stackOfInts.push(2);
        this.stackOfInts.pop();
        assertEquals("DLinkList = 1;", this.stackOfInts.toString());
    }

    @Test
    void push() {
        this.stackOfInts.push(1);
        this.stackOfInts.push(2);
        assertEquals("DLinkList = 1;2;", this.stackOfInts.toString());

    }

    @Test
    void numOfElems() {
        this.stackOfInts.push(1);
        this.stackOfInts.push(2);
        this.stackOfInts.push(1);
        this.stackOfInts.push(2);
        this.stackOfInts.numOfElems();
        Assertions.assertEquals(4, this.stackOfInts.numOfElems());
    }

    @Test
    void peek() {
        this.stackOfInts.push(1);
        this.stackOfInts.push(2);
        this.stackOfInts.push(3);
        Assertions.assertEquals(1, this.stackOfInts.peek());
    }

}