package com.example.fiseiadministrativo.interfaces;

import com.example.fiseiadministrativo.models.Consejo;
import com.example.fiseiadministrativo.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConsejosApi {

    @GET("api/consejos")
    public Call<List<Consejo>> listar();

}
