package com.example.administrator.google_android_arch.archDemo.db.converter;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DateConverter {
  @TypeConverter
  public static Date toDate(Long timestamp){
    return timestamp ==null?null:new Date(timestamp);
  }

  @TypeConverter
  public static Long toTimestamp(Date date){
    return date == null?null:date.getTime();
  }

}
