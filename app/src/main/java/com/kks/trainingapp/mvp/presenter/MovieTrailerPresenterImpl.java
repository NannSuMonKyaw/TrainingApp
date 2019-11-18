package com.kks.trainingapp.mvp.presenter;

import com.kks.trainingapp.R;
import com.kks.trainingapp.interactor.MovieDetailsInteractor;
import com.kks.trainingapp.interactor.MovieTrailerInteractor;
import com.kks.trainingapp.model.MovieTrailerModel;
import com.kks.trainingapp.model.ProfileInfoModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieTrailerPresenterImpl  {
    MovieTrailerInteractor movieTrailerInteractor;
    public MovieTrailerPresenterImpl(MovieTrailerInteractor interactor) {
        this.movieTrailerInteractor = interactor;
    }
//    @Override
//    public void getVideo(int movie_id) {
//
//        this.movieTrailerInteractor.getVideoById(movie_id).subscribe(new Observer<MovieTrailerModel>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//                addDisposableOberver(d);
//            }
//
//            @Override
//            public void onNext(MovieTrailerModel movieTrailerModel) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

