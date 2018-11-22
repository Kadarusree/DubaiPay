package catchway.sree.dubaipay.api_pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("username")
@Expose
private String username;
@SerializedName("email")
@Expose
private String email;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("city")
@Expose
private String city;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

}