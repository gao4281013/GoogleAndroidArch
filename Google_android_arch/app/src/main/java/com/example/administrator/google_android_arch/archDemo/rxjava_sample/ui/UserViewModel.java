package com.example.administrator.google_android_arch.archDemo.rxjava_sample.ui;

import android.arch.lifecycle.ViewModel;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.UserDataSource;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * Created by Administrator on 2017/11/15.
 */

public class UserViewModel extends ViewModel {

  private final UserDataSource mDataSource;

  private User mUser;

  public UserViewModel(UserDataSource dataSource) {
    mDataSource = dataSource;
  }

  public Flowable<String> getUserName(){
    return mDataSource.getUser().map(user ->{
      mUser = user;
      return user.getUserName();
    });
  }

  public Completable updateUserName(final String userName){
    return new CompletableFromAction(() ->{
        mUser = mUser == null?new User(userName)
            :new User(mUser.getId(),userName);

        mDataSource.insertOrUpdateUser(mUser);
    });
  }
}
