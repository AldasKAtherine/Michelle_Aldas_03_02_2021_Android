package com.example.fiseiadministrativo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiseiadministrativo.interfaces.ConsejosApi;
import com.example.fiseiadministrativo.interfaces.ResolucionesApi;
import com.example.fiseiadministrativo.models.Consejo;
import com.example.fiseiadministrativo.models.Resolucion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsejoActivity extends AppCompatActivity {

    Consejo consejo;

    TextView textViewFecha;
    TextView textViewPresi;
    TextView textViewTipS;
    TextView textEncabezado;

    RecyclerView listadoResoluciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejo);
        consejo = (Consejo) getIntent().getSerializableExtra("consejo");



        textViewFecha = findViewById(R.id.textFechaGrande);
        textViewPresi = findViewById(R.id.textPresidenteG);
        textViewTipS = findViewById(R.id.textTipoSesionG);
        textEncabezado = findViewById(R.id.textResoluciones);


        listadoResoluciones = findViewById(R.id.listadoResoluciones);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre"};

        int dia = consejo.fecha_consejo.getDate();
        int mes = consejo.fecha_consejo.getMonth();
        int anio = consejo.fecha_consejo.getYear();

        textViewFecha.setText(dia + " de " + meses[mes] + " del " + (anio + 1900));

        textViewPresi.setText(consejo.presidente);

        textViewTipS.setText(consejo.tipo);
        cargarResoluciones();
    }


    public void cargarResoluciones(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.11:70").addConverterFactory(GsonConverterFactory.create()).build();
        ResolucionesApi usuApi = retrofit.create(ResolucionesApi.class);
        Call<List<Resolucion>> call = usuApi.listar(consejo.id);
        call.enqueue(new Callback<List<Resolucion>>() {
            @Override
            public void onResponse(Call<List<Resolucion>> call, Response<List<Resolucion>> response) {
                try {
                    if(response.isSuccessful()) {
                        List<Resolucion> resoluciones = response.body();
                        textEncabezado.setText("Listado de Resoluciones (" + resoluciones.size() +")");
                        listadoResoluciones.setLayoutManager(new LinearLayoutManager(ConsejoActivity.this));
                        ListadoResolucionesAdapter ad = new ListadoResolucionesAdapter(resoluciones);
                        listadoResoluciones.setAdapter(ad);

                    }
                } catch (Exception ex){
                    Toast.makeText(ConsejoActivity.this, ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Resolucion>> call, Throwable t) {
                Toast.makeText(ConsejoActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });
    }




}