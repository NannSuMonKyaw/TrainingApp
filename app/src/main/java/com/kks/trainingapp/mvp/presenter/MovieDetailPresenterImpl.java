package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.R;
import com.kks.trainingapp.interactor.MovieDetailsInteractor;
import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.mvp.view.MovieDetailsView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieDetailPresenterImpl extends BasePresenter implements MovieDetailsPresenter {

    private MovieDetailsView movieDetailsView;
    private MovieDetailsInteractor movieDetailsInteractor;

    public MovieDetailPresenterImpl(MovieDetailsInteractor interactor) {
        this.movieDetailsInteractor = interactor;
    }

    @Override
    public void onUIReady() {
       //getMovieDetails(movieId);
    }

    @Override
    public void onAttachView(MovieDetailsView view) {
        this.movieDetailsView = view;
    }



    @Override
    public void showMovieDetailsById(int movieId) {

       movieDetailsView.showLoading();

        this.movieDetailsInteractor.getMovieDetailsById(movieId)
                .subscribe(new Observer<MovieDetailsInfoModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                   @Override
                    public void onNext(MovieDetailsInfoModel movieDetailsInfoModel) {
                        movieDetailsView.showMovieDetails(movieDetailsInfoModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailsView.hideLoading();
                        movieDetailsView.showDialogMsg(movieDetailsView.context().getResources().getString(R.string.error_connecting),
                                e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        movieDetailsView.hideLoading();

                    }
                });
    }


}
