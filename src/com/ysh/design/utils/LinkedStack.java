package com.ysh.design.utils;

/**
 * @author joeysh
 * @date 2018/09/29 23:17
 */
public class LinkedStack<T> {
    private static class Node<K> {
        K item;
        Node<K> next;

        Node() {
            item = null;
            next = null;
        }

        Node(K item, Node<K> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();

    public void add(T item) {
        top = new Node<>(item, top);
    }

    public T get() {
        T item = top.item;
        if (!top.end()) {
            top = top.next;
                               }
        return item;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (String s : "a b c".split(" ")) {
            stack.add(s);
        }
        String result;
        while ((result = stack.get()) != null) {
            System.out.println(result);
        }
    }
}
