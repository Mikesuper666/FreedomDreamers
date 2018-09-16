package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.hudobjects.TextoAnimado;

public class TextoAnimadoManager {
    private static ArrayList<TextoAnimado> activeAnimatedTexts = new ArrayList<>();
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
     * Chamado uando o jogo renderiza
     * @param canvas {@link Canvas} objeto de painel para pintura
     * @param paint {@link Paint} objeto de pintura
     */
    public void render(Canvas canvas, Paint paint){
        // Cria uma matriz temporária para evitar exceções de modificação simultânea
        ArrayList<TextoAnimado> temp = new ArrayList<>(activeAnimatedTexts);
        for (TextoAnimado s : temp){
            s.render(canvas, paint);
        }
    }

    /**
     * Chamado quando o jogo ticks
     */
    public void tick(){
        // Cria uma matriz temporária para evitar exceções de modificação simultânea
        ArrayList<TextoAnimado> temp = new ArrayList<>(activeAnimatedTexts);
        for (TextoAnimado s : temp) {
            s.tick();
        }
    }
}