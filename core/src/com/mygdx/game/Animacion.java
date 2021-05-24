package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Animacion {
    Texture[] textures;
    int duracion;

    Animacion(int duracion, Texture... textures){
        this.textures = textures;
        this.duracion = duracion;
    }

    Texture getFrame(){
        int anim = Temporizador.tiempoJuego/duracion%textures.length;
        return textures[anim];
    }
}