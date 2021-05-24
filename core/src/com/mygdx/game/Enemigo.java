package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo {
    Texture texture = new Texture("meteorito_0.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(60);

    Enemigo() {
        x = 640;
        y = Utils.random.nextInt(480);
        w = 64 * 2;
        h = 48 * 2;
        vx = -2;
        vy = 0;
        this.texture = new Texture("meteorito_0.png");
    }
    {
        x = 640;
        y = Utils.random.nextInt(460);
        w = 64;
        h = 48;
        vx = -3;
        vy = 1;
        this.texture = new Texture("meteorito_0.png");
    }
    {
        x = 640;
        y = Utils.random.nextInt(440);
        w = 64;
        h = 48;
        vx = -3;
        vy = 1;
        this.texture = new Texture("meteorito_0.png");
    }
    {
        x = 640;
        y = Utils.random.nextInt(440);
        w = 64 * 2;
        h = 48 * 2;
        vx = -3;
        vy = 1;
        this.texture = new Texture("meteorito_0.png");
    }

    public void update() {
        y += vy;
        x += vx;

        if (cambioVelocidad.suena()) {
            vy = Utils.random.nextInt(6) - 3;
            vx = -(Utils.random.nextInt(3)+1);
        }
    }

    void render(SpriteBatch batch) {
        if (texture != null) {
            batch.draw(texture, x, y, w, h);
        }
    }
}