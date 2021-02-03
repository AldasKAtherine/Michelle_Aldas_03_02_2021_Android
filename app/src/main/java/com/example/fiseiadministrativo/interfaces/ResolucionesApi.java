package com.example.fiseiadministrativo.interfaces;

import com.example.fiseiadministrativo.models.Consejo;
import com.example.fiseiadministrativo.models.Resolucion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResolucionesApi {

    @GET("api/resolucionesConsejo/{id}")
    public Call<List<Resolucion>> listar(@Path("id") int id);

}
