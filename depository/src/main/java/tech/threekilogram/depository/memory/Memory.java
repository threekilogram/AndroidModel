package tech.threekilogram.depository.memory;

import tech.threekilogram.depository.Container;

/**
 * 这是内存缓存的总接口,所有内存缓存均实现了该接口,
 * <p>
 * this make some Object could load from Memory
 *
 * @param <V> value type to load
 * @param <K> identify to value, could use this to get value from container
 *
 * @author liujin
 */
public interface Memory<K, V> extends Container<K, V> {

      /**
       * 清空
       */
      void clear ( );
}
