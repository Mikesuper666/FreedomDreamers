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
     * Chamado quando o jogo renderiza
     * Lida com elementos em movimento no fundo.
     */
    public void tick(){
        switch(CoreManager.state){
            // Todos os movimentos para coisas em segundo plano são tratados aqui.
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
     * Chamado quando {@link ScreenState} muda.
     * @param velhoEstado estado da tela anterior {@link ScreenState}
     * @param novoEstado estado da tela atual {@link ScreenState}
     */
    public static void onStateChange(ScreenState velhoEstado, ScreenState novoEstado){
        // Não importa o que tiver na tela, limpe todos os quadros em segundo plano e recarregue.
        background.clear();
        switch (novoEstado){
            case TITLE:
                background.add(Assets.getBitmapFromMemory("intro"));

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
     * Chamado quando o jogo renderiza
     * @param canvas {@link Canvas} objeto de painel de pintura
     * @param paint {@link Paint} objeto de pintura
     */
    public void render(Canvas canvas, Paint paint){
        // Desenhe o quadro atual e atualize o animador com o tempo
        if (background.size() > 0){
            canvas.drawBitmap(animate.sprite, 0, 0, paint);
            animate.update(System.currentTimeMillis());
        }
        switch(CoreManager.state){
            // Todos os objetos em movimento no fundo são desenhados aqui.
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
