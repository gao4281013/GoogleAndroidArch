package com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/11/15.
 */
@Dao
public interface UserDao {

  @Query("SELECT * FROM Users LIMIT 1 ")
  Flowable<User> getUser();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUser(User user);

  @Query("DELETE FROM Users")
  void deleteAllUsers();
}
