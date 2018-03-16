package model;

import java.util.ArrayList;

/**
 * Created by Александр on 31.05.2017.
 */
public class BinaryTree {

    private Node root;
    private ArrayList<Long> newArrayList;
    private long timeSort;
    private ArrayList<Long> arrayList;


    public BinaryTree(ArrayList<Long> arrayList) {
        this.arrayList = arrayList;
        this.newArrayList = new ArrayList<>();
        this.root = null;
        long startTime = System.nanoTime();
        for (Long number : arrayList) {
            this.insert((long) Math.toIntExact(number));
        }
        this.sort(root);
        arrayList.clear();
        arrayList.addAll(newArrayList);
        this.timeSort = (System.nanoTime() - startTime);

    }

    private void insert(Long number) {
        Node newNode = new Node(number);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (number < current.number) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }

                }
            }

        }
    }

    public long getTimeforSorting() {
        return timeSort;
    }

    private void sort(Node root) {
        if (root == null) return;
        Node current = root;
        sort(current.leftChild);
        newArrayList.add(current.number);
        sort(current.rightChild);
    }
}

class Node {
    Long number;
    Node leftChild;
    Node rightChild;

    Node(Long number) {
        this.number = number;
        this.leftChild = null;
        this.rightChild = null;
    }

}
