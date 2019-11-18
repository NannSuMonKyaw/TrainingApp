package com.kks.trainingapp.mvp.presenter;


import com.kks.trainingapp.mvp.view.MovieDetailsView;

public interface MovieDetailsPresenter {
    void onUIReady(int movieId);
    void onAttachView(MovieDetailsView view);
    void showMovieDetailsById(int movieId);
    void showSimilarVideosById(int movieId);

}
