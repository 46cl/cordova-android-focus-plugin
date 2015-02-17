package fr._46cl.focus;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.os.SystemClock;
import android.os.Build;
import android.view.MotionEvent;
import android.util.DisplayMetrics;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.KeyEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;

public class Focus extends CordovaPlugin {

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
            super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("focus".equals(action)) {

            // Get device pixel density
            DisplayMetrics metrics = this.cordova.getActivity().getApplicationContext().getResources().getDisplayMetrics();
            float density = metrics.density;
            if (android.os.Build.VERSION.SDK_INT < 19) {
              // Backward compatibility for API level < 19
              density = 1;
            }

            // Get bounding positions of target element
            JSONObject rect = args.getJSONObject(0);
            float left = (density *  rect.getInt("left"));
            float top = (density * rect.getInt("top"));
            float right = (density * rect.getInt("right"));
            float bottom = (density * rect.getInt("bottom"));

            // Compute its center
            final Integer centerLeft = (int) ((right + left) / 2);
            final Integer centerTop = (int) ((top + bottom) / 2);
            // Emulate click
            cordova.getActivity().runOnUiThread(new Runnable() {
              public void run() {
                final long uMillis = SystemClock.uptimeMillis();
                webView.dispatchTouchEvent(MotionEvent.obtain(uMillis, uMillis, MotionEvent.ACTION_DOWN, centerLeft, centerTop, 0));
                webView.dispatchTouchEvent(MotionEvent.obtain(uMillis, uMillis, MotionEvent.ACTION_UP, centerLeft, centerTop, 0));
              }
            });
            return true;
        }
        return false;
    }
}

