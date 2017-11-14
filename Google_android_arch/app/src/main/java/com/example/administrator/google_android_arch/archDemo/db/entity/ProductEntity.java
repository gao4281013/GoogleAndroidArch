package com.example.administrator.google_android_arch.archDemo.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.example.administrator.google_android_arch.archDemo.model.Product;

/**
 * Created by Administrator on 2017/11/14.
 */
@Entity(tableName = "products")
public class ProductEntity implements Product{
  @PrimaryKey
  private int id;
  private String name;
  private String description;
  private int price;

  @Override
  public int getId() {
    return 0;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public int getPrice() {
    return 0;
  }


}
