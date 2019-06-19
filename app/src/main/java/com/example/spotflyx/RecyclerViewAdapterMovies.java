package com.example.spotflyx;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterMovies extends RecyclerView.Adapter<RecyclerViewAdapterMovies.MyViewHolder> {

    private Context mContext;
    private List<Pelicula> mData;
    List<String> lstImagenes;

    public RecyclerViewAdapterMovies(Context mContext, List<Pelicula> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.content_navigation_drawer_movies, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitulo());

        /* Picasso.with(mContext).load(mData.get(position).getThumbnail()).into(holder.img_book_thumbnail);*/
        Picasso.with(mContext).load(mData.get(position).getPortada()).into(holder.img_book_thumbnail);
        /*holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());*/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MoviesActivity.class);

                intent.putExtra("Titulo", mData.get(position).getTitulo());
                intent.putExtra("Descripcion", mData.get(position).getDescripcion());
                intent.putExtra("Portada", mData.get(position).getPortada());
                intent.putExtra("Genero", mData.get(position).getGenero());
                intent.putExtra("Link", mData.get(position).getLink());
                mContext.startActivity(intent);
            }
        });
    }

    public static List<String> CargarImagenes(List<Pelicula> mData) {
        List<String> lstImagenes = new ArrayList<String>();
        for (int i = 0; i < mData.size(); i++) {
            lstImagenes.add(mData.get(i).getPortada());
        }
        return lstImagenes;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            tv_book_title = view.findViewById(R.id.book_title_id);
            img_book_thumbnail = view.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
