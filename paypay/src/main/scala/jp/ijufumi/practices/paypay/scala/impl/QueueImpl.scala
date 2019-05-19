package jp.ijufumi.practices.paypay.scala.impl

import jp.ijufumi.practices.paypay.scala.Queue

import scala.reflect.ClassTag

class QueueImpl[T: ClassTag](val elements: Array[T]) extends Queue[T] {
  override def isEmpty: Boolean = elements.length == 0

  override def enQueue(t: T): Queue[T] = {
    val newElements:Array[T] = elements :+ t
    new QueueImpl[T](newElements)
  }

  override def deQueue(): Queue[T] = {
    if (elements.isEmpty) {
      return Queue.empty[T]
    }
    val newElements = elements.slice(1, elements.length)
    new QueueImpl[T](newElements)
  }

  override def head: Option[T] = elements.headOption
}
