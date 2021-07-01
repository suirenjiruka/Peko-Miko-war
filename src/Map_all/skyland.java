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
import MyObject.wing;

public class skyland extends Map {
    Plane plane = new Plane(5, 1);
    Plane plane2 = new Plane(8, 2);
    int direct = 1;
    private Image wing, wing2;

    public skyland(Gameview view, Canvas panel, CharacterProtype p1, CharacterProtype p2) {
        super(view, panel, p1, p2);
        setbackground();
        plane.getplane(0).setLocation(0, 430);
        plane.getplane(1).setLocation(plane.width * 2, 430);
        plane.getplane(2).setLocation(plane.width * 3, 430);
        plane.getplane(3).setLocation(plane.width * 4, 430);
        plane.getplane(4).setLocation(plane.width * 6, 430);

        plane2.getplane(0).setLocation(483, 670);
        plane2.getplane(1).setLocation(683, 670);
        plane2.getplane(2).setLocation(10, 685);
        plane2.getplane(3).setLocation(width - 210, 685);
        plane2.getplane(4).setLocation(plane.width, 430);
        plane2.getplane(5).setLocation(plane.width * 5, 430);
        plane2.getplane(6).setLocation(480, 200);
        plane2.getplane(7).setLocation(680, 200);
        try {
            wing = ImageIO.read(new File("img/wing.png"));
            wing2 = ImageIO.read(new File("img/wing2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setbackground() {
        ImageIcon img = new ImageIcon("img/天空背景.jpg");
        img.setImage(img.getImage().getScaledInstance(view.givewidth(), -1, Image.SCALE_DEFAULT));
        super.background = new JLabel(img);
        view.getRootPane().add(super.background, -1);
        background.setBounds(0, 0, view.givewidth(), view.giveheight());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
        if (direct == 1) {
            plane2.getplane(6).translate(5, 0);
            plane2.getplane(7).translate(5, 0);
        } else if (direct == 2) {
            plane2.getplane(6).translate(-5, 0);
            plane2.getplane(7).translate(-5, 0);
        }
        if (plane2.getplane(7).x + 200 > width) {
            direct = 2;
        }
        if (plane2.getplane(6).x < 0) {
            direct = 1;
        }
        checkmove();
        if (new Random().nextInt(80) == 3) {
            oblist.add(new wing(new Random().nextInt(height - 130) + 50, new Random().nextInt(height - 60) + 20, width,
                    height, wing, wing2));
        }
    }

    private void checkmove() {
        for (CharacterProtype p : player) {
            if (p.getpos().y + 240 < 230 && p.getpos().y + 240 > 190) {
                if (stayin(p.getpos(), plane2.getplane(6), plane2.width)
                        || stayin(p.getpos(), plane2.getplane(7), plane2.width)) {
                    if (direct == 1) {
                        p.getpos().translate(5, 0);
                    } else if (direct == 2) {
                        p.getpos().translate(-5, 0);
                    }
                }
            }
        }
        for (object o : oblist) { // 有點醜(有點懶得精簡)，簡而言之，是判斷掉落的物品是否隨平台移動
            for (int i = 6; i <= 7; i++) {
                if (o.getpos().y + o.getheight() >= plane2.getplane(i).y
                        && o.getpos().y + o.getheight() - 30 < plane2.getplane(i).y
                        && ((o.getpos().x > plane2.getplane(i).x - 10
                                && o.getpos().x < plane2.getplane(i).x + plane2.width + 10)
                                || (o.getpos().x + o.getwidth() > plane2.getplane(i).x - 10
                                        && o.getpos().x + o.getwidth() < plane2.getplane(i).x + plane2.width + 10))) {
                    if (direct == 1) {
                        o.getpos().translate(5, 0);
                    } else if (direct == 2) {
                        o.getpos().translate(-5, 0);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        plane.render(g);
        plane2.render(g);
        super.render(g);
    }

    @Override
    protected void fallhandle(object o) {
        // TODO Auto-generated method stub
        o.getpos().translate(0, 25);
        for (int i = 0; i < 5; i++) {
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
        for (int i = 0; i < 8; i++) {
            if (o.getpos().y + o.getheight() >= plane2.getplane(i).y
                    && o.getpos().y + o.getheight() - 30 < plane2.getplane(i).y
                    && ((o.getpos().x > plane2.getplane(i).x - 10
                            && o.getpos().x < plane2.getplane(i).x + plane2.width + 10)
                            || (o.getpos().x + o.getwidth() > plane2.getplane(i).x - 10
                                    && o.getpos().x + o.getwidth() < plane2.getplane(i).x + plane2.width + 10))) {
                o.fall = 0;
                o.getpos().setLocation(o.getpos().x, plane2.getplane(i).y - o.getheight());
            }
        }
        if (o.getpos().y + o.getheight() > 710) {
            o.getpos().setLocation(new Random().nextInt(height - 130) + 50, new Random().nextInt(height - 60) + 20);
        }
    }

    @Override
    protected void jumphandle(CharacterProtype p) {
        if (p.fall == 2) {
            p.jumpupdistance += 1;
            p.getpos().translate(0, -27);
            p.nowstate = p.getImage().getjump(p.getdirect(), p.jumpupdistance / 2); // 設定image
            if (p.jumpupdistance >= 5) {
                p.fall = 1;
            }
            for (int i = 0; i < 8; i++) {
                if (p.getpos().y <= plane2.getplane(i).y && p.getpos().y + 40 >= plane2.getplane(i).y
                        && super.stayin(p.getpos(), plane2.getplane(i), plane2.width)) {
                    p.fall = 1;
                    p.getpos().setLocation(p.getpos().x, plane2.getplane(i).y + 30);
                }
            }
        } else if (p.fall == 1) {
            p.getpos().translate(0, 25);
            if (p.jumpupdistance >= 0) {
                p.nowstate = p.getImage().getjump(p.getdirect(), p.jumpupdistance / 2);
            }
            p.jumpupdistance -= 1;
            if (p.getpos().y + 240 > 840) {
                p.fall = 0;
                p.jumptime = 0;
                p.nowstate = p.getImage().getwalk(p.getdirect(), 0);
                p.getpos().setLocation(620, 460);
                p.setdamagestate(1);
                p.Hpchange(-100, 6);
            }
        }
        int on = 0;
        for (int i = 0; i < 5; i++) {
            if (p.getpos().y + 220 >= plane.getplane(i).y && p.getpos().y + 190 < plane.getplane(i).y
                    && super.stayin(p.getpos(), plane.getplane(i), plane.width)) {
                if (p.fall == 0) {
                    on += 1;
                } else if (p.fall == 1) {
                    on += 1;
                    p.fall = 0;
                    if (p.jumptime > 0) {
                        p.jumptime = 0;
                    }
                    if (p.getAce() == 0) {
                        p.nowstate = p.getImage().getwalk(p.getdirect(), 0);
                    } else if (p.getAce() == 1) {
                        p.nowstate = p.getImage().getskillwalk(p.getdirect(), 0);
                    }
                    p.getpos().setLocation(p.getpos().x, plane.getplane(i).y - 220);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if (p.getpos().y + 220 >= plane2.getplane(i).y && p.getpos().y + 150 < plane2.getplane(i).y
                    && super.stayin(p.getpos(), plane2.getplane(i), plane2.width)) {
                if (p.fall == 0) {
                    on += 1;
                } else if (p.fall == 1) {
                    on += 1;
                    p.fall = 0;
                    if (p.jumptime > 0) {
                        p.jumptime = 0;
                    }
                    if (p.getAce() == 0) {
                        p.nowstate = p.getImage().getwalk(p.getdirect(), 0);
                    } else if (p.getAce() == 1) {
                        p.nowstate = p.getImage().getskillwalk(p.getdirect(), 0);
                    }
                    p.getpos().setLocation(p.getpos().x, plane2.getplane(i).y - 220);
                }
            }
        }
        if (on == 0 && p.fall == 0) {
            p.fall = 1;
        }
    }
}
