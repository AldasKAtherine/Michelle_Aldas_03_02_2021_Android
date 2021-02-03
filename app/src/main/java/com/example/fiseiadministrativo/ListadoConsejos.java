package com.example.fiseiadministrativo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.widget.Toast;

import com.example.fiseiadministrativo.interfaces.ConsejosApi;
import com.example.fiseiadministrativo.interfaces.UsuarioAPI;
import com.example.fiseiadministrativo.models.Consejo;
import com.example.fiseiadministrativo.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoConsejos extends AppCompatActivity {

    RecyclerView listadoConsejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_consejos);
        listadoConsejos = findViewById(R.id.listadoConsejos);
        cargarConsejos();
    }

    public void cargarConsejos(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.11:70").addConverterFactory(GsonConverterFactory.create()).build();
        ConsejosApi conseApi = retrofit.create(ConsejosApi.class);
        Call<List<Consejo>> call = conseApi.listar();
        call.enqueue(new Callback<List<Consejo>>() {
            @Override
            public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                try {
                    if(response.isSuccessful()) {
                        Toast.makeText(ListadoConsejos.this, "Cargo",Toast.LENGTH_SHORT).show();
                        List<Consejo> consejos = response.body();
                        listadoConsejos.setLayoutManager(new LinearLayoutManager(ListadoConsejos.this));
                        ListadoConsejosAdapter ad = new ListadoConsejosAdapter(consejos, new ListadoConsejosAdapter.OnItemClickListener(){
                            @Override
                            public void onItemClick(Consejo consejo) {
                                Intent nuevaPantalla = new Intent(ListadoConsejos.this, ConsejoActivity.class);
                                nuevaPantalla.putExtra("consejo", consejo);
                                startActivity(nuevaPantalla);
                            }
                        });
                        listadoConsejos.setAdapter(ad);

                    }
                } catch (Exception ex){
                    Toast.makeText(ListadoConsejos.this, ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Consejo>> call, Throwable t) {
                Toast.makeText(ListadoConsejos.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });
    }
}