package com.kks.trainingapp.mvp.view;

import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.model.MovieListModel;

import java.util.List;

public interface MovieDetailsView extends BaseView {
    void showMovieDetails(MovieDetailsInfoModel movieDetailsInfoModel);
    void showSimilarVideos(List<MovieInfoModel> similarVideoListModel);

}
