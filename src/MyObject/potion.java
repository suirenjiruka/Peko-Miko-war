package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class potion extends object {
    private int width = 30;
    private int height = 40;
    private int kind;
    private int ticks = 0;
    private Image img;

    public potion(int x, int y, int wx, int wy, int kind, Image img) {
        super(x, y, wx, wy);
        this.kind = kind;
        this.img = img;
        fall = 1;
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
        ticks += 1;
        boolean ans = false;
        for (CharacterProtype p : player) {
            if ((pos.y + height > p.getpos().y + 20 && pos.y + height < p.getpos().y + 220)
                    || (pos.y > p.getpos().y + 20 && pos.y < p.getpos().y + 220)) {
                if ((pos.x + width > p.getpos().x && pos.x + width < p.getpos().x + 150)
                        || (pos.x > p.getpos().x && pos.x < p.getpos().x + 150)) {
                    if (fall == 0) {
                        ans |= true;
                        if (kind == 1)
                            p.Hpchange(300, 6);
                        else if (kind == 2)
                            p.Mpchange(450, 6);
                    }
                }
            }
        }
        if (ticks >= 100) {
            return true;
        }
        return ans;
    }

    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(img, super.pos.x, super.pos.y, width, height, null);
    }
}
