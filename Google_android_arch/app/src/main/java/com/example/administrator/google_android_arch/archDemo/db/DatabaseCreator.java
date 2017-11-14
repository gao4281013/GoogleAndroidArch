package com.example.administrator.google_android_arch.archDemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.administrator.google_android_arch.archDemo.db.AppDatabase.DATABASE_NAME;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DatabaseCreator {

  private static DatabaseCreator sInstance;

  private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

  private AppDatabase mDb;

  private final AtomicBoolean mInitializing = new AtomicBoolean(true);

  // For Singleton instantiation
  private static final Object LOCK = new Object();
  public synchronized static DatabaseCreator getInstance(Context context){
    if (sInstance == null){
      synchronized (LOCK){
        if (sInstance == null){
          sInstance = new DatabaseCreator();
        }
      }
    }
    return sInstance;
  }

  public LiveData<Boolean> isDatabaseCreated(){
    return mIsDatabaseCreated;
  }

  @Nullable
  public AppDatabase getDatabase(){
    return mDb;
  }


  public void createDb(Context context){
    Log.d("DatabaseCreator","Creating DB from" + Thread.currentThread().getName());
    if (!mInitializing.compareAndSet(true, false)) {
      return;
    }

    mIsDatabaseCreated.setValue(false);
    new AsyncTask<Context,Void,Void>(){
      @Override
      protected Void doInBackground(Context... params) {
        Context context = params[0].getApplicationContext();

        // Reset the database to have new data on every run.
        context.deleteDatabase(DATABASE_NAME);

        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
            DATABASE_NAME).build();

        addDelay();
        DatabaseInitUtil.initializeDb(db);
        mDb = db;
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mIsDatabaseCreated.setValue(true);
      }
    }.execute(context.getApplicationContext());
  }

  private void addDelay() {
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
