package com.epsilon.models.webservice;

import com.epsilon.models.webservice.json.CheckUsernameExistRequestJSON;
import com.epsilon.models.webservice.json.CheckUsernameExistResultJSON;
import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.models.webservice.json.LoginResultJSON;
import com.epsilon.models.webservice.json.RegisterRequestJSON;
import com.epsilon.models.webservice.json.RegisterResultJSON;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface EpsilonUserService {

    @POST("/learners/sessions")
    Call<LoginResultJSON> login(@Body LoginRequestJSON loginRequestJSON);

    @POST("/learners")
    Call<RegisterResultJSON> register(@Body RegisterRequestJSON registerRequestJSON);

    @POST("/learners/check")
    Call<CheckUsernameExistResultJSON> checkUserNameExist(@Body CheckUsernameExistRequestJSON
                                                                  checkUsernameExistRequestJSON);


}
