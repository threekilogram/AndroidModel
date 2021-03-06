package tech.liujin.cache.memory.lru;

import android.graphics.Bitmap;
import tech.liujin.cache.memory.lru.size.BitmapSize;

/**
 * {@link MemoryLruCache}的bitmap实现版本,用于在内存中缓存bitmap,当内存中bitmap达到一个阈值时,会清理掉一部分
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-08-15
 * @time: 18:13
 */
public class MemoryListBitmap extends MemoryLruCache<Integer, Bitmap> {

      public MemoryListBitmap ( int maxSize ) {

            super( maxSize, new BitmapSize() );
      }
}
