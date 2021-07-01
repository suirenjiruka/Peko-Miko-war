package Gamecontrol;

import java.awt.Graphics;

import view.Gameview;
import view.Gameview.Canvas;
import Map_all.Map;
import Map_all.Pekoland;
import Map_all.skyland;
import Map_all.top;
import character.CharacterProtype;
import character.Peko;
import character.Miko;

public class Game extends Gameloop {
    CharacterProtype P1, P2;
    private Map world;

    public Game(int map, int P1, int P2, Gameview view, Canvas panel) {
        super(view, panel);
        if (P1 == 1) { // player1 use Character1
            this.P1 = new Peko(1);
        } else if (P1 == 2) { // player1 use Character2
            this.P1 = new Miko(1);
        }
        if (P2 == 1) { // player2 use Character1
            this.P2 = new Peko(2);
        } else if (P2 == 2) { // player2 use Character2
            this.P2 = new Miko(2);
        }
        setbackground(map);
    }

    public Map getMap() {
        return world;
    }

    public void gamemove(int P, int direct) {
        if (P == 1) {
            P1.move(direct);
        } else if (P == 2) {
            P2.move(direct);
        }
    }

    public void gamejump(int P) {
        if (P == 1) {
            P1.jump();
        } else if (P == 2) {
            P2.jump();
        }
    }

    public void gamefall(int P) {
        if (P == 1) {
            P1.fall();
        } else if (P == 2) {
            P2.fall();
        }
    }

    public void gameatk(int P) {
        if (P == 1) {
            world.ATKhandle(0, 1);
        } else if (P == 2) {
            world.ATKhandle(1, 0);
        }
    }

    public void gamedef(int P) {
        if (P == 1) {
            P1.def();
        } else if (P == 2) {
            P2.def();
        }
    }

    public void gamestopdef(int P) {
        if (P == 1) {
            P1.stopdef();
        } else if (P == 2) {
            P2.stopdef();
        }
    }

    public void gamestop(int P, int direct) {
        if (P == 1) {
            P1.stop(direct);
        } else if (P == 2) {
            P2.stop(direct);
        }
    }

    public void skill(int P, int sk) {
        world.callskill(P, sk);
    }

    @Override
    protected Map getWorld() {
        // TODO Auto-generated method stub
        while (world == null) {
            ;
        }
        return world;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        world.update();
        if (world.getend() == 2) {
            world.removebackground();
            stop();
        }
    }

    @Override
    public void setbackground(int map) {
        if (map == 0) {
            world = new Pekoland(view, panel, P1, P2);
        } else if (map == 1) {
            world = new skyland(view, panel, P1, P2);
        } else if (map == 2) {
            world = new top(view, panel, P1, P2);
        }
    }
}
