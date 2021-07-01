package MyObject;

import java.awt.*;

import character.CharacterProtype;

public class object {
    protected Point pos = new Point();
    protected int WorldWidth, WorldHeight;
    public int fall;

    public object(int x, int y, int wx, int wy) {
        pos.setLocation(x, y);
        WorldWidth = wx;
        WorldHeight = wy;
    }

    public boolean update(CharacterProtype[] player) {
        // wait override
        return false;
    }

    public void render(Graphics g) {
        // wait override
    }

    public Point getpos() {
        return pos;
    }

    public int getwidth() {
        // wait override
        return 0;
    }

    public int getheight() {
        // wait overide
        return 0;
    }

    public void fallchange(int i) {
        return;
    }
}