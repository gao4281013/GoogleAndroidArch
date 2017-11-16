package com.example.administrator.google_android_arch.archDemo.basic.UI;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.basic.model.Product;
import com.example.administrator.google_android_arch.databinding.ProductItemBinding;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
  List<? extends Product> mProductList;

  @Nullable
  private final ProductClickCallback mProductClickCallback;

  public ProductAdapter(ProductClickCallback productClickCallback) {
    mProductClickCallback = productClickCallback;
  }

  public void setProductList(final List<? extends Product> productList){
       if (mProductList == null){
         mProductList = productList;
         notifyItemRangeChanged(0,productList.size());
       }else {
         DiffUtil.DiffResult  result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
           @Override
           public int getOldListSize() {
             return mProductList.size();
           }

           @Override
           public int getNewListSize() {
             return productList.size();
           }

           @Override
           public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
             return mProductList.get(oldItemPosition).getId() == productList.get(newItemPosition)
                 .getId();
           }

           @Override
           public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
             Product oldProduct = mProductList.get(oldItemPosition);
             Product newProduct = productList.get(newItemPosition);
             return oldProduct.getId() == newProduct.getId() && Objects.equals(oldProduct
                 .getDescription(),newProduct.getDescription()) && Objects.equals(oldProduct
                 .getName(),newProduct.getName()) && oldProduct.getPrice() == newProduct.getPrice
                 ();
           }
         }) ;
         mProductList = productList;
         result.dispatchUpdatesTo(this);
       }
  }


  @Override
  public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ProductItemBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item,
            parent,false);
    binding.setCallback(mProductClickCallback);
    return new ProductViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(ProductViewHolder holder, int position) {
       holder.mBinding.setProduct(mProductList.get(position));
       holder.mBinding.executePendingBindings();
  }

  @Override
  public int getItemCount() {
    return mProductList ==null?0:mProductList.size();
  }

  public class ProductViewHolder extends RecyclerView.ViewHolder{
    final ProductItemBinding mBinding;
    public ProductViewHolder(ProductItemBinding itemBinding) {
      super(itemBinding.getRoot());
      this.mBinding = itemBinding;
    }
  }
}
