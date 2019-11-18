package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IGetVideoResultModel {
    Observable<GetVideoResultModel> getVideoFromApi(ServiceHelper.ApiService service, int movieId);
}
