package com.kks.trainingapp.model;

import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.kks.trainingapp.util.AppConstant.DEVELOPER_KEY;

public class GetVideoResultModelImpl implements IGetVideoResultModel{
    @Override
    public Observable<GetVideoResultModel> getVideoFromApi(ServiceHelper.ApiService service, int movieId) {
        return service.getVideo(movieId,DEVELOPER_KEY )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
