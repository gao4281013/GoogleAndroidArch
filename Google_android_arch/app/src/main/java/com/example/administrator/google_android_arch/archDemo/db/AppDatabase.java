package com.example.administrator.google_android_arch.archDemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.example.administrator.google_android_arch.archDemo.db.converter.DateConverter;
import com.example.administrator.google_android_arch.archDemo.db.dao.CommentDao;
import com.example.administrator.google_android_arch.archDemo.db.dao.ProductDao;
import com.example.administrator.google_android_arch.archDemo.db.entity.CommentEntity;
import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;

/**
 * Created by Administrator on 2017/11/14.
 */
@Database(entities = { ProductEntity.class, CommentEntity.class},version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
   static final String DATABASE_NAME = "google_android_arch_db";

   public abstract ProductDao productDao();

   public abstract CommentDao commentDao();
}
