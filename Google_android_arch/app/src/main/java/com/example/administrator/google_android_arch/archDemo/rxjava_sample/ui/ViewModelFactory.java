package com.example.administrator.google_android_arch.archDemo.rxjava_sample.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.UserDataSource;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
  private final UserDataSource mDataSource;

  public ViewModelFactory(UserDataSource dataSource) {
    mDataSource = dataSource;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(UserViewModel.class)){
      return (T) new UserViewModel(mDataSource);
    }
    throw  new IllegalArgumentException("unknown viewModel class");
  }
}
