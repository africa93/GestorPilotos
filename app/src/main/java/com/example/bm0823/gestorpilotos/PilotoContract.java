package com.example.bm0823.gestorpilotos;


import android.provider.BaseColumns;

public abstract class PilotoContract {

    public PilotoContract() {}

    public static class tablaPiloto implements BaseColumns{
        public static final String TABLE_NAME = "pilotos";

        public static final String COL_NAME_ID = "id";
        public static final String COL_NAME_NOMBRE = "nombre";
        public static final String COL_NAME_DORSAL = "dorsal";
        public static final String COL_NAME_MOTO = "moto";
        public static final String COL_NAME_ACTIVO = "activo";
    }
}
