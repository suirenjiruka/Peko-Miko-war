package Map_all;

import view.Gameview;
import view.Gameview.Canvas;
import view.Gameview.TP;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class titlepage {
    static JLabel bg;

    public static void setBack(Gameview view) {
        ImageIcon img = new ImageIcon("img/Titlepage.jpg");
        img.setImage(img.getImage().getScaledInstance(view.givewidth(), -2, Image.SCALE_DEFAULT));
        bg = new JLabel(img);
        view.getRootPane().add(bg, -1);
        bg.setBounds(0, 0, view.givewidth(), view.giveheight());
    }

    public static void title(Gameview view, TP tpage) {
        if (!tpage.running) {
            tpage.running = true;
            startbutton(view, tpage);
        }
    }

    private static void startbutton(Gameview view, TP tpage) {
        ImageIcon img = new ImageIcon("img/gamestart.png");
        img.setImage(img.getImage().getScaledInstance(260, 115, Image.SCALE_DEFAULT));
        JButton startbutton = new JButton();
        startbutton.setIcon(img);
        startbutton.setBounds((view.givewidth() / 2 - 135), (view.giveheight() / 2 - 15), 260, 120);
        tpage.add("startbutton", startbutton);
        startbutton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                view.getRootPane().remove(bg);
                view.changestage(2, "2");
                return;
            }

            public void mouseEntered(MouseEvent e) {
                img.setImage(img.getImage().getScaledInstance(280, 125, Image.SCALE_DEFAULT));
                startbutton.setIcon(img);
                startbutton.setBounds((view.givewidth() / 2 - 145), (view.giveheight() / 2 - 20), 280, 130);
            }

            public void mouseExited(MouseEvent e) {
                img.setImage(img.getImage().getScaledInstance(260, 115, Image.SCALE_DEFAULT));
                startbutton.setIcon(img);
                startbutton.setBounds((view.givewidth() / 2 - 135), (view.giveheight() / 2 - 15), 260, 120);
            }

            public void mouseReleased(MouseEvent e) {
                return;
            }

            // 按下滑鼠
            public void mousePressed(MouseEvent e) {
                return;
            }
        });
    }
}
