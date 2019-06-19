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

public class RecyclerViewAdapterMusic extends RecyclerView.Adapter<RecyclerViewAdapterMusic.MyViewHolder> {

    private Context mContext;
    private List<Album> mData;

    public RecyclerViewAdapterMusic(Context mContext, List<Album> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.content_navigation_drawer_music, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_book_title.setText(mData.get(position).getTitulo());

        Picasso.with(mContext).load(mData.get(position).getPortada()).into(holder.img_book_thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MusicActivity.class);

                intent.putExtra("idAlbum", mData.get(position).getIdAlbum());
                intent.putExtra("Titulo", mData.get(position).getTitulo());
                intent.putExtra("Genero", mData.get(position).getGenero());
                intent.putExtra("Portada", mData.get(position).getPortada());
                intent.putExtra("Artista", mData.get(position).getArtista());

                ArrayList<Cancion> listacanciones = new ArrayList<Cancion>(mData.get(position).getListaCanciones());
                intent.putExtra("Canciones", listacanciones);
                mContext.startActivity(intent);
            }
        });
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

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardviewmusic_id);
        }
    }
}
