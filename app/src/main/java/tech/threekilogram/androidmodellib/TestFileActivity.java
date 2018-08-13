package tech.threekilogram.androidmodellib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import tech.threekilogram.depository.file.BaseFileLoader;
import tech.threekilogram.depository.file.converter.FileStringConverter;
import tech.threekilogram.depository.file.impl.DiskLruCacheLoader;
import tech.threekilogram.depository.file.impl.FileLoader;

/**
 * @author liujin
 */
public class TestFileActivity extends AppCompatActivity {

      private final String key   = "HelloFile";
      private final String value = "Hello Android Model";

      private FileLoader<String, String>         mFileLoader;
      private DiskLruCacheLoader<String, String> mDiskLruCacheLoader;
      private BaseFileLoader<String, String>     mLoader;

      private TextView mTextView;

      public static void start ( Context context ) {

            Intent starter = new Intent( context, TestFileActivity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_test_file );
            initView();

            mFileLoader = new FileLoader<>( getExternalCacheDir(), new FileStringConverter() );
            try {
                  mDiskLruCacheLoader = new DiskLruCacheLoader<>(
                      getCacheDir(),
                      1024 * 1024 * 5,
                      new FileStringConverter()
                  );
            } catch(IOException e) {
                  e.printStackTrace();
            }

            mLoader = mFileLoader;
      }

      private void initView ( ) {

            mTextView = (TextView) findViewById( R.id.textView );
      }

      public void save ( View view ) {

            mLoader.save( key, value );
            show( "save to : " + mLoader.getFile( key ) );
      }

      private void show ( String text ) {

            mTextView.setText( text );
      }

      public void load ( View view ) {

            String load = mLoader.load( key );
            show( "load : " + load );
      }

      public void delete ( View view ) {

            mLoader.remove( key );
            File file = mLoader.getFile( key );
            show( "remove : " + file + " exist : " + file.exists() );
      }

      public void change ( View view ) {

            if( mLoader == mFileLoader ) {

                  mLoader = mDiskLruCacheLoader;
            } else {

                  mLoader = mFileLoader;
            }
      }
}