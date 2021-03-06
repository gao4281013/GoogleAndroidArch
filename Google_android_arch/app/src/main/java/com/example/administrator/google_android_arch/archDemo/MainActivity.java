package com.example.administrator.google_android_arch.archDemo;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.basic.model.Product;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MainActivity extends LifecycleActivity {

  //private Button tranBtn;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null){
      ProductListFragment fragment = new ProductListFragment();
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragment_container,fragment, ProductListFragment.TAG).commit();
    }

    //tranBtn = findViewById(R.id.user_btn);
    //tranBtn.setOnClickListener(new View.OnClickListener() {
    //  @Override
    //  public void onClick(View v) {
    //    startActivity(new Intent(MainActivity.this, UserActivity.class));
    //  }
    //});
  }

  public void show(Product product){
    ProductFragment productFragment = ProductFragment.forProduct(product.getId());
    getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack("product")
        .replace(R.id.fragment_container,
            productFragment,null).commit();
  }
}
