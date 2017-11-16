package com.example.administrator.google_android_arch.archDemo.basic.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.example.administrator.google_android_arch.archDemo.basic.db.converter.DateConverter;
import com.example.administrator.google_android_arch.archDemo.basic.db.dao.CommentDao;
import com.example.administrator.google_android_arch.archDemo.basic.db.dao.ProductDao;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.CommentEntity;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.ProductEntity;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.User;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.UserDao;

/**
 * Created by Administrator on 2017/11/14.
 */
@Database(entities = { ProductEntity.class, CommentEntity.class, User.class},version = 1, exportSchema =
    false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
   static final String DATABASE_NAME = "google_android_arch_db";

   public abstract ProductDao productDao();

   public abstract CommentDao commentDao();

   public abstract UserDao userDao();

}
