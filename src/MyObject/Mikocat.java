package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class Mikocat extends object {
    private int width = 100;
    private int height = 120;
    private int width2 = 180;
    private int height2 = 200;
    private int user, direct;
    private Image img, img2;
    private int state = 0;
    private Point pos2 = new Point(0, 0);
    CharacterProtype defenser;
    int tick = 0;

    public Mikocat(int x, int y, int wx, int wy, int user, int direct, Image img, Image img2) {
        super(x, y, wx, wy);
        this.user = user;
        this.direct = direct;
        this.img = img;
        this.img2 = img2;
        if (this.direct == 1) {
            pos2.setLocation(pos.x + 90, y - 100);
        } else if (this.direct == 2) {
            pos2.setLocation(pos.x - 170, y - 100);
        }
        fall = 0;
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        tick += 1;
        if (tick == 12) {
            state = 1;
        }
        for (CharacterProtype p : player) {
            if (p.Player != user) { // 非使用者接觸
                if (state == 0) { // 第1型態
                    if ((pos.y + height > p.getpos().y + 40 && pos.y + height < p.getpos().y + 200) // 接觸判定
                            || (pos.y > p.getpos().y + 40 && pos.y < p.getpos().y + 200)) {
                        if ((pos.x + width > p.getpos().x && pos.x + width < p.getpos().x + 150)
                                || (pos.x > p.getpos().x && pos.x < p.getpos().x + 150)) {
                            state = 2;
                            if (tick < 8) {
                                tick = 8;
                            }
                            p.setdamagestate(1);
                            p.Hpchange(-90, 0);
                            p.Mpchange(50, 1);
                        }
                    }
                } else if (state == 1) { // 第2型態
                    if ((pos2.y + height2 > p.getpos().y + 20 && pos2.y + height2 < p.getpos().y + 240) // 接觸判定
                            || (pos2.y > p.getpos().y + 20 && pos2.y < p.getpos().y + 240)) {
                        if ((pos2.x + width2 > p.getpos().x && pos2.x + width2 < p.getpos().x + 150)
                                || (pos2.x > p.getpos().x && pos2.x < p.getpos().x + 150)) {
                            state = 2;
                            p.setdamagestate(1);
                            p.Hpchange(-220, 4);
                            p.Mpchange(50, 3);
                        }
                    }
                }
            }
        }
        if (tick >= 20) {
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(img, pos.x, pos.y, width, height, null);
        if (state > 0 && tick % 3 != 0) { // 變形
            g.drawImage(img2, pos2.x, pos2.y, width2, height2, null);
        }
    }

}
