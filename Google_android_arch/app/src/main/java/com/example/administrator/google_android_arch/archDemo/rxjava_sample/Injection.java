package com.example.administrator.google_android_arch.archDemo.rxjava_sample;

import android.content.Context;
import com.example.administrator.google_android_arch.archDemo.db.AppDatabase;
import com.example.administrator.google_android_arch.archDemo.db.DatabaseCreator;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.persistence.LocalUserDataSource;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.ui.ViewModelFactory;

/**
 * Created by Administrator on 2017/11/15.
 */

public class Injection {

  public static UserDataSource provideUserDataSource(Context context){
    AppDatabase database = DatabaseCreator.getInstance(context).getDatabase();
    return new LocalUserDataSource(database.userDao());
  }
  public static ViewModelFactory provideViewModelFactory(Context context){
    UserDataSource userDataSource = provideUserDataSource(context);
    return new ViewModelFactory(userDataSource);
  }
}
