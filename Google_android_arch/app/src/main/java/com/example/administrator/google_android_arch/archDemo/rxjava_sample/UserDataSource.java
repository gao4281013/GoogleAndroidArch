package com.example.administrator.google_android_arch.archDemo.rxjava_sample;

import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.User;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface UserDataSource {

  Flowable<User> getUser();

  void insertOrUpdateUser(User user);

  void deleteAllUsers();

}
