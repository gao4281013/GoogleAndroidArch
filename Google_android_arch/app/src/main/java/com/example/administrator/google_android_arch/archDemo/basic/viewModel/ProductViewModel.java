package com.example.administrator.google_android_arch.archDemo.basic.viewModel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.example.administrator.google_android_arch.archDemo.basic.db.DatabaseCreator;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.CommentEntity;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.ProductEntity;
import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ProductViewModel extends AndroidViewModel {

  private static final MutableLiveData ABSENT = new MutableLiveData();
  {
    ABSENT.setValue(null);
  }

  private final LiveData<ProductEntity> mObservableProducts;

  private final LiveData<List<CommentEntity>> mObservableComment;

  public ObservableField<ProductEntity> product =  new ObservableField<>();

  private final int mProductId;
  public ProductViewModel(@NonNull Application application,final int productId) {
    super(application);
    mProductId = productId;

    final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

     mObservableComment = Transformations.switchMap(databaseCreator.isDatabaseCreated(), new Function<Boolean, LiveData<List<CommentEntity>>>() {
       @Override
       public LiveData<List<CommentEntity>> apply(Boolean isDbCreated) {
         if (!isDbCreated){
           return ABSENT;
         }else {
           return databaseCreator.getDatabase().commentDao().loadComments(mProductId);
         }
       }
     });

     mObservableProducts = Transformations.switchMap(databaseCreator.isDatabaseCreated(), new Function<Boolean, LiveData<ProductEntity>>() {
       @Override
       public LiveData<ProductEntity> apply(Boolean input) {
         if (!input){
           return ABSENT;
         }else {
           return databaseCreator.getDatabase().productDao().loadProduct(mProductId);
         }
       }
     });

     databaseCreator.createDb(this.getApplication());
  }

  public LiveData<List<CommentEntity>> getComments(){
    return mObservableComment;
  }

  public LiveData<ProductEntity> getObservableProduct(){
    return mObservableProducts;
  }

  public void setProduct(ProductEntity product){
    this.product.set(product);
  }

  public static class Factory extends ViewModelProvider.NewInstanceFactory{
    private final Application mApplication;

    private final int mProductId;
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      return (T) new ProductViewModel(mApplication,mProductId);
    }

    public Factory(@NonNull Application application, int productId) {
         mApplication = application;
         mProductId = productId;
    }
  }
}
