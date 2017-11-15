package com.example.administrator.google_android_arch.archDemo.UI;

import android.view.View;

/**
 * Created by Administrator on 2017/11/15.
 */

public class BindingAdapter {
  @android.databinding.BindingAdapter("visibleGone")
  public static void showHide(View view,boolean show){
    view.setVisibility(show?View.VISIBLE:View.GONE);
  }

}
