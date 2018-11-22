package catchway.sree.dubaipay;

import catchway.sree.dubaipay.api_pojos.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("Api.php?apicall=login")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);
 

}