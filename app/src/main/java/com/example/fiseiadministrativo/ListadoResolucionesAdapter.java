package com.example.fiseiadministrativo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiseiadministrativo.models.Resolucion;

import java.util.List;

public class ListadoResolucionesAdapter extends RecyclerView.Adapter<ListadoResolucionesAdapter.ViewHolderReso> {

    List<Resolucion> resoluciones;

    public ListadoResolucionesAdapter(List<Resolucion> resoluciones){
        this.resoluciones = resoluciones;
    }

    @NonNull
    @Override
    public ViewHolderReso onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado_resoluciones, null ,false);
        return new ViewHolderReso(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReso holder, int position) {

        Resolucion res = resoluciones.get(position);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre"};

        int dia = res.created_at.getDate();
        int mes = res.created_at.getMonth();
        int anioRes = res.created_at.getYear() + 1900;



        holder.textCodigo.setText(res.nummero_resolucion + "-P-CD-FISEI-UTA-"+ anioRes);
        holder.textFecha.setText(dia + " de " + meses[mes] + " del " + anioRes);
    }

    @Override
    public int getItemCount() {
        return resoluciones.size();
    }

    public class ViewHolderReso extends RecyclerView.ViewHolder {

        TextView textCodigo;
        TextView textFecha;

        public ViewHolderReso(@NonNull View itemView) {
            super(itemView);

            textCodigo = itemView.findViewById(R.id.textCodigo);
            textFecha = itemView.findViewById(R.id.textFechaRes);

        }


    }
}
