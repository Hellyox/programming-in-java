package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {
    DLinkList<Integer> dLinkList = new DLinkList<>();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addLast() {
        dLinkList.addLast(1);
        dLinkList.addLast(4);
        dLinkList.addLast(5);
        assertEquals(5, dLinkList.removeLast());
    }

    @Test
    void addFirst() {
        dLinkList.addLast(2);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        assertEquals(2, dLinkList.removeFirst());
    }

    @Test
    void removeFirst() {
        dLinkList.addLast(2);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.removeFirst();
        assertEquals("DLinkList = 1;3;",dLinkList.toString());
    }

    @Test
    void removeLast() {
        dLinkList.addLast(2);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.removeLast();
        assertEquals("DLinkList = 2;1;",dLinkList.toString());
    }

    @Test
    void testToString() {
        System.out.println(dLinkList);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.addLast(5);
        System.out.println(dLinkList);
        assertEquals("DLinkList = 1;3;5;", dLinkList.toString());
    }

    @Test
    void count() {
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.addLast(5);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.addLast(5);
        dLinkList.Count();
        assertEquals(6, dLinkList.Count());
    }
}