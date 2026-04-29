public class UnionFind {
    // TODO: 实例变量
    private int[] parents;

    /* 创建一个包含 N 个元素的并查集数据结构。初始状态下，所有元素都位于互不相交的独立集合中。 */
    public UnionFind(int N) {
        // TODO: 在这里编写代码
        parents = new int[N];
        java.util.Arrays.fill(parents, -1);
    }

    /* 返回元素 V 所属集合的大小。 */
    public int sizeOf(int v) {
        // TODO: 在这里编写代码
        validate(v);
        int res = find(v);
        return -parents[res];
    }

    /* 返回 V 的父节点。如果 V 是树的根节点，则返回 V 为根的那棵树的大小的负值。 */
    public int parent(int v) {
        // TODO: 在这里编写代码
        validate(v);
        return parents[v];
    }

    /* 如果节点/顶点 V1 和 V2 是连通的，则返回 true。 */
    public boolean connected(int v1, int v2) {
        // TODO: 在这里编写代码
        int f1 = find(v1);
        int f2 = find(v2);

        return f1 == f2;
    }

    /* 返回元素 V 所属集合的根节点。使用了路径压缩技术以实现快速查找。如果传入此函数的元素无效，则抛出 IllegalArgumentException 异常。 */
    public int find(int v) {
        // TODO: 在这里编写代码
        // 1.检查v是否在数组范围内
        validate(v);

        // 2.递归终止条件
        if (parents[v] < 0) {
            return v;
        }

        // 3.递归找出根节点
        parents[v] = find(parents[v]); // 顺便进树的压缩
        return parents[v];
    }

    private void validate(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException(v + "is not a valid num");
        }
    }

    /* 将 V1 和 V2 两个元素连接起来（即连接它们各自的集合）。V1 和 V2 可以是任意元素，必须使用“按大小合并”的启发式策略。
       如果两个集合的大小相等，通过将 V1 的根节点连接到 V2 的根节点下来打破平局。
       将一个元素与自身合并，或者合并两个已经连通的元素，不应改变现有的结构。 */
    public void union(int v1, int v2) {
        // TODO: 在这里编写代码
        int loot1 = find(v1); // 1.找出v1和v2的根节点（索引）
        int loot2 = find(v2);

        int s1 = sizeOf(v1); // 2.找出对应根节点在数组中的值，即树的长度
        int s2 = sizeOf(v2);

        if (loot1 == loot2){
            return;
        }

        if (s1 > s2) { // 3.如果v1所在集合为大树，则将v2的根节点连接至v1的根节点
            parents[loot2] = loot1;
            parents[loot1] = - (s1 + s2);
        }else {
            parents[loot1] = loot2;
            parents[loot2] = - (s1 + s2);
        }

    }

}
