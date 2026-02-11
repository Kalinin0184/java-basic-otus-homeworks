package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {

    private Node root;

    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree(List<T> sortedList) {
        if (sortedList == null || sortedList.isEmpty()) {
            this.root = null;
        } else {
            this.root = buildTree(sortedList, 0, sortedList.size() - 1);
        }
    }

    private Node buildTree(List<T> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(sortedList.get(mid));

        node.left = buildTree(sortedList, start, mid - 1);
        node.right = buildTree(sortedList, mid + 1, end);

        return node;
    }

    @Override
    public T find(T element) {
        if (element == null || root == null) {
            return null;
        }
        return findRecursive(root, element);
    }

    private T findRecursive(Node node, T element) {
        if (node == null) {
            return null;
        }

        int comparison = element.compareTo(node.data);

        if (comparison == 0) {
            return node.data;
        } else if (comparison < 0) {
            return findRecursive(node.left, element);
        } else {
            return findRecursive(node.right, element);
        }
    }

    @Override
    public List<T> getSortedList() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node node, List<T> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.data);
            inOrderTraversal(node.right, result);
        }
    }
}

