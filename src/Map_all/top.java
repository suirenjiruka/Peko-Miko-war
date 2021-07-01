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
import MyObject.enemy;

public class top extends Map {
    private Plane[] plane = new Plane[3];
    private Plane[] plane2 = new Plane[3];
    private int[] plane_num = { 4, 3, 4 };
    private int[] plane2_num = { 3, 4, 3 };
    private int ticks = 0;
    private int monster_ticks = 0;
    private int state = 0;

    public top(Gameview view, Canvas panel, CharacterProtype p1, CharacterProtype p2) {
        super(view, panel, p1, p2);
        setbackground();

        plane[0] = new Plane(4, 1);
        plane[1] = new Plane(3, 1);
        plane[2] = new Plane(4, 1);
        plane2[0] = new Plane(3, 2);
        plane2[1] = new Plane(4, 2);
        plane2[2] = new Plane(3, 2);

        plane[0].getplane(0).setLocation(0, 450);
        plane[0].getplane(1).setLocation(plane[0].width, 450);
        plane[0].getplane(2).setLocation(width - plane[0].getwidth(), 450);
        plane[0].getplane(3).setLocation(width - 2 * plane[0].getwidth(), 450);
        plane[1].getplane(0).setLocation(width / 2 - plane[1].getwidth() / 2, 480);
        plane[1].getplane(1).setLocation(width / 2 - plane[1].getwidth() * 3 / 2, 480);
        plane[1].getplane(2).setLocation(width / 2 + plane[1].getwidth() / 2, 480);
        plane[2].getplane(0).setLocation(100, 270);
        plane[2].getplane(1).setLocation(300, 270);
        plane[2].getplane(2).setLocation(width - 100 - plane[2].getwidth(), 260);
        plane[2].getplane(3).setLocation(width - 100 - plane[2].getwidth() * 2, 260);

        plane2[0].getplane(0).setLocation(width / 2 - plane[0].getwidth() / 2, 280);
        plane2[0].getplane(1).setLocation(width / 2 - plane[0].getwidth() * 3 / 2, 280);
        plane2[0].getplane(2).setLocation(width / 2 + plane[0].getwidth() / 2, 280);
        plane2[1].getplane(0).setLocation(483, 220);
        plane2[1].getplane(1).setLocation(683, 220);
        plane2[1].getplane(2).setLocation(0, 320);
        plane2[1].getplane(3).setLocation(1166, 320);
        plane2[2].getplane(0).setLocation(width / 2 - plane[2].getwidth() / 2, 430);
        plane2[2].getplane(1).setLocation(0, 430);
        plane2[2].getplane(2).setLocation(1166, 430);
    }

    @Override
    public void setbackground() {
        ImageIcon img = new ImageIcon("img/top.jpg");
        img.setImage(img.getImage().getScaledInstance(view.givewidth(), -1, Image.SCALE_DEFAULT));
        super.background = new JLabel(img);
        view.getRootPane().add(super.background, -1);
        background.setBounds(0, 0, view.givewidth(), view.giveheight());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
        ticks += 1;
        monster_ticks += 1;
        if (ticks >= 130) {
            state += 1;
            state %= 3;
            ticks = 0;
            for (CharacterProtype p : player) {
                p.fall = 1;
            }
            for (object o : oblist) {
                o.fallchange(1);
            }
        }
        if (monster_ticks % 250 == 80) {
            oblist.add(new enemy(new Random().nextInt(width - 400) + 200, new Random().nextInt(height - 450) + 30,
                    width, height, (new Random().nextInt(399) % 2) + 1));
        }
    }

    @Override
    public void render(Graphics g) {
        plane[state].render(g);
        plane2[state].render(g);
        super.render(g);
    }

    @Override
    protected void fallhandle(object o) {
        // TODO Auto-generated method stub
        super.fallhandle(o);
        for (int i = 0; i < plane_num[state]; i++) {
            if (o.getpos().y + o.getheight() >= plane[state].getplane(i).y
                    && o.getpos().y + o.getheight() - 30 < plane[state].getplane(i).y
                    && ((o.getpos().x > plane[state].getplane(i).x - 10
                            && o.getpos().x < plane[state].getplane(i).x + plane[state].width + 10)
                            || (o.getpos().x + o.getwidth() > plane[state].getplane(i).x - 10 && o.getpos().x
                                    + o.getwidth() < plane[state].getplane(i).x + plane[state].width + 10))) {
                o.fall = 0;
                o.getpos().setLocation(o.getpos().x, plane[state].getplane(i).y - o.getheight());
            }
        }
        for (int i = 0; i < plane2_num[state]; i++) {
            if (o.getpos().y + o.getheight() >= plane2[state].getplane(i).y
                    && o.getpos().y + o.getheight() - 30 < plane2[state].getplane(i).y
                    && ((o.getpos().x > plane2[state].getplane(i).x - 10
                            && o.getpos().x < plane2[state].getplane(i).x + plane2[state].width + 10)
                            || (o.getpos().x + o.getwidth() > plane2[state].getplane(i).x - 10 && o.getpos().x
                                    + o.getwidth() < plane2[state].getplane(i).x + plane2[state].width + 10))) {
                o.fall = 0;
                o.getpos().setLocation(o.getpos().x, plane2[state].getplane(i).y - o.getheight());
            }
        }
    }

    @Override
    protected void jumphandle(CharacterProtype p) {
        for (int i = 0; i < plane2_num[state]; i++) {
            if (p.getpos().y <= plane2[state].getplane(i).y && p.getpos().y + 40 >= plane2[state].getplane(i).y
                    && super.stayin(p.getpos(), plane2[state].getplane(i), plane2[state].width)) {
                p.fall = 1;
                p.getpos().setLocation(p.getpos().x, plane2[state].getplane(i).y + 30);
            }
        }
        super.jumphandle(p);
        int on = 0;
        for (int i = 0; i < plane_num[state]; i++) {
            if (p.getpos().y + 220 >= plane[state].getplane(i).y && p.getpos().y + 190 < plane[state].getplane(i).y
                    && super.stayin(p.getpos(), plane[state].getplane(i), plane[state].width)) {
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
                    p.getpos().setLocation(p.getpos().x, plane[state].getplane(i).y - 220);
                }
            }
        }

        for (int i = 0; i < plane2_num[state]; i++) {
            if (p.getpos().y + 220 >= plane2[state].getplane(i).y && p.getpos().y + 150 < plane2[state].getplane(i).y
                    && super.stayin(p.getpos(), plane2[state].getplane(i), plane2[state].width)) {
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
                    p.getpos().setLocation(p.getpos().x, plane2[state].getplane(i).y - 220);
                }
            }
        }
        if (on == 0 && p.getpos().y < 470 && p.fall == 0) {
            p.fall = 1;
        }
    }
}