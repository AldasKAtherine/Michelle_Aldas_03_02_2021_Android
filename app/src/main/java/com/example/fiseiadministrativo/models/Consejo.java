package com.example.fiseiadministrativo.models;

import java.io.Serializable;
import java.util.Date;

public class Consejo implements Serializable {

    public int id;
    public Date fecha_consejo;
    public String presidente;
    public String estado;
    public String tipo;

}
