package character;

import javax.swing.*;

import MyObject.object;
import media.Audio;
import MyObject.carrot;
import MyObject.carrotbomb;
import MyObject.donchan;
import static media.Audio.addAudioByFilePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Peko extends CharacterProtype {

    public Peko(int P) {
        super(1, P, 3500, 2800, 750, 60, 20);
    }

    @Override
    public void Mupdate() {
        // TODO Auto-generated method stub
        super.Mupdate();
        if (Ace == 0 && Mp < Maxmp) {
            Mp += 8;
        } else if (Ace == 1) {
            Mp -= 6;
        }
    }

    @Override
    public void WinVocal() {
        // Audio.playSounds("PEKOwww", 3);
    }

    @Override
    public void skill1(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (Mp >= 200 && cd1 == 0) {
            cd1 = 5;
            Mp -= 200;
            for (int i = 0; i < 3; i++) {
                oblist.add(new carrot((direction == 1) ? pos.x + 170 : pos.x - 20, new Random().nextInt(wy - 50) + 25,
                        wx, wy, this.Player, direction, cimg.getskill(1, (direction == 1) ? 0 : 1)));
            }
        }
        // Audio.playSounds("PEKO1", 2);
        return;
    }

    public void skill2(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (Mp >= 400 && cd2 == 0) {
            cd2 = 8;
            Mp -= 400;
            Point target = new Point(0, 0);
            for (CharacterProtype p : player) {
                if (p.Player != this.Player) {
                    target = p.pos;
                }
            }
            oblist.add(
                    new donchan(target.x - 90, -30, wx, wy, this.Player, 2, cimg.getskill(2, 2), cimg.getskill(2, 3)));
            oblist.add(
                    new donchan(target.x + 30, -30, wx, wy, this.Player, 1, cimg.getskill(2, 0), cimg.getskill(2, 1)));
        }
        // Audio.playSounds("PEKO2", 2);
        return;
    }

    @Override
    public void skill3(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (cd3 > 0) {
            return;
        }
        if (Mp < 55) {
            this.changeskill();
            return;
        }
        cd3 = 10;
        Mp -= 55;
        nowstate = cimg.getskillwalk(this.direction, 0);
        oblist.add(new carrotbomb((direction == 1) ? pos.x + 120 : pos.x + 30, pos.y + 80, wx, wy, this.Player,
                direction, cimg.getskill(3, (direction == 1) ? 0 : 1), cimg.getskill(3, 2)));
        return;
    }

    @Override
    public void atkvocal() {
        // TODO Auto-generated method stub
        // Audio.playSounds("PEKOATK", 7);
    }

    @Override
    public void skillvocal() {
        // TODO Auto-generated method stub
        // Audio.playSounds("PEKO3", 6);
    }
}
