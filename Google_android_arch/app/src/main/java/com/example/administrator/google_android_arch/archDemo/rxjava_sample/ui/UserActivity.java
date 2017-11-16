package com.example.administrator.google_android_arch.archDemo.rxjava_sample.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.rxjava_sample.Injection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends LifecycleActivity {
  private static final String TAG = UserActivity.class.getSimpleName();

  private EditText input_et;
  private TextView input_tv;
  private Button update_btn;

  private ViewModelFactory mViewModelFactory;
  private UserViewModel mUserViewModel;

  private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);

    input_et = findViewById(R.id.input_value_et);
    input_tv = findViewById(R.id.text_value);
    update_btn = findViewById(R.id.add_user);

    mViewModelFactory = Injection.provideViewModelFactory(this);
    mUserViewModel = ViewModelProviders.of(this,mViewModelFactory).get(UserViewModel.class);
    update_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updateUserName();
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mCompositeDisposable.add(mUserViewModel.getUserName()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(username-> input_tv.setText(username),
            throwable -> Log.e(TAG,"unable to update username",throwable)));

  }

  @Override
  protected void onStop() {
    super.onStop();
    mCompositeDisposable.clear();
  }

  private void updateUserName() {
    String username = input_et.getText().toString();
    update_btn.setEnabled(false);
    mCompositeDisposable.add(mUserViewModel.updateUserName(username)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> update_btn.setEnabled(true),
                   throwable -> Log.e(TAG,"unable to update username",throwable)));

        }
  }
