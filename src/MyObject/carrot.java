package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class carrot extends object {
    private int width = 75;
    private int height = 30;
    private int user, direct;
    private Image img;

    public carrot(int x, int y, int wx, int wy, int user, int direct, Image img) {
        super(x, y, wx, wy);
        this.user = user;
        this.direct = direct;
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
        if (direct == 1) { // 移動
            pos.translate(40, 0);
        } else if (direct == 2) {
            pos.translate(-40, 0);
        }
        boolean ans = false;
        for (CharacterProtype p : player) {
            if ((pos.y + height > p.getpos().y + 20 && pos.y + height < p.getpos().y + 220)
                    // 命中判定，如果助教嫌太長太醜可以跳過，基本上所有物件都是相似的
                    || (pos.y > p.getpos().y + 20 && pos.y < p.getpos().y + 220)) {
                if (direct == 1) {
                    if ((pos.x + width + 5 > p.getpos().x && pos.x + width + 5 < p.getpos().x + 150)
                            || (pos.x + width - 15 > p.getpos().x && pos.x + width - 15 < p.getpos().x + 150)) {
                        ans |= true;
                        if (p.Player != user) {
                            p.setdamagestate(1);
                            p.Hpchange(-80, 1);
                            p.Mpchange(-70, 1);
                        }
                    }
                } else if (direct == 2) {
                    if ((pos.x - 5 > p.getpos().x && pos.x - 5 < p.getpos().x + 150)
                            || (pos.x + 15 > p.getpos().x && pos.x + 15 < p.getpos().x + 150)) {
                        ans |= true;
                        if (p.Player != user) {
                            p.setdamagestate(1);
                            p.Hpchange(-80, 1);
                            p.Mpchange(-70, 1);
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
