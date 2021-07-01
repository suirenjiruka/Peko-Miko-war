package MyObject;

import character.CharacterProtype;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class enemy extends object {
    private int width = 120;
    private int height = 75;
    private int direct;
    private Image img1, img2, img1R, img2R;
    int ticks = 0;

    public enemy(int x, int y, int wx, int wy, int direct) {
        super(x, y, wx, wy);
        this.direct = direct;
        try {
            img1 = ImageIO.read(new File("img/enemy1.png"));
            img2 = ImageIO.read(new File("img/enemy2.png"));
            img1R = ImageIO.read(new File("img/enemy1R.png"));
            img2R = ImageIO.read(new File("img/enemy2R.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void borderhandle() { // 是否出界
        if (pos.x < 0) {
            direct = 1;
            ;
        } else if (pos.x + width > WorldWidth) {
            direct = 2;
        }
    }

    @Override
    public boolean update(CharacterProtype[] player) {
        // TODO Auto-generated method stub
        if (direct == 1) {
            pos.translate(15, 0);
        } else if (direct == 2) {
            pos.translate(-15, 0);
        }
        borderhandle();
        ticks += 1;
        fall = 1;
        for (CharacterProtype p : player) {
            if ((pos.y + height > p.getpos().y + 50 && pos.y + height < p.getpos().y + 190)
                    || (pos.y > p.getpos().y + 50 && pos.y < p.getpos().y + 190)) {
                if (direct == 1) {
                    if ((pos.x + width + 5 > p.getpos().x && pos.x + width + 5 < p.getpos().x + 150)
                            || (pos.x + width - 35 > p.getpos().x && pos.x + width - 35 < p.getpos().x + 150)) {
                        if (p.getdamage == 0 && ticks % 7 == 3) {
                            p.setdamagestate(1);
                            p.Hpchange(-80, 1);
                            p.Mpchange(50, 1);
                        }
                    }
                } else if (direct == 2) {
                    if ((pos.x - 5 > p.getpos().x && pos.x - 5 < p.getpos().x + 150)
                            || (pos.x + 35 > p.getpos().x && pos.x + 35 < p.getpos().x + 150)) {
                        if (p.getdamage == 0 && ticks % 7 == 3) {
                            p.setdamagestate(1);
                            p.Hpchange(-80, 1);
                            p.Mpchange(50, 1);
                        }
                    }
                }
            }
        }
        if (ticks >= 90) {
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (direct == 1) {
            if (ticks % 4 <= 1) {
                g.drawImage(img1, super.pos.x, super.pos.y, width, height, null);
            }
            if (ticks % 4 >= 2) {
                g.drawImage(img2, super.pos.x, super.pos.y, width, height, null);
            }
        } else if (direct == 2) {
            if (ticks % 4 <= 1) {
                g.drawImage(img1R, super.pos.x, super.pos.y, width, height, null);
            }
            if (ticks % 4 >= 2) {
                g.drawImage(img2R, super.pos.x, super.pos.y, width, height, null);
            }
        }
    }
}
