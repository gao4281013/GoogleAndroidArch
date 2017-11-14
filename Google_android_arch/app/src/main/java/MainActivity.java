import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.administrator.google_android_arch.R;
import com.example.administrator.google_android_arch.archDemo.ProductListFragment;
import com.example.administrator.google_android_arch.archDemo.model.Product;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MainActivity extends LifecycleActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null){
      ProductListFragment fragment = new ProductListFragment();
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragment_container,fragment);
    }
  }

  public void show(Product product){

  }
}
