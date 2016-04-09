package com.epsilon.models.webservice;

import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.models.webservice.json.LoginResultJSON;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface EpsilonUserService {

    @POST("/learners/sessions")
    Call<LoginResultJSON> login(@Body LoginRequestJSON loginRequestJSON);

}
