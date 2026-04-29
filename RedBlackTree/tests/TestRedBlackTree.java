import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * RedBlackTree.java 的测试类。
 *
 * 我们在文件中的每个操作后都提供了 LLRB（左倾红黑树）的表示形式作为注释，以帮助你调试。
 *
 * 黑色节点用 () 表示，红色节点用 ()* 表示。
 * 左孩子列在右孩子之前。
 */
public class TestRedBlackTree {

    /**
     * 测试最基本的右旋情况。
     * 此测试不检查颜色翻转，仅检查右旋后节点是否处于正确的位置。
     * 注意：我们没有提供左旋的基础测试，但右旋和左旋的实现细节应该是对称的。
     */
    @Test
    public void testBasicRotateRight() {
        // 插入 10, 9, 8
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        assertThat(rbtree.root).isNull();

        // 手动构建一个需要右旋的结构
        RedBlackTree.RBTreeNode<Integer> node1 = new RedBlackTree.RBTreeNode<>(true, 10, null, null);
        RedBlackTree.RBTreeNode<Integer> node2 = new RedBlackTree.RBTreeNode<>(false, 9, null, null);
        RedBlackTree.RBTreeNode<Integer> node3 = new RedBlackTree.RBTreeNode<>(false, 8, null, null);
        node1.left = node2;
        node2.left = node3;

        RedBlackTree.RBTreeNode<Integer> newRoot = rbtree.rotateRight(node1);
        assertThat(newRoot.item).isEqualTo(9);
        assertThat(newRoot.right.item).isEqualTo(10);
        assertThat(newRoot.left.item).isEqualTo(8);
    }

    @Test
    public void testInsertSimple() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        /*
        LLRB 树表示：
        (空)
         */
        assertThat(rbtree.root).isNull();

        rbtree.insert(10);

        /*
        LLRB 树表示：
           (10)
         */

        // 检查根节点
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);

        // 检查左孩子
        assertThat(rbtree.root.left).isNull();

        // 检查右孩子
        assertThat(rbtree.root.right).isNull();

        rbtree.insert(5);

        /*
        LLRB 树表示：
            (10)
            └── (5)*
         */

        // 检查根节点
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);

        // 检查左孩子
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isFalse(); // 应该是红色
        assertThat(rbtree.root.left.item).isEqualTo(5);

        // 检查左孩子的子节点
        assertThat(rbtree.root.left.left).isNull();
        assertThat(rbtree.root.left.right).isNull();

        // 检查右孩子
        assertThat(rbtree.root.right).isNull();

        assertWithMessage("按顺序插入 (10, 5) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(0);
    }

    @Test
    public void testInsertFlipColor() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(15);

        /*
        LLRB 树表示：
            (10)
            ├── (5)
            └── (15)
         */

        // 根节点
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);

        // 左孩子
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(5);

        // 右孩子
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(15);

        assertWithMessage("按顺序插入 (10, 5, 15) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 5, 15) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5, 15) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(0);
    }

    @Test
    public void testInsertRotateLeft() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(15);

        /*
        LLRB 树表示：
            (15)
            └── (10)*
         */

        // 根节点
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(15);

        // 左孩子
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isFalse();
        assertThat(rbtree.root.left.item).isEqualTo(10);

        assertWithMessage("按顺序插入 (10, 15) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 15) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 15) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(0);
    }

    @Test
    public void testInsertRotateRight() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(3);

        /*
        LLRB 树表示：
            (5)
            ├── (3)
            └── (10)
         */

        // 根节点
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(5);

        // 左孩子
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(3);

        // 右孩子
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(10);

        // 如果实现正确，在同一次插入中如果不调用颜色翻转，是不可能测试右旋的
        assertWithMessage("按顺序插入 (10, 5, 3) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 5, 3) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5, 3) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(1);
    }

    @Test
    public void testInsertAllFixes() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(7);

        /*
        LLRB 树表示：
            (7)
            ├── (5)
            └── (10)
         */

        assertThat(rbtree.root.item).isEqualTo(7);
        assertThat(rbtree.root.left.item).isEqualTo(5);
        assertThat(rbtree.root.right.item).isEqualTo(10);

        assertWithMessage("按顺序插入 (10, 5, 7) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 5, 7) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 5, 7) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(1);
    }

    @Test
    public void testInsertUpwardPropagation() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        rbtree.insert(5);
        rbtree.insert(11);
        rbtree.insert(3);
        rbtree.insert(9);
        rbtree.insert(7);
        rbtree.insert(1);
        rbtree.insert(2);

        /*
        LLRB 树表示：
            (5)
            ├── (2)
            │   ├── (1)
            │   └── (3)
            └── (9)
                ├── (7)
                └── (11)
         */

        assertThat(rbtree.root.item).isEqualTo(5);
        assertThat(rbtree.root.left.item).isEqualTo(2);
        assertThat(rbtree.root.right.item).isEqualTo(9);
        assertThat(rbtree.root.left.left.item).isEqualTo(1);
        assertThat(rbtree.root.left.right.item).isEqualTo(3);
        assertThat(rbtree.root.right.left.item).isEqualTo(7);
        assertThat(rbtree.root.right.right.item).isEqualTo(11);

        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(4);
        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用左旋的次数").that(callsToRotateLeft).isEqualTo(3);
        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用右旋的次数").that(callsToRotateRight).isEqualTo(4);
    }

    /**
     * 一个非常巧妙的类，用于测试你的 LLRB 树实现中调用“修复”操作（旋转、翻转）的次数。
     */
    class TestableRedBlackTree extends RedBlackTree<Integer> {

        @Override
        void flipColors(RBTreeNode<Integer> node) {
            callsToFlipColors++;
            super.flipColors(node);
        }

        @Override
        RBTreeNode<Integer> rotateRight(RBTreeNode<Integer> node) {
            callsToRotateRight++;
            return super.rotateRight(node);
        }

        @Override
        RBTreeNode<Integer> rotateLeft(RBTreeNode<Integer> node) {
            callsToRotateLeft++;
            return super.rotateLeft(node);
        }
    }

    private int callsToFlipColors = 0;
    private int callsToRotateRight = 0;
    private int callsToRotateLeft = 0;
}