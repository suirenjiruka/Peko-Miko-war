package Map_all;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

import view.Gameview;
import view.Gameview.Canvas;
import Gamecontrol.Game;
import character.CharacterProtype;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Plane {
    private Image img;
    int width = 200;
    int height = 30;
    int kind;
    private Point[] pos_set;

    public Plane(int planeNum, int kind) {
        pos_set = new Point[planeNum];
        for (int i = 0; i < planeNum; i++) {
            pos_set[i] = new Point();
        }
        if (kind == 1) {
            this.kind = 1;
            try {
                img = ImageIO.read(new File("img/plane.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (kind == 2) {
            this.kind = 2;
            try {
                img = ImageIO.read(new File("img/plane2.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Point getplane(int index) {
        return pos_set[index];
    }

    public int getwidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public void render(Graphics g) {
        for (Point point : pos_set) {
            g.drawImage(img, point.x, point.y, width, height, null);
        }
    }
}
