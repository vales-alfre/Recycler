package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recycler.MainActivity2;
import com.example.recycler.R;

import java.util.ArrayList;
import java.util.List;

import Model.Producto;

public class AdaptadorImagen extends RecyclerView.Adapter<AdaptadorImagen.AdaptadorImagenViewHolder> {
    private Context Ctx;
    private List<String> lstimagen;

    public AdaptadorImagen(Context mCtx, List<String> imagen) {
        this.lstimagen = imagen;
        Ctx = mCtx;
    }

    @NonNull
    @Override
    public AdaptadorImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lytimagen, null);
        return new AdaptadorImagen.AdaptadorImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorImagenViewHolder holder, int position) {
        String imgen;
        imgen=lstimagen.get(position);
        Glide.with(Ctx).load(imgen).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstimagen.size();
    }


    class AdaptadorImagenViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public AdaptadorImagenViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagen);
        }


    }
}

