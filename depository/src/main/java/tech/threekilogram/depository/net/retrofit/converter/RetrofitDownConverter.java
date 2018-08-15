package tech.threekilogram.depository.net.retrofit.converter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import tech.threekilogram.depository.file.BaseFileLoader;
import tech.threekilogram.depository.file.converter.FileStreamConverter;
import tech.threekilogram.depository.file.loader.DiskLruLoader;
import tech.threekilogram.depository.file.loader.FileLoader;
import tech.threekilogram.depository.net.retrofit.BaseRetrofitConverter;
import tech.threekilogram.depository.net.retrofit.loader.RetrofitDowner;

/**
 * 辅助{@link RetrofitDowner}将一个响应流保存到文件系统
 *
 * @author liujin
 */
public class RetrofitDownConverter extends BaseRetrofitConverter<File> {

      /**
       * 下载文件夹
       */
      private File                        mDir;
      /**
       * 保存文件
       */
      private BaseFileLoader<InputStream> mFileLoader;

      /**
       * @param dir 指定保存文件夹
       */
      public RetrofitDownConverter ( File dir ) {

            mDir = dir;
            mFileLoader = new FileLoader<>( dir, new FileStreamConverter() );
      }

      /**
       * @param dir 保存文件夹
       * @param maxSize 该文件夹最大大小
       */
      public RetrofitDownConverter ( File dir, int maxSize ) throws IOException {

            mDir = dir;
            mFileLoader = new DiskLruLoader<>( dir, maxSize, new FileStreamConverter() );
      }

      @Override
      public File onExecuteSuccess ( String key, ResponseBody response ) throws Exception {

            InputStream inputStream = response.byteStream();
            mFileLoader.save( key, inputStream );

            return mFileLoader.getFile( key );
      }

      @Override
      public void onExecuteFailed ( String key, int httpCode, ResponseBody errorResponse ) {

      }

      public File getDir ( ) {

            return mDir;
      }

      public File getFile ( String key ) {

            return mFileLoader.getFile( key );
      }
}
