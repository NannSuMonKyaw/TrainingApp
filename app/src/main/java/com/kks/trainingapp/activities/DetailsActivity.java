package com.kks.trainingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kks.trainingapp.R;
import com.kks.trainingapp.adapters.MovieAdapter;
import com.kks.trainingapp.common.BaseActivity;
import com.kks.trainingapp.common.ItemOffsetDecoration;
import com.kks.trainingapp.custom_control.MyanProgressDialog;
import com.kks.trainingapp.interactor.MovieDetailsInteractor;
import com.kks.trainingapp.interactor.MovieInteractor;
import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.model.MovieInfoModel;
import com.kks.trainingapp.model.MovieListModel;
import com.kks.trainingapp.mvp.presenter.MainPresenterImpl;
import com.kks.trainingapp.mvp.presenter.MovieDetailPresenterImpl;
import com.kks.trainingapp.mvp.presenter.MovieDetailsPresenter;
import com.kks.trainingapp.mvp.view.MovieDetailsView;
import com.kks.trainingapp.util.ServiceHelper;

import java.util.List;

import butterknife.BindView;

import static com.kks.trainingapp.util.AppConstant.BASE_IMG_URL;

public class DetailsActivity extends BaseActivity implements MovieDetailsView {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_release_date)
     TextView tvReleaseDate;

    @BindView(R.id.tv_revenue)
    TextView tvRevenue;

    @BindView(R.id.tv_tagline)
    TextView tvTagline;

    @BindView(R.id.tv_overview)
    TextView tvOverview;

    @BindView(R.id.iv_movie_poster)
    ImageView ivMoviePoster;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.similar_movie_recycler_view)
    RecyclerView recyclerMovie;

    private MyanProgressDialog mDialog;
    private MovieDetailsPresenter mPresenter;
    private static int mmovieId;
    private MovieAdapter mAdapter;
    private  MovieDetailsInfoModel movieDetailsInfoModel;
    private ServiceHelper.ApiService mService;

    public static Intent getDetailsActivityIntent(Context context,int movieId) {

        Intent intent = new Intent(context, DetailsActivity.class);
        mmovieId=movieId;
        return intent;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_details;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(true);
        setupToolbarText("Movie");
        init();

    }


    private void init(){
        //getDetailsActivityIntent(this).getExtras().getSerializable(Movie_Key);

        mService = ServiceHelper.getClient(this);
        mDialog = new MyanProgressDialog(this);


        mPresenter = new MovieDetailPresenterImpl(new MovieDetailsInteractor(this.mService),new MovieInteractor(this.mService));

        mAdapter = new MovieAdapter();
        recyclerMovie.setHasFixedSize(true);
        //recyclerMovie.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerMovie.setLayoutManager(new GridLayoutManager(this,2));
        recyclerMovie.addItemDecoration(new ItemOffsetDecoration(2));
        recyclerMovie.setAdapter(mAdapter);
        mPresenter.onAttachView(this);
        mPresenter.onUIReady(mmovieId);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PlayMovieTrailer.gePlayMovieTrailerIntent(getApplicationContext(),mmovieId));
             }
        });




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
    public void hideLoading() {


            if (!isFinishing()) {
                mDialog.hideDialog();
            }
    }

    @Override
    public void showToastMsg(String msg) {
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
    public void showMovieDetails(MovieDetailsInfoModel movieDetailsInfoModel) {
        Glide.with(this)
                .load(BASE_IMG_URL+movieDetailsInfoModel.getPoster_path())
                .into(ivMoviePoster);
//
//        mtMovieTitle.setMyanmarText(model.getTitle());

        tvTitle.setText(movieDetailsInfoModel.getOriginal_title());
        tvReleaseDate.setText(movieDetailsInfoModel.getRelease_date());
        tvRevenue.setText(""+movieDetailsInfoModel.getRevenue());
        tvTagline.setText(movieDetailsInfoModel.getTagline());
        tvOverview.setText(movieDetailsInfoModel.getOverview());

    }

    @Override
    public void showSimilarVideos(List<MovieInfoModel> similarVideoListModel) {

        //page = 1;

        mAdapter.clear();
        // mAdapter.showLoading();
        for (MovieInfoModel model: similarVideoListModel) {
            mAdapter.add(model);
        }

    }
}
