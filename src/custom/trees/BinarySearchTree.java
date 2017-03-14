package custom.trees;

public class BinarySearchTree<E extends Comparable> {
    private BinaryTree<E> root;
    private int size;

    public void add(E value) {
        if (root == null) {
            root = new BinaryTree<>(value);
            ++size;
            return;
        }

        recursiveAdd(root, value);
    }

    private void recursiveAdd(BinaryTree<E> current, E value) {
        int comparisonResult = current.getValue().compareTo(value);
        if (comparisonResult < 0) {
            if (current.getRightChild() == null) {
                current.setRightChild(new BinaryTree<>(value));
                ++size;
                return;
            }
            recursiveAdd(current.getRightChild(), value);
        } else if (comparisonResult > 0) {
            if (current.getLeftChild() == null) {
                current.setLeftChild(new BinaryTree<>(value));
                ++size;
            }
            recursiveAdd(current.getLeftChild(), value);
        }
    }

    public boolean contains(E value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(BinaryTree<E> current, E value) {
        if (current == null) {
            return false;
        }

        int comparisonResult = value.compareTo(current.getValue());
        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult < 0) {
            return containsRecursive(current.getLeftChild(), value);
        } else {
            return containsRecursive(current.getRightChild(), value);
        }

    }

    public boolean remove(E value) {
        return removeRecursive(root, null, value);
    }

    private boolean removeRecursive(BinaryTree<E> current, BinaryTree<E> parent, E value) {
        if (current == null) {
            return false;
        }

        int comparisonResult = value.compareTo(current.getValue());

        if (comparisonResult < 0) {
            return removeRecursive(current.getLeftChild(), current, value);

        } else if (comparisonResult > 0) {
            return removeRecursive(current.getRightChild(), current, value);

        }

        int childCount = 0;
        childCount += (current.getLeftChild() == null) ? 0 : 1;
        childCount += (current.getRightChild() == null) ? 0 : 1;

        if (childCount == 0) {
            if (current == root) {
                root = null;
                --size;
                return true;
            }

            if (parent.getLeftChild() == current) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }

            --size;
            return true;
        } else if (childCount == 1) {
            if (current == root)
            {
                if (root.getLeftChild() != null)
                {
                    root = root.getLeftChild();
                }
                else
                {
                    root = root.getRightChild();
                }

                --size;
                return true;
            }

            BinaryTree<E> child = (current.getLeftChild() != null) ?
                    current.getLeftChild() :
                    current.getRightChild();

            if (parent.getLeftChild() == current) {
                parent.setLeftChild(child);
            } else {
                parent.setRightChild(child);
            }

            --size;
            return true;
        }

        BinaryTree<E> successor = getLeftMostChild(current.getRightChild());
        current.setValue(successor.getValue());
        BinaryTree<E> successorsParent = current.getRightChild();
        while (successorsParent.getLeftChild() != successor) {
            successorsParent = successorsParent.getLeftChild();
        }

        if (successorsParent == successor) {
            current.setRightChild(successor.getRightChild());
        } else {
            successorsParent.setLeftChild(successor.getRightChild());
        }

        --size;
        return true;

    }

    public void removeAny()
    {
        if (size == 0)
        {
            throw new IllegalStateException("Calling removeAny on empty tree");
        }

        remove(getLeftMostChild(root).getValue());
    }

    private BinaryTree<E> getLeftMostChild(BinaryTree<E> current)
    {
        while (current.getLeftChild() != null)
        {
            current = current.getLeftChild();
        }

        return current;
    }

    public int size()
    {
        return size;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        buildString(root, builder);
        if (size != 0)
        {
            builder.deleteCharAt(builder.length() - 1);
        }

        builder.append(']');

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
        builder.append(' ');
        buildString(node.getRightChild(), builder);
    }
}
