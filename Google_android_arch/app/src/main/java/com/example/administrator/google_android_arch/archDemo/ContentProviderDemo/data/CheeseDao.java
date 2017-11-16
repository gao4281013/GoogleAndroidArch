package com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

/**
 * Created by Administrator on 2017/11/16.
 */
@Dao public interface CheeseDao {

  //获取cheese 条数
  @Query("SELECT COUNT(*) FROM " + Cheese.TABLE_NAME)
  int getCount();

  @Insert
  long insert(Cheese cheese);

  @Insert
  long[] insertAll(Cheese[] cheeses);

  @Query("SELECT * FROM " + Cheese.TABLE_NAME)
  Cursor selectAll();

  @Query("SELECT * FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
  Cursor selectById(long id);

  @Query("DELETE FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
  int deleteById(long id);

  @Update
  int update(Cheese cheese);
}
