package com.example.administrator.google_android_arch.archDemo.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import com.example.administrator.google_android_arch.archDemo.db.DatabaseCreator;
import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ProductLitViewModel extends AndroidViewModel {

  private static final MutableLiveData ABSENT = new MutableLiveData();
  {
    ABSENT.setValue(null);
  }
  private final LiveData<List<ProductEntity>> mObservableProducts;



  public ProductLitViewModel(Application application) {
    super(application);

    final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

    LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();

    mObservableProducts = Transformations.switchMap(databaseCreated,
        new Function<Boolean, LiveData<List<ProductEntity>>>() {
      @Override
      public LiveData<List<ProductEntity>> apply(Boolean isDbCreated) {
        if (Boolean.TRUE.equals(isDbCreated)){
          return ABSENT;
        }else {
          return databaseCreator.getDatabase().productDao().loadAllProducts();
        }
      }
    });
    databaseCreator.createDb(this.getApplication());
  }

  public LiveData<List<ProductEntity>> getProducts(){
    return mObservableProducts;
  }
}
