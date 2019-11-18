package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IMovieTrailerModel {
    Observable<MovieTrailerModel> getMovieTrailerFromApi(ServiceHelper.ApiService service, int movieId);
}
