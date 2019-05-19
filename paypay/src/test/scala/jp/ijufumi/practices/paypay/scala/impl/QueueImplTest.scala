package jp.ijufumi.practices.paypay.scala.impl

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

import org.scalatest.Matchers

class QueueImplTest extends Matchers {
  @Test
  def enqueueTest(): Unit = {
    val queue = new QueueImpl[String](Array.empty)
    assertTrue(queue.isEmpty)

    val queue2 = queue.enQueue("test1")

    assertTrue(queue.isEmpty)
    assertFalse(queue2.isEmpty)

    assertEquals(None, queue.head)
    assertEquals(Some("test1"), queue2.head)
  }

  @Test
  def dequeueTest(): Unit = {
    val queue = new QueueImpl[Integer](Array[Integer](1, 2))
    assertEquals(Some(1), queue.head)

    val queue2 = queue.deQueue

    assertEquals(Some(1), queue.head)
    assertEquals(Some(2), queue2.head)

    val queue3 = queue2.deQueue

    assertFalse(queue.isEmpty)
    assertFalse(queue2.isEmpty)
    assertTrue(queue3.isEmpty)

    assertEquals(Some(1), queue.head)
    assertEquals(Some(2), queue2.head)
    assertEquals(None, queue3.head)
  }
}
