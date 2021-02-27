package com.simanjit.moviesearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface SearchMovieApi {
    @GET(".")
    Call<MovieResponse> getMovieDetail( @Query("t") String title, @Query("apikey") String apikey);
}
