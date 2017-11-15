package com.example.administrator.google_android_arch.archDemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.administrator.google_android_arch.archDemo.db.entity.CommentEntity;
import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */
@Dao
public interface CommentDao {

  @Query("SELECT * FROM comments where productId = :productId")
  LiveData<List<CommentEntity>> loadComments(int productId);

  @Query("SELECT * FROM comments where productId =  :productId")
  List<CommentEntity> loadCommentsSync(int productId);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<CommentEntity> products);

}
