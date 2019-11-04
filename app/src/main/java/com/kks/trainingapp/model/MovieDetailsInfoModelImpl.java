package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.kks.trainingapp.util.AppConstant.DEVELOPER_KEY;

public class MovieDetailsInfoModelImpl implements IMovieDetailsInfoModel {
    @Override
    public Observable<MovieDetailsInfoModel> getMovieDetailsFromApi(ServiceHelper.ApiService service, int movieId) {
        return service.getDetails(movieId,DEVELOPER_KEY )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
