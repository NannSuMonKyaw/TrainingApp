package com.kks.trainingapp.mvp.presenter;

import android.graphics.Movie;

import com.kks.trainingapp.R;
import com.kks.trainingapp.interactor.MovieDetailsInteractor;
import com.kks.trainingapp.interactor.MovieInteractor;
import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.mvp.view.MainView;
import com.kks.trainingapp.mvp.view.MovieDetailsView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieDetailPresenterImpl extends BasePresenter implements MovieDetailsPresenter {

    private MovieDetailsView movieDetailsView;
    private MovieDetailsInteractor movieDetailsInteractor;
    private MovieInteractor movieInteractor;


    public MovieDetailPresenterImpl(MovieDetailsInteractor interactor, MovieInteractor movieInteractor) {
        this.movieDetailsInteractor = interactor;
        this.movieInteractor=movieInteractor;
    }

    @Override
    public void onUIReady(int movieId) {
       //getMovieDetails(movieId);
        showMovieDetailsById(movieId);
        showSimilarVideosById(movieId);
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

    @Override
    public void showSimilarVideosById(int movieId) {
        movieDetailsView.showLoading();

        this.movieInteractor.getSimilarVideosById(movieId,1)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);

                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {

                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {

                            } else {

                                movieDetailsView.showSimilarVideos(movieListModel.getResults());

                            }

                        } else {
                            movieDetailsView.showDialogMsg(movieDetailsView.context().getResources().getString(R.string.error_connecting),
                                    movieDetailsView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }
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
