package com.funworks.woof.api;

import com.funworks.woof.data.RandomDog;
import com.funworks.woof.data.RandomDogs;

import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rahulhariharan on 29/07/18.
 */

@Singleton
public class WoofApiService {

    public interface Service {

        @GET("/api/breeds/list/all")
        Observable<RandomDog> allBreeds ();

        @GET("/api/breeds/image/random")
        Observable<RandomDog> randomDog ();

        @GET("/api/breeds/image/random/{size}")
        Observable<RandomDogs> randomDogs ( @Path("size") int size);

        @GET("/api/breed/{breed}/images")
        Observable<RandomDogs> dogsByBreed (@Path("breed") String breed);

        @GET("/api/breed/{breed}/images/random")
        Observable<RandomDog> randomDogByBreed (@Path("breed") String breed);

        @GET("/api/breed/{breed}/{subbreed}/images/random")
        Observable<RandomDog> randomDogByBreed (@Path("breed") String breed, @Path("subbreed") String subbreed);
    }

    public static Service fetch() {
        return new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Service.class);
    }
}
