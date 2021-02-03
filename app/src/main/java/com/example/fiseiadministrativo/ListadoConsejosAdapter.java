package com.example.fiseiadministrativo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiseiadministrativo.models.Consejo;

import java.util.List;

public class ListadoConsejosAdapter extends RecyclerView.Adapter<ListadoConsejosAdapter.ViewHolderConsejos> {

    public interface OnItemClickListener {
        void onItemClick(Consejo item);
    }

    List<Consejo> consejos;
    OnItemClickListener listener;


    public ListadoConsejosAdapter(List<Consejo> consejos, OnItemClickListener listener){

        this.consejos = consejos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderConsejos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_consejo, null, false);
        return new ViewHolderConsejos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConsejos holder, int position) {
        holder.llenarDatos(consejos.get(position), this.listener);
    }

    @Override
    public int getItemCount() {
        return this.consejos.size();
    }


    public class ViewHolderConsejos extends RecyclerView.ViewHolder {
        TextView textFeha;
        TextView textPresidente;
        TextView textTipo;
        public ViewHolderConsejos(@NonNull View itemView) {
            super(itemView);
            textFeha = itemView.findViewById(R.id.textFecha);
            textPresidente = itemView.findViewById(R.id.textPresidente);
            textTipo = itemView.findViewById(R.id.textTipo);
        }

        public void llenarDatos (Consejo consejo, OnItemClickListener listener) {

            String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre"};

            int dia = consejo.fecha_consejo.getDate();
            int mes = consejo.fecha_consejo.getMonth();
            int anio = consejo.fecha_consejo.getYear();

            textFeha.setText("Consejo del " + dia + " de " + meses[mes] + " del " + (anio + 1900));
            textPresidente.setText(consejo.presidente);
            textTipo.setText(consejo.tipo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(consejo);
                }
            });
        }
    }
}
