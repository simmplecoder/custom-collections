package custom.trees;

public class BinarySearchTree<E extends Comparable<E>> {
    private BinaryTree<E> root;

    public void add(E value)
    {
        if (root == null)
        {
            root = new BinaryTree<>(value);
            return;
        }

        recursiveAdd(root, value);
    }

    private void recursiveAdd(BinaryTree<E> current, E value)
    {
        int comparisonResult = current.getValue().compareTo(value);
        if (comparisonResult < 0)
        {
            if (current.getRightChild() == null)
            {
                current.setRightChild(new BinaryTree<>(value));
                return;
            }
            recursiveAdd(current.getRightChild(), value);
        }
        else if (comparisonResult > 0)
        {
            if (current.getLeftChild() == null)
            {
                current.setLeftChild(new BinaryTree<>(value));
            }
            recursiveAdd(current.getLeftChild(), value);
        }
    }

    public boolean contains(E value)
    {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(BinaryTree<E> current, E value)
    {
        if (current == null)
        {
            return false;
        }

        int comparisonResult = value.compareTo(current.getValue());
        if (comparisonResult == 0)
        {
            return true;
        } else if (comparisonResult < 0)
        {
            return containsRecursive(current.getLeftChild(), value);
        }
        else
        {
            return containsRecursive(current.getRightChild(), value);
        }

    }

    @Override
    public String toString()
    {
        return root.toString();
    }
}
