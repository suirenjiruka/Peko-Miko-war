package Gamecontrol;

import javax.swing.*;
import java.awt.*;
import java.lang.Thread;
import java.util.concurrent.Delayed;

import view.Gameview;
import view.Gameview.Canvas;
import Map_all.Map;

public abstract class Gameloop {
    Gameview view;
    Canvas panel;
    boolean running = false;

    public Gameloop(Gameview view, view.Gameview.Canvas panel) {
        this.view = view;
        this.panel = panel;
    }

    public void start() {
        new Thread(this::gameloop).start();
    }

    private void gameloop() {
        running = true;
        while (true) {
            Map world = getWorld();
            update();
            render();
            delay(80);
        }
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract Map getWorld();

    public void stop() {
        running = false;
        System.exit(0);
    }

    public void update() {
        /* wait override */;
    }

    public void render() {
        panel.repaint();
    }

    public void setbackground(int map) {
        /* wait override */;
    }
}