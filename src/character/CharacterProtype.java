package character;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import MyObject.object;
import media.Audio;

public class CharacterProtype { // public 的變數會在外部(map)中被寫
    public int MaxHP, Hp, Maxmp, Mp, atk, speed, Player;
    public int atkhappen = 0;
    public int getdamage = 0;
    protected Point pos = new Point();
    protected int Ace = 0;
    protected int cd1 = 0;
    protected int cd2 = 0;
    protected int cd3 = 0;
    public int fall = 0; // 0 = 平行移動 1 = 下墜 2 = 上跳 表示不同狀態
    public int jumpupdistance;
    public int jumptime = 0;
    public int collisionhappen = 0;
    int def = 0;
    int walking = 0;
    int moving = 0;
    protected int direction; // 1 = Right, 2 = Left
    public Image nowstate;
    CharacterImage cimg;

    public CharacterProtype(int Ch, int P, int Hp, int Maxmp, int Mp, int atk, int speed) {
        cimg = new CharacterImage(Ch);
        direction = P;
        Player = P;
        if (P == 1) {
            pos.setLocation(50, 470);
        }
        if (P == 2) {
            pos.setLocation(1166, 470);
        }
        nowstate = cimg.getwalk(P, 0);
        this.MaxHP = Hp;
        this.Hp = Hp;
        this.Maxmp = Maxmp;
        this.Mp = Mp;
        this.atk = atk;
        this.speed = speed;
    }

    public void move(int direct) {
        direction = direct;
        moving = 1;
    }

    public void stop(int direct) {
        direction = direct;
        moving = 0;
        walking = 0;
        if (Ace == 0) {
            nowstate = cimg.getwalk(direction, walking);
        } else if (Ace == 1) {
            nowstate = cimg.getskillwalk(direction, walking);
        }
    }

    public void jump() {
        if (jumptime < 2 && def != 1) {
            fall = 2;
            walking = 0;
            jumpupdistance = -1;
            jumptime += 1;
            nowstate = cimg.getjump(direction, 0);
        }
    }

    public void fall() {
        if (def != 1) {
            fall = 1;
            pos.translate(0, 25);
            jumpupdistance = 4;
        }
        return;
    }

    public void def() {
        if (fall == 0) {
            def = 1;
            walking = 0;
        }
    }

    public void stopdef() {
        def = 0;
    }

    public void Mupdate() {
        if (moving == 1 && def == 0) {
            if (direction == 1) {
                pos.translate(speed, 0); // go right
            } else if (direction == 2) {
                pos.translate(-speed, 0); // go right
            }
            if (fall == 0) {
                walking += 1;
                walking %= 5;
                if (Ace == 0) {
                    nowstate = cimg.getwalk(direction, walking);
                } else {
                    nowstate = cimg.getskillwalk(direction, walking);
                }
            }
        }
        if (cd1 > 0) {
            cd1--;
        }
        if (cd2 > 0) {
            cd2--;
        }
        if (cd3 > 0) {
            cd3--;
        }
        if (Mp <= 0) {
            this.changeskill();
            Mp = 0;
        }
    }

    public void Hpchange(int value, int kind) {
        if (kind == 0) {
            if (def == 1) {
                Hp += (value + 50);
            } else {
                Hp += value;
            }
        } else if (kind == 1) {
            if (def == 1) {
                Hp += (value * 3 / 5);
            } else {
                Hp += value;
            }
        } else if (kind == 2) {
            if (def == 1) {
                Hp += (value - 50);
            } else {
                Hp += value;
            }
        } else if (kind == 3) {
            if (def == 1) {
                Hp += (value + 50);
            }
            if (fall != 0) {
                Hp += (value - 50);
            } else {
                Hp += value;
            }
        } else if (kind == 4) {
            if (def == 1) {
                Hp += (value + 15);
            } else {
                Hp += value;
            }
        } else if (kind == 5) {
            if (def == 1) {
                value += 10;
            }
            if (Mp >= 1000) {
                Hp += (value - 10);
            } else {
                Hp += value;
            }
        } else {
            Hp += value;
        }
        if (Hp > MaxHP) {
            Hp = MaxHP;
        }
    }

    public void Mpchange(int value, int kind) {
        if (kind <= 1) {
            if (def == 1) {
                Mp += (value / 2);
            } else {
                Mp += value;
            }
        } else if (kind == 2) {
            if (def == 0) {
                Mp += value;
            }
        } else if (kind == 3) {
            if (def == 0) {
                Mp += value;
            } else {
                Mp -= value;
            }
        } else {
            Mp += value;
        }
        if (Mp > Maxmp) {
            Mp = Maxmp;
        }
    }

    public void changeskill() {
        Ace += 1;
        Ace %= 2;
        cd3 = 0;
        if (Ace == 0) {
            nowstate = cimg.getwalk(direction, walking);
        } else if (Ace == 1) {
            nowstate = cimg.getskillwalk(direction, walking);
            skillvocal();
        }
    }

    public void setdamagestate(int i) {
        getdamage = i;
    }

    public CharacterImage getImage() {
        return cimg;
    }

    public Point getpos() {
        return pos;
    }

    public int getdirect() {
        return direction;
    }

    public int getAce() {
        return Ace;
    }

    public void render(Graphics g) {
        if (nowstate == null) {
            System.out.print("image error\n");
        }
        if (Player == 1) {
            g.drawImage(cimg.geticon(), 10, 10, 70, 70, null);
            g.setColor(Color.RED);
            g.fillRect(90, 15, 200 * Hp / MaxHP, 20);
            g.setColor(Color.getHSBColor((float) 0.56, (float) 0.71, (float) 0.9));
            g.fillRect(90, 45, 200 * Mp / Maxmp, 20);
        } else if (Player == 2) {
            g.drawImage(cimg.geticon(), 1286, 10, 70, 70, null);
            g.setColor(Color.RED);
            g.fillRect(1276 - 200 * Hp / MaxHP, 15, 200 * Hp / MaxHP, 20);
            g.setColor(Color.getHSBColor((float) 0.56, (float) 0.71, (float) 0.9));
            g.fillRect(1276 - 200 * Mp / Maxmp, 45, 200 * Mp / Maxmp, 20);
        }
        if (def == 1) {
            g.drawImage(cimg.getdef(direction), pos.x, pos.y, 150, 240, null);
        } else if (getdamage % 2 == 0) {
            if (atkhappen == 0) {
                g.drawImage(nowstate, pos.x, pos.y, 150, 240, null);
            } else {
                if (Ace == 1) {
                    g.drawImage(cimg.getskillwalk(direction, 0), pos.x, pos.y, 150, 240, null);
                } else {
                    g.drawImage(cimg.getatk(direction), pos.x, pos.y, 150, 240, null);
                }
                atkvocal();
                atkhappen = 0;
            }
        }
        if (getdamage >= 3) {
            getdamage = 0;
        } else if (getdamage > 0) {
            getdamage += 1;
        }
    }

    public void WinVocal() {
        /* wait override */
    }

    public void skill1(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        /* wait override */
    }

    public void skill2(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        /* wait override */
    }

    public void skill3(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        /* wait override */
    }

    public void atkvocal() {
        /* wait override */
    }

    public void skillvocal() {
        /* wait override */
    }
}
