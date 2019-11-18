package com.kks.trainingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kks.trainingapp.R;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.interactor.GetVideoResultInteractor;
import com.kks.trainingapp.interactor.MovieDetailsInteractor;
import com.kks.trainingapp.interactor.MovieTrailerInteractor;
import com.kks.trainingapp.model.GetVideoResultModel;
import com.kks.trainingapp.model.MovieTrailerModel;
import com.kks.trainingapp.mvp.presenter.GetVideoResultPresenter;
import com.kks.trainingapp.mvp.presenter.GetVideoResultPresenterImpl;
import com.kks.trainingapp.mvp.presenter.MovieDetailPresenterImpl;
import com.kks.trainingapp.mvp.presenter.MovieTrailerPresenter;
import com.kks.trainingapp.mvp.presenter.MovieTrailerPresenterImpl;
import com.kks.trainingapp.mvp.view.MovieTrailerView;
import com.kks.trainingapp.util.ServiceHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlayMovieTrailer extends BaseActivity implements MovieTrailerView {

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;


    int movieId;
    String strMovieId="";
    MyanProgressDialog mDialog;
    private ServiceHelper.ApiService mService;
    MovieTrailerModel movieTrailerModel;
    MovieTrailerPresenter movieTrailerPresenter;
    MovieTrailerInteractor movieTrailerInteractor;
    GetVideoResultInteractor getVideoResultInteractor;
    GetVideoResultPresenter getVideoResultPresenter;
    GetVideoResultModel getVideoResultModel;

    private static final String TAG = "PlayMovieTrailer";

    private static final String IE_MOVIE_ID = "movieid";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_play_movie_trailer);
//        movieId=getIntent().getExtras().getInt("MOVIE_ID");
//        Toast.makeText(PlayMovieTrailer.this,movieId,Toast.LENGTH_LONG);
//        mService = ServiceHelper.getClient(this);
//
//      //  MovieTrailerModel movieTrailerModel=movieTrailerInteractor.getVideoById(movieId);
//        getVideoResultPresenter = new GetVideoResultPresenterImpl(new GetVideoResultInteractor(this.mService));
//        getVideoResultPresenter.getVideo(movieId);
//        strMovieId=getVideoResultModel.getResults().get(0).getKey();
//
//        getLifecycle().addObserver(youTubePlayerView);
//
//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        strMovieId,
//                        0
//                );
//            }
//        });
//
//
////        youTubePlayerView.initialize("YOUR API KEY",
////                new YouTubePlayer.OnInitializedListener() {
////                    @Override
////                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
////                                                        YouTubePlayer youTubePlayer, boolean b) {
////
////                        // do any work here to cue video, play video, etc.
////                        youTubePlayer.cueVideo("5xVh-7ywKpE");
////                    }
////                    @Override
////                    public void onInitializationFailure(YouTubePlayer.Provider provider,
////                                                        YouTubeInitializationResult youTubeInitializationResult) {
////
////                    }
////                });
//    }
    public static Intent gePlayMovieTrailerIntent(Context context, int movieId) {

        Intent intent = new Intent(context, PlayMovieTrailer.class);
        intent.putExtra(IE_MOVIE_ID,movieId);
        return intent;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_play_movie_trailer;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(true);
        // setupToolbarBgColor(String.valueOf((R.color.colorBlack)));
        init();
    }

    private void init(){
        //movieId=420809;
//        Toast.makeText(PlayMovieTrailer.this,movieId,Toast.LENGTH_LONG);

        movieId = getIntent().getIntExtra(IE_MOVIE_ID, 0);

        Log.e(TAG, "init: " + movieId );
        mService = ServiceHelper.getClient(this);
        mDialog = new MyanProgressDialog(this);
        //  MovieTrailerModel movieTrailerModel=movieTrailerInteractor.getVideoById(movieId);
        getVideoResultPresenter = new GetVideoResultPresenterImpl(new GetVideoResultInteractor(this.mService));
        getVideoResultPresenter.onAttachView(this);
        getVideoResultPresenter.getVideo(movieId);

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showLoading() {
        if (!isFinishing()) {
            mDialog.showDialog();
        }
    }

    @Override
    public void hideLoading( ) {
        if (!isFinishing()) {
            mDialog.hideDialog();
        }

    }

    @Override
    public void showToastMsg( String msg) {
        this.hideLoading();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDialogMsg(String title, String msg) {
        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }

    @Override
    public void showVideo(GetVideoResultModel getVideoResultModel) {


    strMovieId=getVideoResultModel.getResults().get(0).getKey();

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        strMovieId,
//                        0
//                );

                Log.e(TAG, "onReady: !!!!! " + strMovieId );
                youTubePlayer.loadVideo(strMovieId, 0);
            }
        });
    }
}
