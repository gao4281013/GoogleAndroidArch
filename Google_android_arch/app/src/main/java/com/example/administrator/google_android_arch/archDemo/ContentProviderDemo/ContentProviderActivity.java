package com.example.administrator.google_android_arch.archDemo.ContentProviderDemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data.Cheese;
import com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.provider.SimpleContentProvider;

public class ContentProviderActivity extends AppCompatActivity {

  private static final int LOADER_CHEESE = 1;

  private CheeseAdapter mCheeseAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content_provider);

    final RecyclerView recyclerView = findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    mCheeseAdapter = new CheeseAdapter();
    recyclerView.setAdapter(mCheeseAdapter);

    getSupportLoaderManager().initLoader(LOADER_CHEESE,null,mLoaderCallbacks);
  }

  private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
      switch (id){
        case LOADER_CHEESE:
          return new CursorLoader(getApplicationContext(), SimpleContentProvider.URI_CHEESE,new
              String[]{ Cheese.COLUMN_NAME},null,null,null);
          default:
            throw new IllegalArgumentException("INVILID URL");
      }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
      switch (loader.getId()){
        case LOADER_CHEESE:
            mCheeseAdapter.setCursor(data);
          break;
      }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
      switch (loader.getId()){
        case LOADER_CHEESE:
          mCheeseAdapter.setCursor(null);
          break;
      }
    }
  };


  private static class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.ViewHolder>{

    private Cursor mCursor;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         if (mCursor.moveToPosition(position)){
           holder.mTextView.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(Cheese.COLUMN_NAME)));
         }
    }

    public void setCursor(Cursor cursor) {
      mCursor = cursor;
      notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
      return mCursor == null ? 0:mCursor.getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
      TextView mTextView;
      public ViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_item,parent,
            false));
        mTextView = itemView.findViewById(android.R.id.text1);
      }
    }
  }
}
