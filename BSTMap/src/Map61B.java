import java.util.Set;

/* 你的 BSTMap 实现应当实现此接口。
 * 为此，请将 "implements Map61B<K, V>" 附加到你的 "public class..." 声明的末尾，
 * 不过在必要时，你可以且应该使用其他的类型参数。
 */
public interface Map61B<K, V> extends Iterable<K> {

    /** 将指定的值与此映射（Map）中的指定键相关联。
     *  如果此映射中已经包含了该键，则将其对应的值替换为指定的新值。 */
    void put(K key, V value);

    /** 返回指定键所映射的值；如果此映射不包含该键的映射，则返回 null。 */
    V get(K key);

    /** 返回此映射是否包含指定键的映射。 */
    boolean containsKey(K key);

    /** 返回此映射中键值对（key-value mappings）的数量。 */
    int size();

    /** 移除此映射中的所有映射关系。 */
    void clear();

    /** 返回此映射中包含的所有键的 Set 视图。Lab 7 不做强制要求。
     * 如果你不实现此方法，请抛出 UnsupportedOperationException。 */
    Set<K> keySet();

    /** 如果存在，则从此映射中移除指定键的映射关系；
     * 如果不存在该映射，则返回 null。
     * Lab 7 不做强制要求。如果你不实现此方法，请抛出
     * UnsupportedOperationException。 */
    V remove(K key);
}