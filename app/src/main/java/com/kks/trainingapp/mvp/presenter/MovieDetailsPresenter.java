package com.kks.trainingapp.mvp.presenter;


import com.kks.trainingapp.mvp.view.MovieDetailsView;

public interface MovieDetailsPresenter {
    void onUIReady();
    void onAttachView(MovieDetailsView view);
    void showMovieDetailsById(int movieId);

}
