package com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/11/15.
 */
@Entity(tableName = "users")
public class User {
  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "userid")
  private String mId;

  @ColumnInfo(name = "username")
  private String mUserName;

  @Ignore
  public User(String userName) {
    mUserName = userName;
  }

  public User(String id, String userName) {
    mId = id;
    mUserName = userName;
  }

  public String getId() {
    return mId;
  }

  public String getUserName() {
    return mUserName;
  }
}
