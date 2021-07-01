package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class donchan extends object {
    private int width = 90;
    private int height = 120;
    private int width2 = 130;
    private int height2 = 120;
    private int user, direct;
    private Image img, img2;
    private int state = 0;
    CharacterProtype defenser;
    int tick = 0;

    public donchan(int x, int y, int wx, int wy, int user, int direct, Image img, Image img2) {
        super(x, y, wx, wy);
        this.user = user;
        this.direct = direct;
        this.img = img;
        this.img2 = img2;
        fall = 0;
        if (pos.x + width > wx) {
            pos.x = wx - width;
        }
        if (pos.x < 0) {
            pos.x = 0;
        }
    }

    public boolean borderhandle() { // 是否出界
        if (pos.x < 0 || pos.x + width > WorldWidth) {
            return true;
        } else if (pos.y < -50 || pos.y + height > WorldWidth) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        if (state == 0) {
            pos.translate(0, 15);
        } else if (direct == 1 && state == 1) {
            pos.translate(20, 0);
        } else if (direct == 2 && state == 1) {
            pos.translate(-20, 0);
        }
        boolean ans = false;
        for (CharacterProtype p : player) {
            if (p.Player != user) { // 非使用者接觸
                if (state == 0) { // 第1型態
                    if (pos.y > p.getpos().y + 60 && pos.y < p.getpos().y + 180) {
                        state = 1;
                        pos.x -= 15;
                        pos.y -= 15;
                        if ((pos.x + width > p.getpos().x && pos.x + width < p.getpos().x + 150)
                                || (pos.x > p.getpos().x && pos.x < p.getpos().x + 150)) {
                            p.setdamagestate(1);
                            p.Hpchange(-80, 0);
                            p.Mpchange(-60, 1);
                        }
                    }
                } else if (state == 1) { // 第2型態
                    if ((pos.y + height2 > p.getpos().y + 40 && pos.y + height2 < p.getpos().y + 200) // 接觸判定
                            || (pos.y + 40 > p.getpos().y + 40 && pos.y + 40 < p.getpos().y + 200)) {
                        if (direct == 1) {
                            if (pos.x + width2 > p.getpos().x && pos.x + width2 < p.getpos().x + 150) {
                                ans |= true;
                                p.setdamagestate(1);
                                p.Hpchange(-100, 2);
                                p.Mpchange(60, 2);
                            }
                        } else if (direct == 2) {
                            if (pos.x > p.getpos().x && pos.x < p.getpos().x + 150) {
                                ans |= true;
                                p.setdamagestate(1);
                                p.Hpchange(-100, 2);
                                p.Mpchange(60, 2);
                            }
                        }
                    }
                }
            }
        }
        ans |= borderhandle();
        return ans;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (state == 0) {
            g.drawImage(img, pos.x, pos.y, width, height, null);
        } else if (state == 1) { // 變形
            g.drawImage(img2, pos.x, pos.y, width2, height2, null);
        }
    }

}
