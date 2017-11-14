package com.example.administrator.google_android_arch.archDemo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */
@Dao
public interface ProductDao {
  @Query("SELECT * FROM products")
  LiveData<List<ProductEntity>> loadAllProducts();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<ProductEntity> products);

  @Query("select * from products where id =  :productId")
  LiveData<ProductEntity> loadProduct(int productId);

  @Query("select * from products where id =  :productID")
   ProductEntity loadProductSync(int productId);
}
