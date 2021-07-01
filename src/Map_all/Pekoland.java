package Map_all;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import view.Gameview;
import view.Gameview.Canvas;
import MyObject.object;
import character.CharacterProtype;
import MyObject.carrot;

public class Pekoland extends Map {
    Plane plane = new Plane(7, 1);
    private Image C, CR;

    public Pekoland(Gameview view, Canvas panel, CharacterProtype p1, CharacterProtype p2) {
        super(view, panel, p1, p2);
        setbackground();
        plane.getplane(0).setLocation(0, 450);
        plane.getplane(1).setLocation(plane.width, 450);
        plane.getplane(2).setLocation(super.width - plane.getwidth(), 450);
        plane.getplane(3).setLocation(super.width - 2 * plane.getwidth(), 450);
        plane.getplane(4).setLocation(super.width / 2 - plane.getwidth() / 2, 280);
        plane.getplane(5).setLocation(super.width / 2 - plane.getwidth() * 3 / 2, 280);
        plane.getplane(6).setLocation(super.width / 2 + plane.getwidth() / 2, 280);
        try {
            C = ImageIO.read(new File("img/PEKO_skill1.png"));
            CR = ImageIO.read(new File("img/PEKO_skill1R.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
        if (new Random().nextInt(75) == 1) {
            oblist.add(new carrot(0, new Random().nextInt(height - 30) + 10, width, height, 10, 1, C));
        }
        if (new Random().nextInt(75) == 1) {
            oblist.add(new carrot(width - 80, new Random().nextInt(height - 30) + 10, width, height, 10, 2, CR));
        }
    }

    @Override
    public void render(Graphics g) {
        plane.render(g);
        super.render(g);
    }

    @Override
    public void setbackground() {
        ImageIcon img = new ImageIcon("img/Pekoland.jpg");
        img.setImage(img.getImage().getScaledInstance(view.givewidth(), -1, Image.SCALE_DEFAULT));
        super.background = new JLabel(img);
        view.getRootPane().add(super.background, -1);
        background.setBounds(0, 0, view.givewidth(), view.giveheight());
    }

    @Override
    protected void fallhandle(object o) {
        // TODO Auto-generated method stub
        super.fallhandle(o);
        for (int i = 0; i < 7; i++) {
            if (o.getpos().y + o.getheight() >= plane.getplane(i).y
                    && o.getpos().y + o.getheight() - 30 < plane.getplane(i).y
                    && ((o.getpos().x > plane.getplane(i).x - 10
                            && o.getpos().x < plane.getplane(i).x + plane.width + 10)
                            || (o.getpos().x + o.getwidth() > plane.getplane(i).x - 10
                                    && o.getpos().x + o.getwidth() < plane.getplane(i).x + plane.width + 10))) {
                o.fall = 0;
                o.getpos().setLocation(o.getpos().x, plane.getplane(i).y - o.getheight());
            }
        }
    }

    @Override
    protected void jumphandle(CharacterProtype p) {
        // TODO Auto-generated method stub
        super.jumphandle(p);
        int on = 0;
        for (int i = 0; i < 7; i++) {
            if (p.getpos().y + 220 >= plane.getplane(i).y && p.getpos().y + 190 < plane.getplane(i).y
                    && super.stayin(p.getpos(), plane.getplane(i), plane.width)) {
                if (p.fall == 0) {
                    on += 1;
                } else if (p.fall == 1) {
                    on += 1;
                    p.fall = 0;
                    p.jumptime = 0;
                    if (p.getAce() == 0) {
                        p.nowstate = p.getImage().getwalk(p.getdirect(), 0);
                    } else if (p.getAce() == 1) {
                        p.nowstate = p.getImage().getskillwalk(p.getdirect(), 0);
                    }
                    p.getpos().setLocation(p.getpos().x, plane.getplane(i).y - 220);
                }
            }
        }
        if (on == 0 && p.getpos().y < 470 && p.fall == 0) {
            p.fall = 1;
        }
    }
}
