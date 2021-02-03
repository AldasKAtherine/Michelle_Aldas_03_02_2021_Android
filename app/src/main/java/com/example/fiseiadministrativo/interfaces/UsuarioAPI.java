package com.example.fiseiadministrativo.interfaces;
import com.example.fiseiadministrativo.models.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import  retrofit2.http.Path;

public interface UsuarioAPI {
    @GET("api/autenticar/{email}/{contra}")
    public Call<Usuario> find(@Path("email") String email, @Path("contra") String contra);
}
