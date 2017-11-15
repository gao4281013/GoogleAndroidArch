package com.example.administrator.google_android_arch.archDemo.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface Comment {
  int getId();
  int getProductId();
  String getText();
  Date getPostedAt();
}
