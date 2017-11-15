package com.example.administrator.google_android_arch.archDemo.UI;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.model.Comment;
import com.example.administrator.google_android_arch.databinding.CommentItemBinding;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/11/14.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
  List<? extends Comment> mComments;

  @Nullable
  private final CommentClickCallback mCommentClickCallback;

  public CommentAdapter(CommentClickCallback commentClickCallback) {
    mCommentClickCallback = commentClickCallback;
  }

  public void setCommentList(final List<? extends Comment> comments){
       if (mComments == null){
         mComments = comments;
         notifyItemRangeChanged(0,mComments.size());
       }else {
         DiffUtil.DiffResult  result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
           @Override
           public int getOldListSize() {
             return mComments.size();
           }

           @Override
           public int getNewListSize() {
             return mComments.size();
           }

           @Override
           public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
             return mComments.get(oldItemPosition).getId() == mComments.get(newItemPosition)
                 .getId();
           }

           @RequiresApi(api = Build.VERSION_CODES.KITKAT)
           @Override
           public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
             Comment oldComment = mComments.get(oldItemPosition);
             Comment newComment = mComments.get(newItemPosition);
             return oldComment.getId() == oldComment.getId() && Objects.equals(oldComment
                 .getText(),newComment.getText()) && Objects.equals(oldComment
                 .getPostedAt(),newComment.getPostedAt()) && oldComment.getProductId() == newComment
                 .getProductId();
           }
         }) ;
         mComments = comments;
         result.dispatchUpdatesTo(this);
       }
  }


  @Override
  public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CommentItemBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item,
            parent,false);
    binding.setCallback(mCommentClickCallback);
    return new CommentViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(CommentViewHolder holder, int position) {
       holder.mBinding.setComment(mComments.get(position));
       holder.mBinding.executePendingBindings();
  }

  @Override
  public int getItemCount() {
    return mComments ==null?0:mComments.size();
  }

  public class CommentViewHolder extends RecyclerView.ViewHolder{
    final CommentItemBinding mBinding;
    public CommentViewHolder(CommentItemBinding itemBinding) {
      super(itemBinding.getRoot());
      this.mBinding = itemBinding;
    }
  }
}
