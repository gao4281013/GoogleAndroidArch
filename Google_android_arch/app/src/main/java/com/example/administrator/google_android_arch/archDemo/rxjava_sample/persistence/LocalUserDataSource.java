package com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence;

import com.example.administrator.google_android_arch.archDemo.rxjava_sample.UserDataSource;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/11/15.
 */

public class LocalUserDataSource implements UserDataSource{

  private final UserDao mUserDao;

  public LocalUserDataSource(UserDao userDao) {
    mUserDao = userDao;
  }

  @Override
  public Flowable<User> getUser() {
    return mUserDao.getUser();
  }

  @Override
  public void insertOrUpdateUser(User user) {
       mUserDao.insertUser(user);
  }

  @Override
  public void deleteAllUsers() {
      mUserDao.deleteAllUsers();
  }
}
