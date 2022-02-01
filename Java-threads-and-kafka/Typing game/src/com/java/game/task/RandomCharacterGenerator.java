package com.java.game.task;
import com.java.game.Interface.CharacterListener;
import com.java.game.Interface.CharacterSource;
import com.java.game.handler.CharacterEventHandler;

import java.util.*;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
    static char[] chars;
    static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
    static {
        chars = charArray.toCharArray( );
    }

    Random random;
    CharacterEventHandler handler;

    public RandomCharacterGenerator( ) {
        random = new Random( );
        handler = new CharacterEventHandler( );
    }

    public int getPauseTime( ) {
        return (int) (Math.max(1000, 5000 * random.nextDouble( )));
    }

    public void addCharacterListener(CharacterListener cl) {
        handler.addCharacterListener(cl);
    }

    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }

    public void nextCharacter( ) {
        handler.fireNewCharacter(this,
                (int) chars[random.nextInt(chars.length)]);
    }

    public void run( ) {
        for (;;) {
            nextCharacter( );
            try {
                Thread.sleep(getPauseTime( ));
            } catch (InterruptedException ie) {
                return;
            }
        }
    }
}

