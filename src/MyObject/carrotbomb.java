package MyObject;

import java.awt.*;

import character.CharacterProtype;

public class carrotbomb extends object {
    private int width = 117;
    private int height = 27;
    private int width2 = 180;
    private int height2 = 180;
    private int user, direct;
    private Image img, img2;
    private int state = 0;
    CharacterProtype defenser;
    private Point target = new Point(0, 0);

    public carrotbomb(int x, int y, int wx, int wy, int user, int direct, Image img, Image img2) {
        super(x, y, wx, wy);
        this.user = user;
        this.direct = direct;
        this.img = img;
        this.img2 = img2;
        fall = 0;
    }

    public boolean borderhandle() { // 是否出界
        if (pos.x < 0 || pos.x + width > WorldWidth) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        for (CharacterProtype p : player) {
            if (p.Player != user && state == 0) {
                if (direct == 1) {
                    pos.translate(24, 0);
                } else if (direct == 2) {
                    pos.translate(-24, 0);
                }
                if (p.getpos().y > pos.y) {
                    pos.translate(0, 7);
                } else if (p.getpos().y < pos.y - 80) {
                    pos.translate(0, -7);
                }
                if ((pos.y + height > p.getpos().y + 30 && pos.y + height < p.getpos().y + 210)
                        || (pos.y > p.getpos().y + 30 && pos.y < p.getpos().y + 210)) {
                    if (direct == 1) {
                        if ((pos.x + width + 20 > p.getpos().x && pos.x + width + 20 < p.getpos().x + 150)
                                || (pos.x + width - 30 > p.getpos().x && pos.x + width - 30 < p.getpos().x + 150)) {
                            state = 1;
                            defenser = p;
                            target.setLocation(p.getpos().x + 20, p.getpos().y + 50);
                        }
                    } else if (direct == 2) {
                        if ((pos.x + 30 > p.getpos().x && pos.x + 30 < p.getpos().x + 150)
                                || (pos.x - 20 > p.getpos().x && pos.x - 20 < p.getpos().x + 150)) {
                            state = 1;
                            defenser = p;
                            target.setLocation(p.getpos().x + 20, p.getpos().y + 50);
                        }
                    }
                }
            }
        }
        if (state >= 4) {
            defenser.setdamagestate(1);
            defenser.Hpchange(-150, 3);
            defenser.Mpchange(40, 1);
            return true;
        }
        if (borderhandle()) {
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (state == 0) {
            g.drawImage(img, super.pos.x, super.pos.y, width, height, null);
        } else if (state > 0) { // 爆炸動畫顯示， state >= 3後被update移除
            g.drawImage(img2, target.x, target.y, width2, height2, null);
            state += 1;
        }
    }
}
