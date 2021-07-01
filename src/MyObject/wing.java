package MyObject;

import character.CharacterProtype;
import java.awt.*;

public class wing extends object {
    private int width = 69;
    private int height = 44;
    private int state = 0;
    private int ticks = 0;
    private Image img, img2;
    CharacterProtype effecter;

    public wing(int x, int y, int wx, int wy, Image img, Image img2) {
        super(x, y, wx, wy);
        this.img = img;
        this.img2 = img2;
        fall = 0;
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        ticks += 1;
        for (CharacterProtype p : player) {
            if (state == 0) {
                if ((pos.y + height > p.getpos().y + 20 && pos.y + height < p.getpos().y + 220)
                        || (pos.y > p.getpos().y + 20 && pos.y < p.getpos().y + 220)) {
                    if ((pos.x + width > p.getpos().x && pos.x + width < p.getpos().x + 150)
                            || (pos.x > p.getpos().x && pos.x < p.getpos().x + 150)) {
                        if (p.jumptime >= 0) {
                            effecter = p;
                            p.jumptime = -2000;
                            state = 1;
                            ticks = -10;
                        } else {
                            ticks = 60;
                        }
                    }
                }
            }
        }
        if (ticks >= 60) {
            if (state == 1 && effecter.jumptime < 0) {
                System.out.print(effecter.jumptime);
                effecter.jumptime = 2;
            }
            return true;
        }
        return false;
    }

    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (state == 0) {
            g.drawImage(img, super.pos.x, super.pos.y, width, height, null);
        }
        if (state == 1) {
            if (effecter.Player == 1) {
                g.drawImage(img2, 310, 30, 30, 20, null);
            } else if (effecter.Player == 2) {
                g.drawImage(img2, 1026, 30, 30, 20, null);
            }
        }
    }
}
