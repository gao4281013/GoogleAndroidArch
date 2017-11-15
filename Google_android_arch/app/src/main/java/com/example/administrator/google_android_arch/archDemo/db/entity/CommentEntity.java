package com.example.administrator.google_android_arch.archDemo.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import com.example.administrator.google_android_arch.archDemo.model.Comment;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/14.
 */
@Entity(tableName = "comments" ,foreignKeys = {
    @ForeignKey(entity = ProductEntity.class,parentColumns = "id",
    childColumns = "productId",
    onDelete = ForeignKey.CASCADE)},indices ={
    @Index(value = "productId")})
public class CommentEntity implements Comment {
  @PrimaryKey(autoGenerate = true)
  private int id;
  private int productId;
  private String text;
  private Date postedAt;

  public void setId(int id) {
    this.id = id;
  }

  public void setPostedAt(Date postedAt) {
    this.postedAt = postedAt;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public int getProductId() {
    return productId;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public Date getPostedAt() {
    return postedAt;
  }

  public CommentEntity() {
  }

  public CommentEntity(Comment comment) {
     id = comment.getId();
     productId = comment.getProductId();
     text = comment.getText();
     postedAt = comment.getPostedAt();
  }
}
