package com.epsilon.models.webservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.epsilon.EpsilonApplication;
import com.epsilon.utils.Constants;
import com.epsilon.utils.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dandoh on 3/22/16.
 */
public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";


    /**
     * Generic retrofit builder
     */
    private static Retrofit.Builder builder() {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    private static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, builder(), normalHttpClient());
    }

    private static <S> S createService(Class<S> serviceClass, Retrofit.Builder builder,
                                       OkHttpClient httpClient) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


    /**
     * Normal HTTP Client - required no authentication
     *
     * @return
     */
    private static OkHttpClient normalHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Utils.log("REQUEST", request.url() + " " + request.headers().toString());
                        Request modifiedRequest = request.newBuilder()
                                .addHeader("Accept", "application/api.packageshipper.v1")
                                .build();

                        return chain.proceed(modifiedRequest);
                    }
                })
                .build();
        return okHttpClient;
    }


    /**
     * Authenticated Http Client - require logged in
     *
     * @return
     */
    private static OkHttpClient getAuthenticatedHttpClient() {
        Context context = EpsilonApplication.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        final String authToken = sharedPreferences.getString(Constants.TOKEN_TAG,
                "------Random invalid default token-----");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Utils.log("REQUEST", request.url() + " " + request.headers().toString());
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request modifiedRequest = request.newBuilder()
                                // TODO - add authorization
//                                .addHeader("token", getAccessToken())
                                .addHeader("Authorization", authToken)
                                .build();
                        return chain.proceed(modifiedRequest);
                    }
                })
                .build();

        return okHttpClient;
    }

    // Authentication service - not requiring logged in
    private static EpsilonUserService mEpsilonUserService;

    public synchronized static EpsilonUserService getEpsilonUserService() {
        if (mEpsilonUserService == null) {
            mEpsilonUserService = createService(EpsilonUserService.class);
        }

        return mEpsilonUserService;
    }


    // Main web service - requiring logged in

    private static EpsilonMainService mEpsilonMainService;

    public synchronized static EpsilonMainService getEpsilonWebService() {
        if (mEpsilonMainService == null) {
            mEpsilonMainService = createService(EpsilonMainService.class,
                    builder(), getAuthenticatedHttpClient());
        }

        return mEpsilonMainService;
    }
}
