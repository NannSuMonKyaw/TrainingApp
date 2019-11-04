package com.kks.trainingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kks.trainingapp.R;
import com.kks.trainingapp.activities.DetailsActivity;
import com.kks.trainingapp.common.BaseAdapter;
import com.kks.trainingapp.custom_control.MyanTextView;
import com.kks.trainingapp.model.MovieDetailsInfoModel;
import com.kks.trainingapp.model.MovieInfoModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kks.trainingapp.util.AppConstant.BASE_IMG_URL;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public class MovieAdapter extends BaseAdapter  {

    private Context context;
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);

        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieAdapter.ViewHolder)holder).bindView((MovieInfoModel) getItemsList().get(position),position);
    }
    //for recyclerview header
    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_poster)
        ImageView ivMoviePoster;

        @BindView(R.id.mt_movie_title)
        MyanTextView mtMovieTitle;

        private MovieInfoModel movieInfoModel;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    context.startActivity(DetailsActivity.getDetailsActivityIntent(context,movieInfoModel.getId()));
                }
            });
        }

        public void bindView(MovieInfoModel model, int position) {
            this.movieInfoModel=model;
            Glide.with(context)
                    .load(BASE_IMG_URL+model.getPoster_path())
                    .into(ivMoviePoster);

            mtMovieTitle.setMyanmarText(model.getOriginal_title());

        }
    }
}
