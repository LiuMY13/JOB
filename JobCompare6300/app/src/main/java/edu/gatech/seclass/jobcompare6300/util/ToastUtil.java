package edu.gatech.seclass.jobcompare6300.util;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
public class ToastUtil {
    public static Toast mToast;
    public static void showMsg(Activity activity, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
/**
 * example
 * mBtnLogout.setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View v) {
 *                 String Logout = "Logout";
 *                 ToastUtil.showMsg(MainActivity.this,Logout);
 *             }
 *         });
 */