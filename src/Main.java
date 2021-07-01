import view.Gameview;
import java.awt.*;
import java.io.File;
import java.lang.Thread;

import media.Audio;

import static media.Audio.addAudioByFilePath;

public class Main {
    public static void main(String[] args) {
        addAudioByFilePath("background_music", new File("Audio/PekoMiko.wav"));
        addAudioByFilePath("PEKOwww", new File("Audio/PEKO_www.wav"));
        addAudioByFilePath("Mikowww", new File("Audio/Miko_www.wav"));
        addAudioByFilePath("PEKO1", new File("Audio/PEKO1.wav"));
        addAudioByFilePath("PEKO2", new File("Audio/PEKO2.wav"));
        addAudioByFilePath("PEKO3", new File("Audio/PEKO3.wav"));
        addAudioByFilePath("PEKOATK", new File("Audio/PEKOatk.wav"));
        addAudioByFilePath("PEKOdamage", new File("Audio/PEKO_damage.wav"));
        addAudioByFilePath("Miko1", new File("Audio/Miko1.wav"));
        addAudioByFilePath("Miko2", new File("Audio/Miko2.wav"));
        addAudioByFilePath("Miko3", new File("Audio/Miko3.wav"));
        addAudioByFilePath("MikoATK", new File("Audio/Mikoatk.wav"));
        addAudioByFilePath("Mikodamage", new File("Audio/Miko_damage.wav"));
        Gameview gameboard = new Gameview();
        gameboard.init();
    }

}