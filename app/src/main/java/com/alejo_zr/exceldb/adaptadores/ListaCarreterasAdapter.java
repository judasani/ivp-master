package com.alejo_zr.exceldb.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alejo_zr.exceldb.R;
import com.alejo_zr.exceldb.entidades.Carretera;

import java.util.ArrayList;

/**
 * Created by Alejo on 27/09/2017.
 */

public class ListaCarreterasAdapter extends RecyclerView.Adapter<ListaCarreterasAdapter.CarreterasViewHolder>
        implements View.OnClickListener {

    ArrayList<Carretera> listaCarretera;
    private View.OnClickListener listener;

    public ListaCarreterasAdapter(ArrayList<Carretera> listaCarretera){
        this.listaCarretera = listaCarretera;
    }

    @Override
    public CarreterasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_carretera,null,false);

        view.setOnClickListener(this);

        return new CarreterasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarreterasViewHolder holder, int position) {
        holder.tvCVId.setText(listaCarretera.get(position).getId().toString());
        holder.tvCVNomCar.setText(listaCarretera.get(position).getNombreCarretera());
        holder.tvCVCod.setText(listaCarretera.get(position).getCodCarretera());
        holder.tvCVTerrito.setText(listaCarretera.get(position).getTerritorial());
        holder.tvCVAdmon.setText(listaCarretera.get(position).getAdmon());
        holder.tvCVLevan.setText(listaCarretera.get(position).getLevantado());
    }

    @Override
    public int getItemCount() {
        return listaCarretera.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class CarreterasViewHolder extends RecyclerView.ViewHolder {

        TextView  tvCVId,tvCVNomCar, tvCVCod, tvCVTerrito, tvCVAdmon,tvCVLevan;

        public CarreterasViewHolder(View itemView) {
            super(itemView);

            tvCVId = (TextView) itemView.findViewById(R.id.tvCVId);
            tvCVNomCar = (TextView) itemView.findViewById(R.id.tvCVNomCar);
            tvCVCod = (TextView)  itemView.findViewById(R.id.tvCVCod);
            tvCVTerrito = (TextView)  itemView.findViewById(R.id.tvCVTerrito);
            tvCVAdmon = (TextView)  itemView.findViewById(R.id.tvCVAdmon);
            tvCVLevan= (TextView)  itemView.findViewById(R.id.tvCVLevan);

        }


    }
}
