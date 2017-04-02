package custom.binary_tree_tests;

import custom.trees.BinaryTree;

public class Test {

    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<>(0);

        root.setLeftChild(new BinaryTree<>(1));
        root.setRightChild(new BinaryTree<>(2));

        root.getLeftChild().setLeftChild(new BinaryTree<>(3));
        root.getLeftChild().setRightChild(new BinaryTree<>(4));

        root.getRightChild().setLeftChild(new BinaryTree<>(5));
        root.getRightChild().setRightChild(new BinaryTree<>(6));

        System.out.println(root.toString());
    }
}
