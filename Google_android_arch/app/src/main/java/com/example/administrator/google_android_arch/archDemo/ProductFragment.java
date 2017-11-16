package com.example.administrator.google_android_arch.archDemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.basic.UI.CommentAdapter;
import com.example.administrator.google_android_arch.archDemo.basic.UI.CommentClickCallback;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.CommentEntity;
import com.example.administrator.google_android_arch.archDemo.basic.db.entity.ProductEntity;
import com.example.administrator.google_android_arch.archDemo.basic.model.Comment;
import com.example.administrator.google_android_arch.archDemo.basic.viewModel.ProductViewModel;
import com.example.administrator.google_android_arch.databinding.FragmentProductBinding;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

  private static final String KEY_PRODUCT_ID = "product_id";

  private FragmentProductBinding mBinding;

  private CommentAdapter mCommentAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);

    mCommentAdapter = new CommentAdapter(mCommentClickCallback);
    mBinding.commentList.setAdapter(mCommentAdapter);

    return mBinding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ProductViewModel.Factory factory = new ProductViewModel.Factory(getActivity().getApplication
        (),getArguments().getInt(KEY_PRODUCT_ID));

    final ProductViewModel productViewModel = ViewModelProviders.of(this,factory).get
        (ProductViewModel.class);
    subscribeToModel(productViewModel);
  }

  private void subscribeToModel(final ProductViewModel productViewModel) {
     productViewModel.getObservableProduct().observe(this, new Observer<ProductEntity>() {
       @Override
       public void onChanged(@Nullable ProductEntity productEntity) {
         productViewModel.setProduct(productEntity);
       }
     });


     productViewModel.getComments().observe(this, new Observer<List<CommentEntity>>() {
       @Override
       public void onChanged(@Nullable List<CommentEntity> commentEntities) {
         if (commentEntities !=null){
           mBinding.setIsLoading(false);
           mCommentAdapter.setCommentList(commentEntities);
         }else {
           mBinding.setIsLoading(true);
         }
       }
     });
  }

  private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
    @Override
    public void onClick(Comment comment) {

    }
  };


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }
  public static ProductFragment forProduct(int productId){
    ProductFragment fragment = new ProductFragment();
    Bundle args = new Bundle();
    args.putInt(KEY_PRODUCT_ID,productId);
    fragment.setArguments(args);
    return fragment;
  }
}
