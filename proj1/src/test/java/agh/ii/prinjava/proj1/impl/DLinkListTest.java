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
        assertEquals(1, dLinkList.removeLast());
    }

    @Test
    void addFirst() {
        dLinkList.addLast(1);
        assertEquals(1, dLinkList.removeFirst());
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void testToString() {
        System.out.println(dLinkList);


    }
}