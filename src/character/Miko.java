package character;

import javax.swing.*;
import java.awt.*;

import MyObject.object;
import MyObject.sakura;
import media.Audio;
import MyObject.Mikocat;
import MyObject.bullet;
import static media.Audio.addAudioByFilePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Miko extends CharacterProtype {
    int Player;

    public Miko(int P) {
        super(2, P, 2500, 2200, 300, 80, 25);
        Player = P;
    }

    @Override
    public void Mupdate() {
        // TODO Auto-generated method stub
        super.Mupdate();
        if (Ace == 0 && Mp < Maxmp) {
            Mp += 7;
        } else if (Ace == 1) {
            Mp -= 5;
        }
    }

    @Override
    public void WinVocal() {
        // TODO Auto-generated method stub
        Audio.playSounds("Mikowww", 3);
    }

    @Override
    public void skill1(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (Mp >= 110 && cd1 == 0) {
            cd1 = 4;
            Mp -= 110;
            oblist.add(new sakura((direction == 1) ? pos.x + 185 : pos.x - 35, pos.y + 140, wx, wy, this.Player,
                    cimg.getskill(1, 0), cimg.getskill(1, 1)));
        }
        Audio.playSounds("Miko1", 8);
        return;
    }

    @Override
    public void skill2(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (Mp >= 300 && cd2 == 0) {
            if (direction == 1) {
                if (pos.x + 270 > wx) {
                    return;
                }
            } else if (direction == 2) {
                if (pos.x - 120 < 0) {
                    return;
                }
            }
            cd2 = 6;
            Mp -= 300;
            oblist.add(new Mikocat((direction == 1) ? pos.x + 170 : pos.x - 120, pos.y + 100, wx, wy, this.Player,
                    direction, cimg.getskill(2, (direction == 1) ? 0 : 2), cimg.getskill(2, (direction == 1) ? 1 : 3)));
        }
        Audio.playSounds("Miko2", 8);
        return;
    }

    @Override
    public void skill3(ArrayList<object> oblist, CharacterProtype[] player, int wx, int wy) {
        // TODO Auto-generated method stub
        if (cd3 > 0) {
            return;
        }
        if (Mp < 45) {
            this.changeskill();
            return;
        }
        cd3 = 7;
        Mp -= 45;
        nowstate = cimg.getskillwalk(this.direction, 0);
        for (int i = 0; i < 4; i++) {
            oblist.add(new bullet(((direction == 1) ? pos.x + 110 : pos.x - 40) + new Random().nextInt(80),
                    new Random().nextInt(200) + pos.y + 20, wx, wy, this.Player, this.direction,
                    10 + new Random().nextInt(35), cimg.getskill(3, (direction == 1) ? 0 : 1)));
        }
        return;
    }

    @Override
    public void atkvocal() {
        Audio.playSounds("MikoATK", 0);
    }

    @Override
    public void skillvocal() {
        Audio.playSounds("Miko3", 12.0f);
    }
}
