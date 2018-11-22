package catchway.sree.dubaipay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import catchway.sree.dubaipay.api_pojos.LoginResponse;
import catchway.sree.dubaipay.dashboard.Dashboard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @BindView(R.id.login_edt_username)
    EditText username;

    @BindView(R.id.login_edt_password)
    EditText password;


    ProgressDialog mProgressDialog;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressDialog = Utils.getProgressDialog(this, "Please Wait...");
        apiService = ApiClient.getClient().create(ApiInterface.class);

    }

    public void login(View view) {
        if (validate()) {
            mProgressDialog.show();
            Call<LoginResponse> login = apiService.login(username.getText().toString().trim(), password.getText().toString().trim());
            login.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    if (!response.body().getError()){
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public boolean validate() {
        boolean isValid = true;
        if (username.getText().toString().trim().length() == 0) {
            username.setError("Enter Username");
            isValid = false;
        }
        if (password.getText().toString().trim().length() == 0) {
            password.setError("Enter Password");
            isValid = false;
        }
        return isValid;
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }
}
