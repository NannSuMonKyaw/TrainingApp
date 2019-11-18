package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.activities.PlayMovieTrailer;
import com.kks.trainingapp.mvp.view.MovieTrailerView;

public interface GetVideoResultPresenter {
    void getVideo(int movieId);
    void onUIReady();
    void onAttachView(MovieTrailerView movieTrailerView);
}
