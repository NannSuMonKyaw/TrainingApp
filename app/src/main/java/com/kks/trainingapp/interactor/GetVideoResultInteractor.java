package com.kks.trainingapp.interactor;

import com.kks.trainingapp.model.GetVideoResultModel;
import com.kks.trainingapp.model.GetVideoResultModelImpl;
import com.kks.trainingapp.model.IGetVideoResultModel;
import com.kks.trainingapp.model.MovieTrailerModel;
import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public class GetVideoResultInteractor {
    private ServiceHelper.ApiService mService;
    private IGetVideoResultModel getVideoResultModel;

    public GetVideoResultInteractor(ServiceHelper.ApiService mService) {
        this.mService = mService;
        getVideoResultModel=new GetVideoResultModelImpl();
    }

    public Observable<GetVideoResultModel> getVideoById(int movie_id){
        return this.getVideoResultModel.getVideoFromApi(mService,movie_id);
    }
}
