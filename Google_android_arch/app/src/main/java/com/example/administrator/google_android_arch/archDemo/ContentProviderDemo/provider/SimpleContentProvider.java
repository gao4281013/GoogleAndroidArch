package com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data.Cheese;
import com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data.CheeseDao;
import com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.data.SimpleDataBase;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/16.
 */

public class SimpleContentProvider extends ContentProvider {

  public static final String AUTHORITY =
      "com.example.administrator.google_android_arch.archDemo.ContentProviderDemo.provider";
  /** The URI for the Cheese table. */
  public static final Uri URI_CHEESE =
      Uri.parse("content://" + AUTHORITY + "/" + Cheese.TABLE_NAME);

  private static final int CODE_CHEESE_DIR = 1;

  private static final int CODE_CHEESE_ITEM = 2;

  private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

  static {
    MATCHER.addURI(AUTHORITY, Cheese.TABLE_NAME, CODE_CHEESE_DIR);
    MATCHER.addURI(AUTHORITY, Cheese.TABLE_NAME + "/*", CODE_CHEESE_ITEM);
  }

  @Override
  public boolean onCreate() {
    return true;
  }

  @Nullable
  @Override
  public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
      @Nullable String[] selectionArgs, @Nullable String sortOrder) {
    final int code = MATCHER.match(uri);
    if (code == CODE_CHEESE_ITEM || code == CODE_CHEESE_ITEM) {
      final Context context = getContext();
      if (context == null) {
        return null;
      }
      CheeseDao cheeseDao = SimpleDataBase.getInstance(context).cheeseDao();
      final Cursor cursor;
      if (code == CODE_CHEESE_DIR) {
        cursor = cheeseDao.selectAll();
      } else {
        cursor = cheeseDao.selectById(ContentUris.parseId(uri));
      }
      cursor.setNotificationUri(context.getContentResolver(), uri);
      return cursor;
    } else {
      throw new IllegalArgumentException("unknown uri" + uri);
    }
  }

  @Nullable
  @Override
  public String getType(@NonNull Uri uri) {
    switch (MATCHER.match(uri)) {
      case CODE_CHEESE_DIR:
        return "vnd.android.cursor.dir/" + AUTHORITY + "." + Cheese.TABLE_NAME;
      case CODE_CHEESE_ITEM:
        return "vnd.android.cursor.item/" + AUTHORITY + "." + Cheese.TABLE_NAME;
      default:
        throw new IllegalArgumentException("unknown uri");
    }
  }

  @Nullable
  @Override
  public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
    switch (MATCHER.match(uri)) {
      case CODE_CHEESE_DIR:
        final Context context = getContext();
        if (context == null) {
          return null;
        }
        final long id = SimpleDataBase.getInstance(context)
            .cheeseDao()
            .insert(Cheese.fromContentValues(values));
        context.getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
      case CODE_CHEESE_ITEM:
        throw new IllegalArgumentException("unknown uri, can not insert with ID" + uri);
      default:
        throw new IllegalArgumentException("unknown uri" + uri);
    }
  }

  @Override
  public int delete(@NonNull Uri uri, @Nullable String selection,
      @Nullable String[] selectionArgs) {
    switch (MATCHER.match(uri)) {
      case CODE_CHEESE_ITEM:
        final Context context = getContext();
        if (context == null) {
          return 0;
        }
        final int id =
            SimpleDataBase.getInstance(context).cheeseDao().deleteById(ContentUris.parseId(uri));
        context.getContentResolver().notifyChange(uri, null);
        return id;
      case CODE_CHEESE_DIR:
        throw new IllegalArgumentException("Invalid URI, can not delete with ID" + uri);
      default:
        throw new IllegalArgumentException("unknown uri" + uri);
    }
  }

  @Override
  public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
      @Nullable String[] selectionArgs) {
    switch (MATCHER.match(uri)) {
      case CODE_CHEESE_ITEM:
        final Context context = getContext();
        if (context == null) {
          return 0;
        }

        final Cheese cheese = Cheese.fromContentValues(values);
        cheese.id = ContentUris.parseId(uri);
        final int id = SimpleDataBase.getInstance(context).cheeseDao().update(cheese);
        context.getContentResolver().notifyChange(uri, null);
        return id;
      case CODE_CHEESE_DIR:
        throw new IllegalArgumentException("Invalid URI, can not update with ID" + uri);
      default:
        throw new IllegalArgumentException("unknown uri" + uri);
    }
  }

  @NonNull
  @Override
  public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations)
      throws OperationApplicationException {
    final Context context = getContext();
    if (context == null) {
      return new ContentProviderResult[0];
    }

    final SimpleDataBase dataBase = SimpleDataBase.getInstance(context);
    dataBase.beginTransaction();
    try {
      final ContentProviderResult[] result = super.applyBatch(operations);
      dataBase.setTransactionSuccessful();
      return result;
    } finally {
      dataBase.endTransaction();
    }
  }

  @Override
  public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
    switch (MATCHER.match(uri)) {
      case CODE_CHEESE_DIR:
        final Context context = getContext();
        if (context == null) {
          return 0;
        }
        final SimpleDataBase dataBase = SimpleDataBase.getInstance(context);
        final Cheese[] cheeses = new Cheese[values.length];
        for (int i = 0; i < values.length; i++) {
          cheeses[i] = Cheese.fromContentValues(values[i]);
        }
        return dataBase.cheeseDao().insertAll(cheeses).length;
      case CODE_CHEESE_ITEM:
        throw new IllegalArgumentException("Invalid URI, can not update with ID" + uri);
      default:
        throw new IllegalArgumentException("unknown uri" + uri);
    }
  }
}
