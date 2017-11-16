package com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Administrator on 2017/11/16.
 */
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

  private static volatile UserDatabase SINSTANCE;

  public abstract UserDao userDao();

  public static UserDatabase getInstance(Context context){
    if (SINSTANCE == null){
     synchronized (UserDatabase.class){
       if (SINSTANCE == null){
         SINSTANCE = Room.databaseBuilder(context,UserDatabase.class,
             "sample.db").build();
       }
     }
    }
    return SINSTANCE;
  }
}
