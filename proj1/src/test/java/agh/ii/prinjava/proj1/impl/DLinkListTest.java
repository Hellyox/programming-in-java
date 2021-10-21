package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {
    DLinkList<Integer> dLinkList = new DLinkList<>();
    /** we declare a double linked List that we will use for our unit tests**/

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     *We just make sure the first value listed is the one removed by the function removeLast
     * So we know our addLast works!
     */
    @Test
    void addLast() {
        dLinkList.addLast(1);
        dLinkList.addLast(4);
        dLinkList.addLast(5);
        assertEquals(5, dLinkList.removeLast());
    }

    /**
     *We just make sure the last value listed is the one removed by the function removeFirst
     * So we know our addFirst works!
     */
    @Test
    void addFirst() {
        dLinkList.addFirst(2);
        dLinkList.addFirst(1);
        dLinkList.addFirst(3);
        assertEquals(3, dLinkList.removeFirst());
    }

    /**
     *We display the list and we remove the first element
     */
    @Test
    void removeFirst() {
        dLinkList.addLast(2);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.removeFirst();
        assertEquals("DLinkList = 1;3;",dLinkList.toString());
    }

    /**
     *We display the list and we remove the last element
     */
    @Test
    void removeLast() {
        dLinkList.addLast(2);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.removeLast();
        assertEquals("DLinkList = 2;1;",dLinkList.toString());
    }


    /**
     * We check if the string is the same as what expected
     */
    @Test
    void testToString() {
        System.out.println(dLinkList);
        dLinkList.addLast(1);
        dLinkList.addLast(3);
        dLinkList.addLast(5);
        System.out.println(dLinkList);
        assertEquals("DLinkList = 1;3;5;", dLinkList.toString());
    }

    /**
     * We expect to count 6 elements in the list
     *
     */
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