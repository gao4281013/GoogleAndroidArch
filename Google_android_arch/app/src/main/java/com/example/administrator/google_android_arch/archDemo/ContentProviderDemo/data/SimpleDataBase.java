package com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

/**
 * Created by Administrator on 2017/11/16.
 */
@Database(entities = {Cheese.class},version = 1,exportSchema = false)
public abstract class SimpleDataBase extends RoomDatabase{

  public abstract CheeseDao cheeseDao();

  private static SimpleDataBase SINSTANCE;

  public static synchronized SimpleDataBase getInstance(Context context){
    if (SINSTANCE == null){
      SINSTANCE = Room.databaseBuilder(context,SimpleDataBase.class,"provider_data.db").build();
      SINSTANCE.inititalData();
    }
    return SINSTANCE;
  }

  @VisibleForTesting
  public static void swithToInMomery(Context context){
    SINSTANCE = Room.inMemoryDatabaseBuilder(context,SimpleDataBase.class).build();
  }

  private void inititalData() {
    if (cheeseDao().getCount() ==0){
        Cheese cheese = new Cheese();
        beginTransaction();
        try {
          for (int i = 0; i < Cheese.CHEESES.length; i++) {
               cheese.name = Cheese.CHEESES[i];
               cheeseDao().insert(cheese);
          }
          setTransactionSuccessful();
        }finally {
          endTransaction();
        }
    }
  }
}
