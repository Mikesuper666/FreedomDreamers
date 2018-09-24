package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.utils.Animator;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class Background {
    private static ArrayList<Bitmap> background;
    private Bitmap clouds;
    //private static Animator animate;
    private static double xOff = 0;
    public boolean shaking = false, fadingIn = false, fadingOut = false, alive = false;
    private int leftTick = 0;
    private int rightTick = 0;
    private int fadeTick = 0;
    private int shakeTick = 0;
    private int maxShakeTicks = 0;
    private int maxFadeTicks = 0;
    public int currAlpha = 0;
    Background(){
        // Carrega os assets da memoria
        clouds = Assets.getBitmapFromMemory("nuvens");
        //cria um array para fazer uma animação do tipo gif
        background = new ArrayList<>();
        background.add(Assets.getBitmapFromMemory("intro"));
        background.add(Assets.getBitmapFromMemory("mountains"));
        //animate = new Animator(background);
        // Este é o padrão inicializado pela primeira vez, supondo que seja iniciado na tela de título.
        // Essa velocidade será alterada em onStateChange ()
        //animate.setSpeed(450);
        //animate.play();
        //animate.update(System.currentTimeMillis());
        onStateChange(EstadoTela.TITULO, EstadoTela.TITULO);
    }

    /**
     * Chamado quando o jogo renderiza
     * Lida com elementos em movimento no fundo.
     */
    public void tick(){
       /* switch(NucleoManager.state){
            // Todos os movimentos para coisas em segundo plano são tratados aqui.
            case CHAR_SELECT:
            case TITULO:
            case LOAD:
            case INFO:
                if (xOff < NucleoManager.largura + clouds.getWidth()){
                    xOff += HUDManager.getSpeed(NucleoManager.largura, 1500);
                }else{
                    xOff = 0;
                }
                break;
        }*/
        if (fadingOut){
            if (fadeTick < maxFadeTicks){
                // Inc alpha uniformemente
                int amount = 255 / maxFadeTicks + 1;
                fadeTick++;

                if (currAlpha - amount > 0){
                    currAlpha -= amount;
                }else{
                    currAlpha = 0;
                }
            }else{
                // Máximo de ticks atingidos, redefinir
                fadingOut = false;
                fadeTick = 0;
                maxFadeTicks = 0;
            }
        }
    }

    /**
     * Efeito de esmaecimento da entidade
     * @param ticks quantidade de ticks determina o tempo de esmaecimento
     */
    public void fadeOut(int ticks){
        fadingOut = true;
        maxFadeTicks = ticks;
    }

    /**
     * Chamado quando {@link EstadoTela} muda.
     * @param velhoEstado estado da tela anterior {@link EstadoTela}
     * @param novoEstado estado da tela atual {@link EstadoTela}
     */
    public static void onStateChange(EstadoTela velhoEstado, EstadoTela novoEstado){
        // Não importa o que tiver na tela, limpe todos os quadros em segundo plano e recarregue.
        background.clear();
        switch (novoEstado){
            case TITULO:
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
            case BATALHA:
                break;
            case SHOP:
                break;
            case INVENTARIO:
                break;
            default:
                break;

        }
        //animate.replace(background);
    }

    /**
     * Chamado quando o jogo renderiza
     * @param canvas {@link Canvas} objeto de painel de pintura
     * @param paint {@link Paint} objeto de pintura
     */
    public void render(Canvas canvas, Paint paint){
        // Desenhe o quadro atual e atualize o animador com o tempo
      /*  if (background.size() > 0){
            canvas.drawBitmap(animate.sprite, 0, 0, paint);
            animate.update(System.currentTimeMillis());
        }
        switch(NucleoManager.state){
            // Todos os objetos em movimento no fundo são desenhados aqui.
            case TITULO:
                canvas.drawBitmap(clouds, NucleoManager.largura - (int) xOff, NucleoManager.altura / 10, paint);
                break;
            case INFO:
                break;
            case LOAD:
                break;
            case CHAR_SELECT:
                break;
        }*/
    }

    /**
     * To animate loop images with different speed (parallax effect can be aplied).
     * @param canvas The {@link Canvas} to draw on
     * @param image The {@link Bitmap} object to draw with and animate
     * @param offset position X from the image
     * @param speed speed moving image
     * @param infite determines if infinite scroll
     */
    public double offsetScrollingInverted(Canvas canvas, Bitmap image, double offset, int speed, boolean infite){
        offset -= HUDManager.getSpeed(NucleoManager.largura, speed);
        if (offset <- NucleoManager.largura){
            offset = 0;
        }
        canvas.drawBitmap(image,(int)offset, 0, null);
        if(offset < 0 && infite){
            canvas.drawBitmap(image,(int)offset + NucleoManager.largura, 0, null);
        }
        return offset;
    }

    public double offsetScrolling(Canvas canvas, Bitmap image, double offset, int speed, boolean infite){
        offset += HUDManager.getSpeed(NucleoManager.largura, speed);
        if (offset > NucleoManager.largura){
            offset = 0;
        }
        canvas.drawBitmap(image,(int)offset, 0, null);
        if(offset > 0 && infite){
            canvas.drawBitmap(image,(int)offset - NucleoManager.largura, 0, null);
        }
        return offset;
    }
}
