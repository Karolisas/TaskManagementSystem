package lt.karolis.demo.TaskManagementSystem.controller;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface FeatureTogglerApi {

    @GET("/features")
    @Headers("Content-Type: application/json")
    Call<Object> getFeatures();

}
