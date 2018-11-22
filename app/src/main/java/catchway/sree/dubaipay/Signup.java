package catchway.sree.dubaipay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import catchway.sree.dubaipay.api_pojos.LoginResponse;
import catchway.sree.dubaipay.dashboard.Dashboard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends Activity {

    @BindView(R.id.signup_edt_username)
    EditText username;

    @BindView(R.id.signup_edt_email)
    EditText email;

    @BindView(R.id.signup_edt_mobile)
    EditText mobile;

    @BindView(R.id.signup_edt_password)
    EditText password;

    @BindView(R.id.signup_edt_city)
    EditText city;


    ProgressDialog mProgressDialog;
    ApiInterface apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mProgressDialog = Utils.getProgressDialog(this, "Please Wait...");
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void register(View view) {
        mProgressDialog.show();
        Call<LoginResponse> signup = apiService.signup(username.getText().toString().trim(),
                email.getText().toString().trim(),
                mobile.getText().toString().trim(),
                password.getText().toString().trim(),
                city.getText().toString().trim());
        signup.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                mProgressDialog.dismiss();
                AlertDialog.Builder mDialog = new AlertDialog.Builder(Signup.this);
                mDialog.setTitle(getResources().getString(R.string.app_name));
                mDialog.setMessage(response.body().getMessage());
                mDialog.setCancelable(false);
                mDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!response.body().getError()){
                            dialogInterface.dismiss();
                            onBackPressed();
                        }
                        else {
                            dialogInterface.dismiss();
                        }
                    }
                });
                mDialog.show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
