package view;

import Map_all.Map;
import Map_all.Chosepage;
import Map_all.titlepage;

import javax.imageio.ImageIO;
import javax.swing.*;

import Gamecontrol.Gameloop;
import Gamecontrol.Game;

import media.Audio;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Gameview extends JFrame {
    int width, height;
    private TP tpage = new TP(this);
    private CP cpage = new CP(this);
    private Canvas canvas = new Canvas(this);

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        width = 1366;
        height = 768;
        tpage.setOpaque(false);
        tpage.start();
        setContentPane(tpage);
        setTitle("ぺこみこ大戦争");
        setIconImage(this.getToolkit().getImage("img/Peko_icon.jpg"));
        setVisible(true);
        setSize(this.width, this.height);
        new Thread(this::BGM).start();
    }

    private void BGM() {
        Audio.playBGM("background_music");
    }

    public int givewidth() {
        return this.width;
    }

    public int giveheight() {
        return this.height;
    }

    public void changestage(int index, String num) {
        switch (index) {
            case 1:
                tpage = new TP(this);
                tpage.setOpaque(false);
                tpage.start();
                this.setContentPane(tpage);
                canvas = null;
                break;
            case 2:
                cpage = new CP(this);
                cpage.setOpaque(false);
                cpage.start();
                this.setContentPane(cpage);
                tpage = null;
                /* this.pack(); */
                break;
            case 3:
                Canvas canvas = new Canvas(this);
                canvas.setOpaque(false);
                this.setContentPane(canvas);
                canvas.start();
                this.setContentPane(canvas);
                cpage = null;
                break;
        }
    }

    public class Canvas extends JPanel {
        public Gameview view;
        public Game game;

        Canvas(Gameview view) {
            this.view = view;
            return;
        }

        public void start() {
            this.setSize(view.givewidth(), view.giveheight());
            this.setLayout(null);
            game = new Game((new Random().nextInt(299) % 3), cpage.p1_index, cpage.p2_index, view, this);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_W:
                            game.gamejump(1);
                            break;
                        case KeyEvent.VK_S:
                            game.gamefall(1);
                            break;
                        case KeyEvent.VK_A:
                            game.gamemove(1, 2);
                            break;
                        case KeyEvent.VK_D:
                            game.gamemove(1, 1);
                            break;
                        case KeyEvent.VK_E:
                            game.gameatk(1);
                            break;
                        case KeyEvent.VK_Q:
                            game.gamedef(1);
                            break;
                        case KeyEvent.VK_1:
                            game.skill(1, 1);
                            break;
                        case KeyEvent.VK_2:
                            game.skill(1, 2);
                            break;
                        case KeyEvent.VK_3:
                            game.skill(1, 3);
                            break;
                        case KeyEvent.VK_LEFT:
                            game.gamemove(2, 2);
                            break;
                        case KeyEvent.VK_RIGHT:
                            game.gamemove(2, 1);
                            break;
                        case KeyEvent.VK_UP:
                            game.gamejump(2);
                            break;
                        case KeyEvent.VK_DOWN:
                            game.gamefall(2);
                            break;
                        case KeyEvent.VK_NUMPAD0:
                            game.gameatk(2);
                            break;
                        case KeyEvent.VK_CONTROL:
                            game.gamedef(2);
                            break;
                        case KeyEvent.VK_NUMPAD1:
                            game.skill(2, 1);
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            game.skill(2, 2);
                            break;
                        case KeyEvent.VK_NUMPAD3:
                            game.skill(2, 3);
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_A:
                            game.gamestop(1, 2);
                            break;
                        case KeyEvent.VK_D:
                            game.gamestop(1, 1);
                            break;
                        case KeyEvent.VK_LEFT:
                            game.gamestop(2, 2);
                            break;
                        case KeyEvent.VK_RIGHT:
                            game.gamestop(2, 1);
                            break;
                        case KeyEvent.VK_Q:
                            game.gamestopdef(1);
                            break;
                        case KeyEvent.VK_CONTROL:
                            game.gamestopdef(2);
                            break;
                    }
                }

            });
            this.requestFocus();
            game.start();
            return;
        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);
            if (game != null && game.getMap() != null) {
                game.getMap().render(g);
            }
        }

    }

    public class TP extends JPanel {
        public Gameview view;
        public boolean running = false;

        TP(Gameview view) {
            this.view = view;
        }

        public void start() {

            this.setSize(view.givewidth(), view.giveheight());
            this.setLayout(null);
            titlepage.setBack(view);
            titlepage.title(view, this);
        }

    }

    public class CP extends JPanel {
        public Gameview view;
        public boolean running = false;
        public Image p1;
        public Image p2;
        public int p1_index = 1;
        public int p2_index = 2;
        public int p1OK = 0;
        public int p2OK = 0;

        CP(Gameview view) {
            this.view = view;
        }

        public void start() {
            this.setSize(view.givewidth(), view.giveheight());
            this.setLayout(null);
            Chosepage.setBack(view);
            Chosepage.chose(view, this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(p1, 125, 100, p1.getWidth(null), p1.getHeight(null), null);
            g.drawImage(p2, view.givewidth() - 525, 100, p2.getWidth(null), p2.getHeight(null), null);
        }
    }
}
