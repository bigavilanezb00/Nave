package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont bitmapFont;
    Fondo fondo;
    Jugador jugador;
    List<Enemigo> enemigos = new ArrayList<>();
    List<Disparo> disparosAEliminar = new ArrayList<>();
    List<Enemigo> enemigosAEliminar = new ArrayList<>();
    Temporizador temporizadorNuevoAlien = new Temporizador(120);

    @Override
    public void create() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFont.getData().setScale(2f);

        fondo = new Fondo();
        jugador = new Jugador();
    }

    void update() {
        Temporizador.tiempoJuego += 1;

        if (temporizadorNuevoAlien.suena()) enemigos.add(new Enemigo());

        jugador.update();

        for (Enemigo enemigo : enemigos) enemigo.update();              // enemigos.forEach(Enemigo::update);

        for (Enemigo enemigo : enemigos) {
            for (Disparo disparo : jugador.disparos) {
                if (Temporizador.Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo.x, enemigo.y, enemigo.w, enemigo.h)) {
                    disparosAEliminar.add(disparo);
                    enemigosAEliminar.add(enemigo);
                    jugador.puntos++;
                    break;
                }
            }

            if (!jugador.muerto && Temporizador.Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.morir();
            }

            if (enemigo.x < -enemigo.w) enemigosAEliminar.add(enemigo);
        }

        for (Disparo disparo : jugador.disparos)
            if (disparo.x > 640)
                disparosAEliminar.add(disparo);

        for (Disparo disparo : disparosAEliminar) jugador.disparos.remove(disparo);       // disparosAEliminar.forEach(disparo -> jugador.disparos.remove(disparo));
        for (Enemigo enemigo : enemigosAEliminar) enemigos.remove(enemigo);               // enemigosAEliminar.forEach(enemigo -> enemigos.remove(enemigo));
        disparosAEliminar.clear();
        enemigosAEliminar.clear();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.begin();
        fondo.render(batch);
        jugador.render(batch);
        for (Enemigo enemigo : enemigos) enemigo.render(batch);  // enemigos.forEach(e -> e.render(batch));
        bitmapFont.draw(batch, "" + jugador.vidas, 590, 440);
        bitmapFont.draw(batch, "" + jugador.puntos, 30, 440);
        batch.end();
    }
}

/*
init();
create();
while(true) render();
 */