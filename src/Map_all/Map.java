package Map_all;

import javax.swing.JLabel;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import view.Gameview;
import view.Gameview.Canvas;
import Gamecontrol.Game;
import character.CharacterProtype;
import media.Audio;
import MyObject.object;
import MyObject.potion;

public class Map {
    public Gameview view;
    public Canvas panel;
    public CharacterProtype[] player = new CharacterProtype[2];
    protected ArrayList<object> oblist = new ArrayList<object>();
    private Image img1, img2, potion1, potion2;
    CollisionHandler collisionhandler = new CollisionHandler();
    JLabel background;
    int width, height;
    int tick = 0;
    int end = 0; // 0未結束， 1結束動畫， 2結束

    public Map(Gameview view, Canvas panel, CharacterProtype p1, CharacterProtype p2) {
        this.view = view;
        this.panel = panel;
        player[0] = p1;
        player[1] = p2;
        setsize(view.givewidth(), view.giveheight());
        try {
            img1 = ImageIO.read(new File("img/ending1.png"));
            img2 = ImageIO.read(new File("img/ending2.png"));
            potion1 = ImageIO.read(new File("img/potion1.png"));
            potion2 = ImageIO.read(new File("img/potion2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setbackground() {
        /* wait override */
    }

    public void setsize(int w, int h) {
        this.width = w;
        this.height = h - 40;
    }

    public void removebackground() {
        view.getRootPane().remove(background);
    }

    public int getend() {
        return end;
    }

    public void update() {
        // wait override for different map
        if (end == 0) {
            for (CharacterProtype p : player) {
                p.Mupdate();
                jumphandle(p);
                if (p.Hp <= 0) {
                    end = 1;
                }
            }
            collisionhandler.collisionhandle(player[0], player[1]);
            collisionhandler.borderhandle(player[0], width, height);
            collisionhandler.borderhandle(player[1], width, height);
        }
        for (java.util.Iterator<object> iter = oblist.iterator(); iter.hasNext();) {
            object o = iter.next();
            if (o.fall == 1) {
                fallhandle(o);
            }
            if (o.update(player))
                iter.remove();
        }
        if (end == 1) {
            tick += 1;
            if (tick > 80) {
                end = 2;
            }
        }
        if (new Random().nextInt(180) == 5) {
            oblist.add(new potion(new Random().nextInt(width - 100) + 30, new Random().nextInt(height - 40) + 20, width,
                    height, 1, potion1));
        }
        if (new Random().nextInt(150) == 3) {
            oblist.add(new potion(new Random().nextInt(width - 100) + 30, new Random().nextInt(height - 40) + 20, width,
                    height, 2, potion2));
        }
    }

    public void render(Graphics g) {
        for (CharacterProtype p : player) {
            if (p.Hp > 0) {
                p.render(g);
            }
        }
        for (object O : oblist) {
            O.render(g);
        }
        if (end == 1) {
            if (player[0].Hp <= 0) {
                g.drawImage(img2, 250, 150, 800, 400, null);
                if (tick == 1) {
                    player[1].WinVocal();
                }
            } else if (player[1].Hp <= 0) {
                g.drawImage(img1, 250, 150, 800, 400, null);
                if (tick == 1) {
                    player[0].WinVocal();
                }
            }
        }
    }

    protected void fallhandle(object o) {
        o.getpos().translate(0, 25);
        if (o.getpos().y + o.getheight() > 710) {
            o.fall = 0;
            o.getpos().setLocation(o.getpos().x, 710 - o.getheight());
        }
    }

    protected void jumphandle(CharacterProtype p) {
        if (p.fall == 2) {
            p.jumpupdistance += 1;
            p.getpos().translate(0, -27);
            p.nowstate = p.getImage().getjump(p.getdirect(), p.jumpupdistance / 2); // 設定image
            if (p.jumpupdistance >= 5) {
                p.fall = 1;
            }
        } else if (p.fall == 1) {
            p.getpos().translate(0, 25);
            if (p.jumpupdistance >= 0) {
                p.nowstate = p.getImage().getjump(p.getdirect(), p.jumpupdistance / 2);
            }
            p.jumpupdistance -= 1;
            if (p.getpos().y + 240 > 710) {
                p.fall = 0;
                p.jumptime = 0;
                p.nowstate = p.getImage().getwalk(p.getdirect(), 0);
                p.getpos().setLocation(p.getpos().x, 470);
            }
        }
    }

    protected boolean stayin(Point p, Point plane, int length) {
        boolean ans = false;
        if ((p.x + 40 > plane.getX() && p.x + 40 < plane.x + length)
                || (p.x + 110 > plane.getX() && p.x + 110 < plane.x + length)) {
            ans = true;
        }
        return ans;
    }

    public void ATKhandle(int atker, int defenser) {
        player[atker].atkhappen = 1;
        if (player[atker].getAce() == 0) {
            if ((player[atker].getpos().y + 90 > player[defenser].getpos().y
                    && player[atker].getpos().y + 90 < player[defenser].getpos().y + 220)
                    || (player[atker].getpos().y + 130 > player[defenser].getpos().y
                            && player[atker].getpos().y + 130 < player[defenser].getpos().y + 220)) {
                if (player[atker].getdirect() == 1) {
                    if (player[defenser].getpos().x < player[atker].getpos().x + 175
                            && player[defenser].getpos().x > player[atker].getpos().x + 120) {
                        player[defenser].Hpchange(-player[atker].atk, 0);
                        player[defenser].Mpchange(75, 0);
                        player[defenser].setdamagestate(1);
                    }
                } else if (player[atker].getdirect() == 2) {
                    if (player[defenser].getpos().x + 175 > player[atker].getpos().x
                            && player[defenser].getpos().x + 120 < player[atker].getpos().x) {
                        player[defenser].Hpchange(-player[atker].atk, 0);
                        player[defenser].Mpchange(75, 0);
                        player[defenser].setdamagestate(1);
                    }
                }
            }
        } else if (player[atker].getAce() == 1) {
            player[atker].skill3(oblist, player, width, height);
        }
    }

    public void callskill(int P, int sk) {
        if (sk == 1) {
            player[P - 1].skill1(oblist, player, width, height);
        } else if (sk == 2) {
            player[P - 1].skill2(oblist, player, width, height);
        } else if (sk == 3) {
            player[P - 1].changeskill();
        }
    }
}

class CollisionHandler {
    public void collisionhandle(CharacterProtype p1, CharacterProtype p2) {
        int happen = 0;
        if (p1.getpos().x > p2.getpos().x && p1.getpos().x <= p2.getpos().x + 140 // p1從右側撞p2
                && p1.getpos().y + 220 > p2.getpos().y + 50 && p1.getpos().y + 50 < p2.getpos().y + 220) {
            p1.getpos().translate((p2.getpos().x + 150 - p1.getpos().x) / 2 + 1, 0);
            p2.getpos().translate(-((p2.getpos().x + 150 - p1.getpos().x) / 2 + 1), 0);
            if (p1.collisionhappen == 0) {
                if (p1.getpos().y < p2.getpos().y - 160) {
                    p2.Hpchange(-20, 4);
                    p2.getdamage = 1;
                }
                if (p2.getpos().y < p1.getpos().y - 160) {
                    p1.Hpchange(-20, 4);
                    p1.getdamage = 1;
                }
            }
            happen = 1;
        } else if (p2.getpos().x > p1.getpos().x && p2.getpos().x <= p1.getpos().x + 140
                && p1.getpos().y + 220 > p2.getpos().y + 50 && p1.getpos().y + 50 < p2.getpos().y + 220) {
            p2.getpos().translate((p1.getpos().x + 150 - p2.getpos().x) / 2 + 1, 0);
            p1.getpos().translate(-((p1.getpos().x + 150 - p2.getpos().x) / 2 + 1), 0);
            if (p2.collisionhappen == 0) {
                if (p1.getpos().y < p2.getpos().y - 150) {
                    p2.Hpchange(-20, 4);
                    p2.getdamage = 1;
                }
                if (p2.getpos().y < p1.getpos().y - 150) {
                    p1.Hpchange(-20, 4);
                    p1.getdamage = 1;
                }
            }
            happen = 1;
        }
        if (happen == 1) {
            p1.collisionhappen += 1;
            p2.collisionhappen += 1;
            if (p1.getpos().y < p2.getpos().y - 50) {
                if (p1.collisionhappen < 3) {
                    p1.getpos().translate(0, -35);
                    if (p1.collisionhappen == 2) {
                        p1.jumptime -= 1;
                    }
                } else {
                    p1.jumptime += 1;
                    if (p1.getpos().x > p2.getpos().x) {
                        p1.getpos().translate(50, 30);
                    } else if (p1.getpos().x < p2.getpos().x) {
                        p1.getpos().translate(-50, 30);
                    }
                    p1.collisionhappen = 0;
                    p2.collisionhappen = 0;
                }
            } else if (p1.getpos().y - 50 > p2.getpos().y) {
                if (p2.collisionhappen < 3) {
                    p2.getpos().translate(0, -35);
                    if (p2.collisionhappen == 2) {
                        p2.jumptime -= 1;
                    }
                } else {
                    p2.jumptime -= 1;
                    if (p1.getpos().x > p2.getpos().x) {
                        p2.getpos().translate(-50, 30);
                    } else if (p1.getpos().x < p2.getpos().x) {
                        p2.getpos().translate(50, 30);
                    }
                    p1.collisionhappen = 0;
                    p2.collisionhappen = 0;
                }
            }
        } else {
            p1.collisionhappen = 0;
            p2.collisionhappen = 0;
        }
    }

    public void borderhandle(CharacterProtype P, int width, int height) {
        if (P.getpos().x < -30) {
            P.getpos().setLocation(-30, P.getpos().y);
        }
        if (P.getpos().x > width - 120) {
            P.getpos().setLocation(width - 120, P.getpos().y);
        }
        if (P.getpos().y < -30) {
            P.getpos().setLocation(P.getpos().x, -30);
        }
        if (P.getpos().y > 710) {
            P.getpos().setLocation(P.getpos().x, 470);
        }
    }
}
