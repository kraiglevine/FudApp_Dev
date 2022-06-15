package com.jvillacorta.fudapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvillacorta.fudapp.R;
import com.jvillacorta.fudapp.entity.Plato;

import java.util.ArrayList;
import java.util.List;

public class PlatoRecycler extends RecyclerView.Adapter<PlatoRecycler.MyViewHolder> {
    private Context context;
    private List<Plato> listaPlatos = new ArrayList<>();

    public PlatoRecycler(Context context, List<Plato> listaPlatos){
        this.context = context;
        this.listaPlatos = listaPlatos;
    }

    @NonNull
    @Override
    public PlatoRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.plato_recycler_fila, parent, false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoRecycler.MyViewHolder holder, int position) {
        holder.plato_nombre.setText(listaPlatos.get(position).getNombre()+"");
        holder.plato_ingredientes.setText(listaPlatos.get(position).getIngredientes()+"");
        holder.plato_precio.setText(listaPlatos.get(position).getPrecio()+"");
        holder.plato_descripcion.setText(listaPlatos.get(position).getDescripcion()+"");
        holder.plato_oferta.setText(listaPlatos.get(position).getOferta()+"");
        //holder.plato_imagen.setImageBitmap(listaPlatos.get(position).getImagen());
        //Herramientas.cargarImagenURL(listaPlatos.get(position).getImagenURL(), holder.plato_imagen);
        //Herramientas.cargarImagenURL(listaPlatos.get(position).getImagenURL(), holder.plato_imagen);
        holder.plato_imagen.setImageDrawable(Herramientas.cargarImagenURL(listaPlatos.get(position).getImagenURL()+"", holder.plato_imagen));
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView plato_nombre, plato_ingredientes, plato_precio, plato_descripcion, plato_oferta;
        ImageView plato_imagen;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            plato_nombre = itemView.findViewById(R.id.txtPlatoNombre);
            plato_ingredientes = itemView.findViewById(R.id.txtPlatoIngredientes);
            plato_precio = itemView.findViewById(R.id.txtPlatoPrecio);
            plato_descripcion = itemView.findViewById(R.id.txtPlatoDescripcion);
            plato_oferta = itemView.findViewById(R.id.txtPlatoOferta);
            plato_imagen = itemView.findViewById(R.id.ivPlatoImagen);
        }
    }
}
