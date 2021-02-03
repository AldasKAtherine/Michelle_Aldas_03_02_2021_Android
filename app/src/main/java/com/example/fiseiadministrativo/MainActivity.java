package com.example.fiseiadministrativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiseiadministrativo.interfaces.UsuarioAPI;
import com.example.fiseiadministrativo.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText txtEmail;
    EditText txtContra;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail =findViewById(R.id.textEmail);
        txtContra = findViewById(R.id.textContra);

        btnLog = findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(txtEmail.getText().toString(),txtContra.getText().toString());
            }
        });
    }
    private  void find(String email, String contra){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.11:70").addConverterFactory(GsonConverterFactory.create()).build();
        UsuarioAPI usuApi = retrofit.create(UsuarioAPI.class);
        Call<Usuario> call = usuApi.find(email,contra);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {
                    if(response.isSuccessful()) {
                        Usuario p = response.body();
                        Intent nuevaPantalla = new Intent(MainActivity.this, ListadoConsejos.class);
                        startActivity(nuevaPantalla);
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}