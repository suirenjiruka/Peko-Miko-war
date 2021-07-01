package character;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CharacterImage {
    Image[] walking = new Image[5];
    Image[] walkingR = new Image[5];
    Image[] skillwalking = new Image[5];
    Image[] skillwalkingR = new Image[5];
    Image[] Jump = new Image[3];
    Image[] JumpR = new Image[3];
    Image icon;
    Image ATK, ATKR;
    Image DEF, DEFR;
    Image[][] skill = new Image[3][];

    public CharacterImage(int Character) {
        for (int i = 0; i < 3; i++) {
            skill[i] = new Image[10];
        }
        if (Character == 1) {
            for (int i = 0; i < 5; i++) {
                try {
                    walking[i] = ImageIO.read(new File("img/PEKO_" + i + ".png"));
                    walkingR[i] = ImageIO.read(new File("img/PEKO_" + i + "R.png"));
                    skillwalking[i] = ImageIO.read(new File("img/PEKO_skill3_" + i + ".png"));
                    skillwalkingR[i] = ImageIO.read(new File("img/PEKO_skill3_" + i + "R.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 1; i < 3; i++) {
                try {
                    Jump[i] = ImageIO.read(new File("img/PEKO_" + i + "jump.png"));
                    JumpR[i] = ImageIO.read(new File("img/PEKO_" + i + "Rjump.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Jump[0] = walking[0];
            JumpR[0] = walkingR[0];
            try {
                ATK = ImageIO.read(new File("img/PEKO_atk.png"));
                ATKR = ImageIO.read(new File("img/PEKO_atkR.png"));
                DEF = ImageIO.read(new File("img/PEKO_def.png"));
                DEFR = ImageIO.read(new File("img/PEKO_defR.png"));
                skill[0][0] = ImageIO.read(new File("img/PEKO_skill1.png"));
                skill[0][1] = ImageIO.read(new File("img/PEKO_skill1R.png"));
                skill[1][0] = ImageIO.read(new File("img/PEKO_skill2_1.png"));
                skill[1][1] = ImageIO.read(new File("img/PEKO_skill2_2.png"));
                skill[1][2] = ImageIO.read(new File("img/PEKO_skill2_1R.png"));
                skill[1][3] = ImageIO.read(new File("img/PEKO_skill2_2R.png"));
                skill[2][0] = ImageIO.read(new File("img/PEKO_skill3_5.png"));
                skill[2][1] = ImageIO.read(new File("img/PEKO_skill3_5R.png"));
                skill[2][2] = ImageIO.read(new File("img/PEKO_skill3_6.png"));
                icon = ImageIO.read(new File("img/PEKO_icon.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (Character == 2) {
            for (int i = 0; i < 5; i++) {
                try {
                    walking[i] = ImageIO.read(new File("img/Miko_" + i + ".png"));
                    walkingR[i] = ImageIO.read(new File("img/Miko_" + i + "R.png"));
                    skillwalking[i] = ImageIO.read(new File("img/Miko_skill3_" + i + ".png"));
                    skillwalkingR[i] = ImageIO.read(new File("img/Miko_skill3_" + i + "R.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 1; i < 3; i++) {
                try {
                    Jump[i] = ImageIO.read(new File("img/Miko_" + i + "jump.png"));
                    JumpR[i] = ImageIO.read(new File("img/Miko_" + i + "Rjump.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Jump[0] = walking[0];
            JumpR[0] = walkingR[0];
            try {
                ATK = ImageIO.read(new File("img/Miko_atk.png"));
                ATKR = ImageIO.read(new File("img/Miko_atkR.png"));
                DEF = ImageIO.read(new File("img/Miko_def.png"));
                DEFR = ImageIO.read(new File("img/Miko_defR.png"));
                skill[0][0] = ImageIO.read(new File("img/Miko_skill1_1.png"));
                skill[0][1] = ImageIO.read(new File("img/Miko_skill1_2.png"));
                skill[1][0] = ImageIO.read(new File("img/Miko_skill2_1.png"));
                skill[1][1] = ImageIO.read(new File("img/Miko_skill2_2.png"));
                skill[1][2] = ImageIO.read(new File("img/Miko_skill2_1R.png"));
                skill[1][3] = ImageIO.read(new File("img/Miko_skill2_2R.png"));
                skill[2][0] = ImageIO.read(new File("img/Miko_skill3_5.png"));
                skill[2][1] = ImageIO.read(new File("img/Miko_skill3_5R.png"));
                icon = ImageIO.read(new File("img/Miko_icon.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Image getwalk(int direct, int index) { // 1 = right, 2 = left
        if (direct == 1) {
            return walking[index];
        } else if (direct == 2) {
            return walkingR[index];
        }
        return null;
    }

    public Image getskillwalk(int direct, int index) { // 1 = right, 2 = left
        if (direct == 1) {
            return skillwalking[index];
        } else if (direct == 2) {
            return skillwalkingR[index];
        }
        return null;
    }

    public Image getjump(int direct, int index) { // 1 = right, 2 = left
        if (direct == 1) {
            return Jump[index];
        } else if (direct == 2) {
            return JumpR[index];
        }
        return null;
    }

    public Image getatk(int direct) {
        if (direct == 1) {
            return ATK;
        } else if (direct == 2) {
            return ATKR;
        }
        return null;
    }

    public Image getdef(int direct) {
        if (direct == 1) {
            return DEF;
        } else if (direct == 2) {
            return DEFR;
        }
        return null;
    }

    public Image geticon() {
        return icon;
    }

    public Image getskill(int which, int index) {
        return skill[which - 1][index];
    }

}