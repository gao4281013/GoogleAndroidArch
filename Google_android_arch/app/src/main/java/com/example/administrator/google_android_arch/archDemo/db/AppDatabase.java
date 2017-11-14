package com.example.administrator.google_android_arch.archDemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.example.administrator.google_android_arch.archDemo.db.converter.DateConverter;
import com.example.administrator.google_android_arch.archDemo.db.dao.ProductDao;
import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;

/**
 * Created by Administrator on 2017/11/14.
 */
@Database(entities = { ProductEntity.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
   static final String DATABASE_NAME = "basic_simple_db";

   public abstract ProductDao productDao();
}
