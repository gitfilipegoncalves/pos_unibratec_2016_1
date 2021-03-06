package br.com.nglauber.aula04_filmes;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.nglauber.aula04_filmes.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VH> {

    private List<Movie> mMovies;
    private Context mContext;
    private OnMovieClickListener mMovieClickListener;

    public MovieAdapter(Context ctx, List<Movie> mMovies) {
        this.mContext = ctx;
        this.mMovies = mMovies;
    }

    public void setMovieClickListener(OnMovieClickListener mcl) {
        this.mMovieClickListener = mcl;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_movie, parent, false);
        final VH viewHolder = new VH(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                if (mMovieClickListener != null){
                    mMovieClickListener.onMovieClick(view, mMovies.get(pos), pos);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Movie movie = mMovies.get(position);
        Glide.with(mContext)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imageViewPoster);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewYear.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewYear;

        public VH(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView)itemView.findViewById(R.id.movie_item_image_poster);
            textViewTitle = (TextView) itemView.findViewById(R.id.movie_item_text_title);
            textViewYear = (TextView)itemView.findViewById(R.id.movie_item_text_year);

            ViewCompat.setTransitionName(imageViewPoster, "capa");
            ViewCompat.setTransitionName(textViewTitle, "titulo");
            ViewCompat.setTransitionName(textViewYear, "ano");
        }
    }
}
