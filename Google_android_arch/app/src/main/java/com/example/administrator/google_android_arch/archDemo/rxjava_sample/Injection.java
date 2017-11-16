package com.example.administrator.google_android_arch.archDemo.rxjava_sample;

import android.content.Context;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.LocalUserDataSource;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.UserDatabase;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.ui.ViewModelFactory;

/**
 * Created by Administrator on 2017/11/15.
 */

public class Injection {

  public static UserDataSource provideUserDataSource(Context context){
    UserDatabase userDatabase = UserDatabase.getInstance(context);
    return new LocalUserDataSource(userDatabase.userDao());
  }
  public static ViewModelFactory provideViewModelFactory(Context context){
    UserDataSource userDataSource = provideUserDataSource(context);
    return new ViewModelFactory(userDataSource);
  }
}
