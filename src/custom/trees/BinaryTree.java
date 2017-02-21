package custom.trees;

public class BinaryTree<E> {
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;
    private E value;

    public BinaryTree(E value)
    {
        this.value = value;
    }

    public BinaryTree<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTree<E> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTree<E> rightChild) {
        this.rightChild = rightChild;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        buildString(this, builder);
        return builder.toString();
    }

    private void buildString(BinaryTree<E> node, StringBuilder builder)
    {
        if (node == null)
        {
            return;
        }

        buildString(node.getLeftChild(), builder);
        builder.append(node.getValue().toString());
        builder.append('\n');
        buildString(node.getRightChild(), builder);
    }
}
