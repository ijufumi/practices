package jp.ijufumi.practices.paypay.impl;

import jp.ijufumi.practices.paypay.Queue;

import java.util.Arrays;

public class QueueImpl<T> implements Queue<T> {

    private Object[] elements;

    public QueueImpl() {
        this(new Object[0]);
    }

    public QueueImpl(Object..._elements) {
        if (_elements == null) {
            throw new IllegalArgumentException("elements not allowed null.");
        }
        elements = _elements;
    }

    public Queue<T> enQueue(T t) {
        var newElements = new Object[elements.length + 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        newElements[elements.length] = t;
        return new QueueImpl<>(newElements);
    }

    public Queue<T> deQueue() {
        var newElements = new Object[elements.length - 1];
        System.arraycopy(elements, 1, newElements, 0, elements.length - 1);
        return new QueueImpl<>(newElements);
    }

    @SuppressWarnings("unchecked")
    public T head() {
        return (T) Arrays.stream(elements).findFirst().orElse(null);
    }

    public boolean isEmpty() {
        return elements.length == 0;
    }
}
