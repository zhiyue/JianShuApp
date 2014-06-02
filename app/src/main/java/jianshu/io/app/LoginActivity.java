package jianshu.io.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import jianshu.io.app.model.JianshuSession;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class LoginActivity extends SwipeBackActivity {

  private WebView mWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    CookieManager.getInstance().removeAllCookie();

    mWebView = (WebView) findViewById(R.id.login_webview);
    WebSettings settings = mWebView.getSettings();
    settings.setJavaScriptEnabled(true);
          mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
              if (url.equals("http://jianshu.io/")) {
                JianshuSession.getsInstance().validate();
                if (JianshuSession.getsInstance().isUserLogin()) {
                  setResult(RESULT_OK);
                  LoginActivity.this.finish();
                  overridePendingTransition(0, R.anim.slide_out_right);
                }
        }
        return false;
      }
    });
    mWebView.loadUrl("http://jianshu.io/sign_in");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.login, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    finish();
    overridePendingTransition(0, R.anim.slide_out_right);
  }

}
