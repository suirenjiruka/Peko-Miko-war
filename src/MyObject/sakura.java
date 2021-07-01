package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class sakura extends object {
    private int width = 40;
    private int height = 40;
    private int width2 = 180;
    private int height2 = 180;
    private int user;
    private Image img, img2;
    private int state = 0;
    CharacterProtype defenser;
    int tick = 0;

    public sakura(int x, int y, int wx, int wy, int user, Image img, Image img2) {
        super(x, y, wx, wy);
        this.user = user;
        this.img = img;
        this.img2 = img2;
        fall = 1;
        if (pos.x + width > wx) {
            pos.x = wx - width;
        }
        if (pos.x < 0) {
            pos.x = 0;
        }
    }

    @Override
    public int getwidth() {
        return width;
    }

    @Override
    public int getheight() {
        return height;
    }

    @Override
    public void fallchange(int i) {
        // TODO Auto-generated method stub
        fall = i;
        return;
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        tick += 1;
        for (CharacterProtype p : player) {
            if (p.Player != user && state == 0 && tick >= 15) {
                if ((pos.y - 25 > p.getpos().y + 120 && pos.y - 25 < p.getpos().y + 220)
                        || (pos.y + 20 > p.getpos().y + 120 && pos.y + 20 < p.getpos().y + 220)) {
                    if ((pos.x + 45 > p.getpos().x && pos.x + 45 < p.getpos().x + 150)
                            || (pos.x - 5 > p.getpos().x && pos.x - 5 < p.getpos().x + 150)) {
                        state = 1;
                        defenser = p;
                    }
                }
            }
        }
        if (state >= 4) {
            defenser.setdamagestate(1);
            defenser.Hpchange(-300, 1);
            defenser.Mpchange(125, 1);
            return true;
        } else if (tick >= 140) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (state == 0) {
            g.drawImage(img, super.pos.x, super.pos.y + 5, width, height, null);
        } else if (state > 0) { // 爆炸動畫顯示， state >= 3後被update移除
            g.drawImage(img2, super.pos.x - 70, super.pos.y - 130, width2, height2, null);
            state += 1;
        }
    }
}
