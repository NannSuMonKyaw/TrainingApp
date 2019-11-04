package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IMovieDetailsInfoModel {
    Observable<MovieDetailsInfoModel> getMovieDetailsFromApi(ServiceHelper.ApiService service, int movieId);
}
