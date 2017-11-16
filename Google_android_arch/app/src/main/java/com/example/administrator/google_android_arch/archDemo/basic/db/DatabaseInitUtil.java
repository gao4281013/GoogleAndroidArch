package com.example.administrator.google_android_arch.archDemo.basic.db;

import android.util.Log;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.CommentEntity;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.ProductEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    List<CommentEntity> commentEntities = new ArrayList<>();

    generateData(products,commentEntities);

    insertData(db,products,commentEntities);
  }

  private static void generateData(List<ProductEntity> products,
      List<CommentEntity> commentEntities) {
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

    for (ProductEntity product : products) {
        int commentsNumber = rnd.nextInt(5)+1;
      for (int i = 0; i < commentsNumber; i++) {
        CommentEntity comment = new CommentEntity();
        comment.setProductId(product.getId());
        comment.setText(COMMENTS[i]+ " for "+product.getName());
        comment.setPostedAt(new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis
            (commentsNumber-i)+TimeUnit.HOURS.toMillis(i)));
        commentEntities.add(comment);
      }
    }
  }

  private static void insertData(AppDatabase db, List<ProductEntity> products,
      List<CommentEntity> commentEntities){
    Log.d("GAVIN",products.toString()+"----"+commentEntities.toString());
    db.beginTransaction();
    try {
      db.productDao().insertAll(products);
      db.commentDao().insertAll(commentEntities);
      db.setTransactionSuccessful();
    } finally {
      db.endTransaction();
    }
  }
}
