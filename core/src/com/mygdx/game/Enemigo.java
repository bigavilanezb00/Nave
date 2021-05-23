package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo {
    Animacion animacion = new Animacion(6, true, "meteorito_0.png", "meteorito_1.png", "meteorito_2.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(60);

    Enemigo() {
        x = 640;
        y = Temporizador.Utils.random.nextInt(480);
        w = 64 * 2;
        h = 48 * 2;
        vx = -2;
        vy = 0;
    }

    public void update() {
        y += vy;
        x += vx;

        if (cambioVelocidad.suena()) {
            vy = Temporizador.Utils.random.nextInt(6) - 3;
            vx = -(Temporizador.Utils.random.nextInt(3)+1);
        }
    }

    void render(SpriteBatch batch) {
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
    }
}