package com.kks.trainingapp.interactor;

import com.kks.trainingapp.model.IMovieDetailsInfoModel;
import com.kks.trainingapp.model.IMovieListModel;
import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.model.MovieDetailsInfoModelImpl;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.model.MovieListModelImpl;
import com.kks.trainingapp.model.ProfileInfoModelImpl;
import com.kks.trainingapp.mvp.presenter.MovieDetailPresenterImpl;
import com.kks.trainingapp.util.ServiceHelper;

import io.reactivex.Observable;

public class MovieDetailsInteractor {
    private ServiceHelper.ApiService mService;
    private IMovieDetailsInfoModel movieDetailsModel;
    private MovieListModel movieListModel;

    public MovieDetailsInteractor(ServiceHelper.ApiService service) {
        this.mService = service;
        movieDetailsModel = new MovieDetailsInfoModelImpl();
    }

    public Observable<MovieDetailsInfoModel> getMovieDetailsById(int movie_id) {
        return this.movieDetailsModel.getMovieDetailsFromApi(mService,movie_id);

    }


}
