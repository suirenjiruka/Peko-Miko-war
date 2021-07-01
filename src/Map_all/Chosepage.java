package Map_all;

import view.Gameview;
import view.Gameview.CP;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chosepage {
    static JLabel background;
    static ImageIcon pekohead;
    static ImageIcon Mikohead;
    static ImageIcon PekoCharacter1;
    static ImageIcon PekoCharacter2;
    static ImageIcon MikoCharacter1;
    static ImageIcon MikoCharacter2;
    static ImageIcon OK;
    static ImageIcon OK_click;

    public static void setBack(Gameview view) {
        ImageIcon img = new ImageIcon("img/Title.jpg");
        img.setImage(img.getImage().getScaledInstance(view.givewidth(), -1, Image.SCALE_DEFAULT));
        background = new JLabel(img);
        view.getRootPane().add(background, -1);
        background.setBounds(0, 0, view.givewidth(), view.giveheight());
    }

    public static void chose(Gameview view, CP cpage) {
        ImageIcon peko = new ImageIcon("img/Peko_icon.jpg");
        peko.setImage(peko.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        pekohead = peko;
        ImageIcon Miko = new ImageIcon("img/Miko_icon.jpg");
        Miko.setImage(Miko.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        Mikohead = Miko;
        ImageIcon Character1 = new ImageIcon("img/PEKO_0.png");
        Character1.setImage(Character1.getImage().getScaledInstance(-1, 600, Image.SCALE_DEFAULT));
        PekoCharacter1 = Character1;
        ImageIcon Character1_2 = new ImageIcon("img/Miko_0.png");
        Character1_2.setImage(Character1_2.getImage().getScaledInstance(-1, 600, Image.SCALE_DEFAULT));
        MikoCharacter1 = Character1_2;
        ImageIcon Character2 = new ImageIcon("img/PEKO_0R.png");
        Character2.setImage(Character2.getImage().getScaledInstance(-1, 600, Image.SCALE_DEFAULT));
        PekoCharacter2 = Character2;
        ImageIcon Character2_2 = new ImageIcon("img/Miko_0R.png");
        Character2_2.setImage(Character2_2.getImage().getScaledInstance(-1, 600, Image.SCALE_DEFAULT));
        MikoCharacter2 = Character2_2;
        ImageIcon okbutton = new ImageIcon("img/Ok.png");
        okbutton.setImage(okbutton.getImage().getScaledInstance(110, -1, Image.SCALE_DEFAULT));
        OK = okbutton;
        ImageIcon okbutton_click = new ImageIcon("img/Ok.png");
        okbutton_click.setImage(okbutton_click.getImage().getScaledInstance(130, -1, Image.SCALE_DEFAULT));
        OK_click = okbutton_click;
        if (!cpage.running) {
            cpage.running = true;
            P1(view, cpage);
            P2(view, cpage);
        }
    }

    public static void P1(Gameview view, CP cpage) {
        ImageIcon p1 = new ImageIcon("img/1P.png");
        p1.setImage(p1.getImage().getScaledInstance(135, 90, Image.SCALE_DEFAULT));
        JLabel title1 = new JLabel();
        title1.setIcon(p1);
        title1.setBounds(200, 10, 135, 100);
        JButton pekobutton = new JButton();
        pekobutton.setIcon(pekohead);
        pekobutton.setBounds(30, 130, 80, 80);
        JButton Mikobutton = new JButton();
        Mikobutton.setIcon(Mikohead);
        Mikobutton.setEnabled(false);
        Mikobutton.setBounds(30, 220, 80, 80);
        pekobutton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (cpage.p1OK == 0) {
                    pekobutton.setEnabled(true);
                    Mikobutton.setEnabled(false);
                    cpage.p1 = PekoCharacter1.getImage();
                    cpage.p1_index = 1;
                    cpage.repaint();
                }
            }

            public void mouseEntered(MouseEvent evt) {
                pekobutton.setEnabled(true);
            }

            public void mouseExited(MouseEvent evt) {
                if (Mikobutton.isEnabled()) {
                    pekobutton.setEnabled(false);
                }
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        Mikobutton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (cpage.p1OK == 0) {
                    pekobutton.setEnabled(false);
                    Mikobutton.setEnabled(true);
                    cpage.p1 = MikoCharacter1.getImage();
                    cpage.p1_index = 2;
                    cpage.repaint();
                }
            }

            public void mouseEntered(MouseEvent evt) {
                Mikobutton.setEnabled(true);
            }

            public void mouseExited(MouseEvent evt) {
                if (pekobutton.isEnabled()) {
                    Mikobutton.setEnabled(false);
                }
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        JButton OK1 = new JButton();
        OK1.setContentAreaFilled(false);
        OK1.setIcon(OK);
        OK1.setBorder(null);
        OK1.setBounds(40, 550, 110, 110);
        OK1.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                OK1.setEnabled(false);
                cpage.p1OK = 1;
                if (cpage.p1OK == 1 && cpage.p2OK == 1) {
                    view.getRootPane().remove(background);
                    view.changestage(3, "3");
                    return;
                }
            }

            public void mouseEntered(MouseEvent evt) {
                OK1.setIcon(OK_click);
                OK1.setBounds(30, 540, 130, 130);
            }

            public void mouseExited(MouseEvent evt) {
                OK1.setIcon(OK);
                OK1.setBounds(40, 550, 110, 110);
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        cpage.add(title1);
        cpage.add(pekobutton);
        cpage.add(Mikobutton);
        cpage.add(OK1);
        cpage.p1 = PekoCharacter1.getImage();
    }

    public static void P2(Gameview view, CP cpage) {
        ImageIcon p2 = new ImageIcon("img/2P.png");
        p2.setImage(p2.getImage().getScaledInstance(135, 90, Image.SCALE_DEFAULT));
        JLabel title2 = new JLabel();
        title2.setIcon(p2);
        title2.setBounds(view.givewidth() - 335, 10, 135, 100);
        JButton pekobutton = new JButton();
        pekobutton.setIcon(pekohead);
        pekobutton.setBounds(view.givewidth() - 115, 130, 80, 80);
        pekobutton.setEnabled(false);
        JButton Mikobutton = new JButton();
        Mikobutton.setIcon(Mikohead);
        Mikobutton.setBounds(view.givewidth() - 115, 220, 80, 80);
        pekobutton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (cpage.p2OK == 0) {
                    pekobutton.setEnabled(true);
                    Mikobutton.setEnabled(false);
                    cpage.p2 = PekoCharacter2.getImage();
                    cpage.p2_index = 1;
                    cpage.repaint();
                }
            }

            public void mouseEntered(MouseEvent evt) {
                pekobutton.setEnabled(true);
            }

            public void mouseExited(MouseEvent evt) {
                if (Mikobutton.isEnabled()) {
                    pekobutton.setEnabled(false);
                }
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        Mikobutton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (cpage.p2OK == 0) {
                    pekobutton.setEnabled(false);
                    Mikobutton.setEnabled(true);
                    cpage.p2 = MikoCharacter2.getImage();
                    cpage.p2_index = 2;
                    cpage.repaint();
                }
            }

            public void mouseEntered(MouseEvent evt) {
                Mikobutton.setEnabled(true);
            }

            public void mouseExited(MouseEvent evt) {
                if (pekobutton.isEnabled()) {
                    Mikobutton.setEnabled(false);
                }
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        JButton OK2 = new JButton();
        OK2.setContentAreaFilled(false);
        OK2.setIcon(OK);
        OK2.setBounds(view.givewidth() - 150, 550, 110, 110);
        OK2.setBorder(null);
        OK2.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                OK2.setEnabled(false);
                cpage.p2OK = 1;
                if (cpage.p1OK == 1 && cpage.p2OK == 1) {
                    view.getRootPane().remove(background);
                    view.changestage(3, "3");
                    return;
                }
            }

            public void mouseEntered(MouseEvent evt) {
                OK2.setIcon(OK_click);
                OK2.setBounds(view.givewidth() - 160, 540, 130, 130);
            }

            public void mouseExited(MouseEvent evt) {
                OK2.setIcon(OK);
                OK2.setBounds(view.givewidth() - 150, 550, 110, 110);
            }

            public void mousePressed(MouseEvent evt) {
            }

            public void mouseReleased(MouseEvent evt) {
            }
        });
        cpage.add(title2);
        cpage.add(pekobutton);
        cpage.add(Mikobutton);
        cpage.add(OK2);
        cpage.p2 = MikoCharacter2.getImage();
    }

}
