package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.utils.Animator;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class Background {
    private static ArrayList<Bitmap> background;
    private Bitmap clouds, bg;
    private static Animator animate;
    private static double xOff = 0;
    public Background(){
        // Carrega os assets da memoria
        clouds = Assets.getBitmapFromMemory("nuvens");
        //cria um array para fazer uma animação do tipo gif
        background = new ArrayList<>();
        background.add(Assets.getBitmapFromMemory("intro"));
        background.add(Assets.getBitmapFromMemory("mountains"));
        animate = new Animator(background);
        // Este é o padrão inicializado pela primeira vez, supondo que seja iniciado na tela de título.
        // Essa velocidade será alterada em onStateChange ()
        animate.setSpeed(450);
        animate.play();
        animate.update(System.currentTimeMillis());
        onStateChange(ScreenState.TITLE, ScreenState.TITLE);
    }

    /**
     * Called when the game ticks.
     * Handles moving elements in the background.
     */
    public void tick(){
        switch(CoreManager.state){
            // All movement for things in the background are handled here.
            case CHAR_SELECT:
            case TITLE:
            case LOAD:
            case INFO:
                if (xOff < CoreManager.largura + clouds.getWidth()){
                    xOff += HUDManager.getSpeed(CoreManager.largura, 1500);
                }else{
                    xOff = 0;
                }
                break;
        }
    }

    /**
     * Called when the {@link ScreenState} changes.
     * @param oldState The previous {@link ScreenState}
     * @param newState The new {@link ScreenState}
     */
    public static void onStateChange(ScreenState oldState, ScreenState newState){
        // No matter what, clear all frames in the background, and reload.
        background.clear();
        switch (newState){
            case TITLE:
                //background.add(Assets.getBitmapFromMemory("intro"));

                break;
            case INFO:
                break;
            case LOAD:
                break;
            case CHAR_SELECT:
                background.add(Assets.getBitmapFromMemory("mountains"));
                break;
            case STAGE_TRANSITION:
                break;
            case BATTLE:
                break;
            case SHOP:
                break;
            case INVENTORY:
                break;
            default:
                break;

        }
        animate.replace(background);
    }

    /**
     * Called when the game renders.
     * @param canvas The {@link Canvas} to draw on
     * @param paint The {@link Paint} object to draw with
     */
    public void render(Canvas canvas, Paint paint){
        HUDManager.displayTypingText("Choose a character...", 0, 0, 2, 11, Color.rgb(255,2,5), true);

        // Desenhe o quadro atual e atualize o animador com o tempo
        if (background.size() > 0){
            canvas.drawBitmap(animate.sprite, 0, 0, paint);
            animate.update(System.currentTimeMillis());
        }
        switch(CoreManager.state){
            // All moving objects in the background are drawn here.
            case TITLE:
                canvas.drawBitmap(clouds, CoreManager.largura - (int) xOff, CoreManager.altura / 10, paint);
                break;
            case INFO:
                break;
            case LOAD:
                break;
            case CHAR_SELECT:
                break;

        }
    }
}
