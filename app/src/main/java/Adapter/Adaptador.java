package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Adaptador extends RecyclerView.Adapter<Adaptador.AdaptadorViewHolder> {
    private Context Ctx;
    private List<Producto> lstProductos;

    public Adaptador(Context mCtx, List<Producto> productos) {
        this.lstProductos = productos;
        Ctx = mCtx;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lytitem, null);
        return new AdaptadorViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
        Producto producto = lstProductos.get(position);
        holder.textViewTitle.setText(producto.getTitle());
        holder.textViewPrice.setText(producto.getPrice());
        holder.textViewStock.setText(producto.getStock());
        holder.textViewCategory.setText(producto.getCategory());
        Glide.with(Ctx)
                .load(producto.getUrlavatar())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = createIntent(Ctx,producto.getImagen());
            Ctx.startActivity(intent);});

    }

    @Override
    public int getItemCount() {
        return lstProductos.size();
    }
    private Intent createIntent(Context context, List<String> imagen) {
        Intent intent = new Intent(context, MainActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("carta", new ArrayList<>(imagen));
        intent.putExtras(bundle);
        Log.i("bundle",bundle.toString());
        return intent;

    }

    class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrice, textViewStock, textViewCategory;
        ImageView imageView;

        public AdaptadorViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtTitle);
            textViewPrice = itemView.findViewById(R.id.txtPrice);
            textViewStock = itemView.findViewById(R.id.txtStock);
            textViewCategory = itemView.findViewById(R.id.txtCategory);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }


    }
}