package catchway.sree.dubaipay;


import android.app.Activity;
import android.app.ProgressDialog;

public class Utils {


public static ProgressDialog getProgressDialog(Activity mActivity, String message) {
        ProgressDialog mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;
    }
}
