package com.example.administrator.google_android_arch.archDemo.db;

import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DatabaseInitUtil {

  private static final String[] FIRST = new String[]{
      "Spethen Curry","Larben James","Kobe Bryant","Kevin Durent","Tim Denken"};

  private static final String[] SECOND = new String[]{
      "RAUSSA WESTBROOK","PAUL JUDGE","CRIES PUAL","Kevin Ering","JAMES HARDEN"};
  private static final String[] DESCRIPTION = new String[]{
      "is finally here", "is recommended by Stan S. Stanman",
      "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
  private static final String[] COMMENTS = new String[]{
      "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6",
  };


  static void initializeDb(AppDatabase db){
    List<ProductEntity> products = new ArrayList<>(FIRST.length * SECOND.length);

    generateData(products);
  }

  private static void generateData(List<ProductEntity> products) {
    Random rnd = new Random();
    for (int i = 0; i < FIRST.length; i++) {
      for (int i1 = 0; i1 < SECOND.length; i1++) {
           ProductEntity productEntity = new ProductEntity();
           productEntity.setName(FIRST[i]+" "+SECOND[i1]);
           productEntity.setDescription(productEntity.getName()+" "+DESCRIPTION[i1]);
           productEntity.setPrice(rnd.nextInt(240));
           productEntity.setId(FIRST.length*i+i1+1);
           products.add(productEntity);
      }
    }
  }

  private static void insertData(AppDatabase db,List<ProductEntity> products){
    db.beginTransaction();
    try {
      db.productDao().insertAll(products);
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }
}
