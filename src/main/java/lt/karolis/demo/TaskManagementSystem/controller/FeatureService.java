package lt.karolis.demo.TaskManagementSystem.controller;

import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;

@Component
public class FeatureService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8081/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    FeatureTogglerApi featureTogglerApi = retrofit.create(FeatureTogglerApi.class);

    public FeatureService() {
    }

    public String getFeatures() {
//        Call<Object> features = featureTooglerApi.getFeatures();
//        String credentials = "admin:admin";
//        System.out.println(".request " + features.request());
////                .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8)));
//        features.enqueue(new Callback<>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                System.out.println("Woohoo " + response + " \n body=" + response.body() + "\n req: " + call.request());
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable throwable) {
//                System.out.println("Failed onFailure " + throwable.getMessage());
//            }
//        });

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<String>(headers);
//        String body = .getBody();
        return restTemplate.exchange("http://localhost:8081/features", HttpMethod.GET, entity, String.class).getBody();
    }
}
