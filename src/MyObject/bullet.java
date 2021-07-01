package MyObject;

import character.CharacterProtype;
import java.awt.*;
import java.util.Random;

public class bullet extends object {
    private int width = 30;
    private int height = 15;
    private int user, direct;
    private Image img;
    private int speed;

    public bullet(int x, int y, int wx, int wy, int user, int direct, int speed, Image img) {
        super(x, y, wx, wy);
        this.user = user;
        this.direct = direct;
        this.speed = speed;
        this.img = img;
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
        if (direct == 1) {
            pos.translate(speed, 0);
        } else if (direct == 2) {
            pos.translate(-speed, 0);
        }
        boolean ans = false;
        for (CharacterProtype p : player) {
            if ((pos.y + height > p.getpos().y + 20 && pos.y + height < p.getpos().y + 220)
                    || (pos.y > p.getpos().y + 20 && pos.y < p.getpos().y + 220)) {
                if (direct == 1) {
                    if (pos.x + width > p.getpos().x && pos.x + width < p.getpos().x + 150) {
                        ans |= true;
                        if (p.Player != user) {
                            p.setdamagestate(1);
                            p.Hpchange(-35, 5);
                            p.Mpchange(-20, 1);
                        }
                    }
                } else if (direct == 2) {
                    if (pos.x > p.getpos().x && pos.x < p.getpos().x + 150) {
                        ans |= true;
                        if (p.Player != user) {
                            p.setdamagestate(1);
                            p.Hpchange(-35, 5);
                            p.Mpchange(-20, 1);
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
        g.drawImage(img, super.pos.x, super.pos.y, width, height, null);
    }
}
