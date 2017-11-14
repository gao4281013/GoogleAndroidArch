package com.example.administrator.google_android_arch.archDemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.UI.ProductAdapter;
import com.example.administrator.google_android_arch.archDemo.UI.ProductClickCallback;
import com.example.administrator.google_android_arch.archDemo.db.entity.ProductEntity;
import com.example.administrator.google_android_arch.archDemo.model.Product;
import com.example.administrator.google_android_arch.archDemo.viewModel.ProductLitViewModel;
import com.example.administrator.google_android_arch.databinding.FragmentProductListBinding;
import java.util.List;

public class ProductListFragment extends LifecycleFragment {

  public static final String TAG = "ProductListFrgment";
  private ProductAdapter mProductAdapter;
  private FragmentProductListBinding mBinding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);

    mProductAdapter = new ProductAdapter(mProductClickCallback);
    mBinding.productsList.setAdapter(mProductAdapter);
    return mBinding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    final ProductLitViewModel model = ViewModelProviders.of(this).get(ProductLitViewModel.class);

    subscribeUi(model);
  }

  private void subscribeUi(ProductLitViewModel model) {
    model.getProducts().observe(this, new Observer<List<ProductEntity>>() {
      @Override
      public void onChanged(@Nullable List<ProductEntity> productEntities) {
          if (productEntities!=null){
            mBinding.setIsLoading(false);
            mProductAdapter.setProductList(productEntities);
          }else {
            mBinding.setIsLoading(true);
          }

          mBinding.executePendingBindings();
      }
    });

  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }


  private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
    @Override
    public void onClick(Product product) {
      if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){

      }
    }
  };
}
