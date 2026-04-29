public class RedBlackTree<T extends Comparable<T>> {

    /* 树的根节点。 */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {
        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * 创建一个 RBTreeNode，其项为 ITEM，颜色取决于 ISBLACK 的值。
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * 创建一个 RBTreeNode，具有指定的项 ITEM、颜色 ISBLACK、左孩子 LEFT 和右孩子 RIGHT。
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    public RedBlackTree() {
        root = null;
    }

    /**
     * 翻转节点及其子节点的颜色。假设 NODE 同时具有左孩子和右孩子。
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * 将给定节点向右旋转。返回该子树的新根节点。
     * 在此实现中，确保交换新根节点和旧根节点的颜色！
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> leftChild = node.left;

        node.left = leftChild.right;

        leftChild.right = node;

        leftChild.isBlack = node.isBlack;
        node.isBlack = false;

        return leftChild;
    }

    /**
     * 将给定节点向左旋转。返回该子树的新根节点。
     * 在此实现中，确保交换新根节点和旧根节点的颜色！
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> rightChild = node.right;

        node.right = rightChild.left;

        rightChild.left = node;

        rightChild.isBlack = node.isBlack;
        node.isBlack = false;

        return rightChild;
    }

    /**
     * 辅助方法，返回给定节点是否为红色。
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * 将项插入红黑树中。将树的根节点涂为黑色。
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * 将给定节点插入此红黑树。
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        if (node == null) {
            return new RBTreeNode<T>(false, item);
        }

        int i = item.compareTo(node.item);
        if (i < 0) {
            node.left = insert(node.left, item);
        } else if (i > 0) {
            node.right = insert(node.right, item);
        } else {
            return node;
        }

        // 重点：修复逻辑的顺序决定了旋转次数
        // 按照 CS61B 的标准 Lab 7 要求：
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }
}