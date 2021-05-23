package com.mygdx.game;

import java.util.Random;

public class Temporizador {
    static int tiempoJuego;
    int alarma;
    int frecuencia;
    boolean repetir = true;
    boolean activo = true;

    Temporizador(int frecuencia) {
        this.frecuencia = frecuencia;
        alarma = tiempoJuego + frecuencia;
    }

    Temporizador(int frecuencia, boolean repetir) {
        this.frecuencia = frecuencia;
        alarma = tiempoJuego + frecuencia;
        this.repetir = repetir;
    }

    public boolean suena() {
        if (activo && tiempoJuego >= alarma) {
            alarma = tiempoJuego + frecuencia;
            if (!repetir) activo = false;
            return true;
        }
        return false;
    }

    public void activar() {
        activo = true;
        alarma = tiempoJuego + frecuencia;
    }

    public static class Utils {
        static Random random = new Random();

        static boolean solapan(float x, float y, float w, float h, float x2, float y2, float w2, float h2) {
            return !(x > x2 + w2) && !(x + w < x2) && !(y > y2 + h2) && !(y + h < y2);
        }
    }
}