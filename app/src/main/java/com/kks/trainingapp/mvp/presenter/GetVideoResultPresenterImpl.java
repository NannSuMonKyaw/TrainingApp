package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.activities.PlayMovieTrailer;
import com.kks.trainingapp.interactor.GetVideoResultInteractor;
import com.kks.trainingapp.model.GetVideoResultModel;
import com.kks.trainingapp.model.MovieTrailerModel;
import com.kks.trainingapp.mvp.view.MovieTrailerView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class GetVideoResultPresenterImpl extends  BasePresenter implements GetVideoResultPresenter {
    GetVideoResultInteractor getVideoResultInteractor;
    MovieTrailerView movieTrailerView;

    public GetVideoResultPresenterImpl(GetVideoResultInteractor getVideoResultInteractor) {
        this.getVideoResultInteractor = getVideoResultInteractor;
    }

    @Override
    public void getVideo(int movie_id) {
        this.getVideoResultInteractor.getVideoById(movie_id)
                .subscribe(new Observer<GetVideoResultModel>() {

            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(GetVideoResultModel getVideoResultModel) {
                movieTrailerView.showVideo(getVideoResultModel);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onUIReady() {

    }

    @Override
    public void onAttachView(MovieTrailerView movieTrailerView) {
        this.movieTrailerView=movieTrailerView;
    }


}
