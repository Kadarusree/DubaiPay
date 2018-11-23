package catchway.sree.dubaipay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void createAccount(View view) {
        startActivity(new Intent(getApplicationContext(), Signup.class));
    }
}
