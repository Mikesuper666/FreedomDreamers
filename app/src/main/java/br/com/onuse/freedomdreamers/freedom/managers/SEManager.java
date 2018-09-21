package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Gerencia todos efeitos de tela, incluindo transições e flashes.
 * Cham mudanças de estado para outros gerenciadores
 * @author Maico Ribeiro
 * @since 21/09/2018
 */
public class SEManager {
    private static int tick = 0;
    private static int opacity = 0;
    private static boolean running = false;
    private static boolean fade = false;
    private static Effect currentEffect = Effect.NOTHING;
    private static EstadoTela proximoEstado = null;
    /**
     * Called when the game ticks.
     * Handles all screen effects, and calls state changes accordingly.
     */
    public void tick(){

    }
    /**
     * Called when the game renders.
     * Renders all screen effects.
     * @param canvas The {@link Canvas} to draw on
     * @param paint The {@link Paint} object to draw with
     */
    public void render(Canvas canvas, Paint paint){

    }
    /**
     * Plays a screen effect, with a new {@link EstadoTela} to transition to.
     * @param effect The {@link Effect} to play
     * @param novoEstado The new {@link EstadoTela} to transition to
     */
    public static void playEffect(Effect effect, EstadoTela novoEstado){

    }
    /**
     * Plays a screen effect.
     * @param effect The {@link Effect} to play
     */
    public static void playEffect(Effect effect){

    }

    public enum Effect {
        NOTHING, FADE_TRANSITION, SHAKE, RED_FLASH, YELLOW_FLASH, GREEN_FLASH, PURPLE_FLASH
    }
}
