package com.jvillacorta.fudapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jvillacorta.fudapp.MainActivity;
import com.jvillacorta.fudapp.R;
import com.jvillacorta.fudapp.RegisterActivity;
import com.jvillacorta.fudapp.RegistrarPlato;
import com.jvillacorta.fudapp.entity.Plato;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PlatoRecycler extends RecyclerView.Adapter<PlatoRecycler.MyViewHolder> {
    private Context context;
    private List<Plato> listaPlatos = new ArrayList<>();
    private List<Plato> filtroPlatos = new ArrayList<>();

    public PlatoRecycler(Context context, List<Plato> listaPlatos){
        this.context = context;
        this.listaPlatos = listaPlatos;
        filtroPlatos.clear();
        filtroPlatos.addAll(listaPlatos);
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

        holder.linearLayout.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, RegistrarPlato.class);
            intent.putExtra("pl_id", listaPlatos.get(position).getId());
            intent.putExtra("pl_nombre", listaPlatos.get(position).getNombre());
            intent.putExtra("pl_ingredientes", listaPlatos.get(position).getIngredientes());
            intent.putExtra("pl_descripcion", listaPlatos.get(position).getDescripcion());
            intent.putExtra("pl_precio", listaPlatos.get(position).getPrecio()+"");
            intent.putExtra("pl_oferta", listaPlatos.get(position).getOferta()+"");
            intent.putExtra("pl_fecha", listaPlatos.get(position).getFecha());
            intent.putExtra("pl_imagenURL", listaPlatos.get(position).getImagenURL());
            context.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView plato_nombre, plato_ingredientes, plato_precio, plato_descripcion, plato_oferta;
        ImageView plato_imagen;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            plato_nombre = itemView.findViewById(R.id.txtPlatoNombre);
            plato_ingredientes = itemView.findViewById(R.id.txtPlatoIngredientes);
            plato_precio = itemView.findViewById(R.id.txtPlatoPrecio);
            plato_descripcion = itemView.findViewById(R.id.txtPlatoDescripcion);
            plato_oferta = itemView.findViewById(R.id.txtPlatoOferta);
            plato_imagen = itemView.findViewById(R.id.ivPlatoImagen);
            linearLayout = itemView.findViewById(R.id.llMostrarDatosPlato);
        }
    }
}
