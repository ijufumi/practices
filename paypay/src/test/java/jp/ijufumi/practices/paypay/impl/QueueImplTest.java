package jp.ijufumi.practices.paypay.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueImplTest {

    @Test
    void enqueueTest() {
        var queue = new QueueImpl<String>();

        assertTrue(queue.isEmpty());

        var queue2 = queue.enQueue("test1");

        assertTrue(queue.isEmpty());
        assertFalse(queue2.isEmpty());

        assertNull(queue.head());
        assertEquals("test1", queue2.head());
    }

    @Test
    void dequeueTest() {
        var queue = new QueueImpl<Integer>(1, 2);

        assertEquals(1, queue.head());

        var queue2 = queue.deQueue();

        assertEquals(1, queue.head());
        assertEquals(2, queue2.head());

        var queue3 = queue2.deQueue();

        assertFalse(queue.isEmpty());
        assertFalse(queue2.isEmpty());
        assertTrue(queue3.isEmpty());

        assertEquals(1, queue.head());
        assertEquals(2, queue2.head());
        assertNull(queue3.head());
    }
}