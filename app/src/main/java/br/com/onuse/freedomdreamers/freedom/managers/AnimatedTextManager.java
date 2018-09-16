package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.hudobjects.TextoAnimado;

public class AnimatedTextManager {
    public static ArrayList<TextoAnimado> activeAnimatedTexts = new ArrayList<>();
    public static void addText(TextoAnimado text){
        activeAnimatedTexts.add(text);
    }
    public static void removeText(TextoAnimado text){
        activeAnimatedTexts.remove(text);
    }
    public static void clear(){
        activeAnimatedTexts.clear();
    }

    /**
     * Called when the game renders.
     * @param canvas The {@link Canvas} to draw on
     * @param paint The {@link Paint} object to draw with
     */
    public void render(Canvas canvas, Paint paint){
        // Create temp array to prevent concurrent modification exceptions
        ArrayList<TextoAnimado> temp = new ArrayList<>(activeAnimatedTexts);
        for (TextoAnimado s : temp){
            s.render(canvas, paint);
        }
    }

    /**
     * Called when the game ticks.
     */
    public void tick(){
        // Create temp array to prevent concurrent modification exceptions
        ArrayList<TextoAnimado> temp = new ArrayList<>(activeAnimatedTexts);
        for (TextoAnimado s : temp) {
            s.tick();
        }
    }
}