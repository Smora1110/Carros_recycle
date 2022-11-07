package com.example.carros_recycle.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carros_recycle.R;
import com.example.carros_recycle.model.entities.Carros;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    ArrayList<Carros> listadoDatos;


    public OnItemClickListener onItemClickListener;

    public AdaptadorPersonalizado(ArrayList<Carros> listadoDatos) {
        this.listadoDatos = listadoDatos;
        this.onItemClickListener = null;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setListadoDatos(ArrayList<Carros> listadoDatos) {
        this.listadoDatos = listadoDatos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecyclercarro, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarDatos(listadoDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView modelo, descripcion;
        ImageView ivPrincipal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            modelo = itemView.findViewById(R.id.tv_tit1);
            descripcion = itemView.findViewById(R.id.tv_des1);
            ivPrincipal = itemView.findViewById(R.id.img1);
        }

        public void cargarDatos(Carros carro) {
            modelo.setText(carro.getNombre());
            descripcion.setText(carro.getDescripcion());
            //ivPrincipal.setImageResource();
            Picasso.get()
                    .load(carro.getURLimg())
                    .resize(300, 300)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(ivPrincipal);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "> " + carro.getNombre(), Toast.LENGTH_SHORT).show();
                }
            });*/
            if(onItemClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(carro, getAdapterPosition());

                        //Toast.makeText(itemView.getContext(), "Click ItemAAA"+ carro.getNombre(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Carros carro, int posicion);
    }
}
