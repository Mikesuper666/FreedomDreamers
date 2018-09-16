package br.com.onuse.freedomdreamers.freedom.utils;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Animator {
    private ArrayList<Bitmap> frames;
    private volatile boolean isRunning = false;
    private long prevTime, speed;
    public Bitmap sprite;
    private int frameAtPause, currentFrame;

    /**
     *
     * @param frames A lista de frames individuais
     */
    public Animator(ArrayList<Bitmap> frames){
        this.frames = new ArrayList<>(frames);
    }

    /**
     * Seta a velocidade da animação
     * @param speed A velocidade da animação
     */
    public void setSpeed(long speed){
        this.speed = speed;
    }

    /**
     * Atualizar o quadro da animação atual.
     * @param time Tempo do sistema em minisegundos
     */
    public void update(long time){
        if (isRunning){
            // Verifique se já passou tempo suficiente
            if (time - prevTime >= speed){
                // atualiza o sprite atual
                try{
                    sprite = frames.get(currentFrame);
                }catch(IndexOutOfBoundsException e){
                    reset();
                    sprite = frames.get(currentFrame);
                }
                // Avança para o proximo frame
                currentFrame++;
                prevTime = time;
            }
        }
    }

    /**
     * Substitui o conjunto atual de quadros por um novo conjunto.
     * @param frames novo set de frames
     */
    public void replace(ArrayList<Bitmap> frames){
        stop();
        reset();
        this.frames = frames;
        play();
    }

    /**
     * Inicia a animação
     */
    public void play(){
        isRunning = true;
        prevTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    /**
     * @return Retorna para o index atual
     */
    public int getCurrentFrame(){
        return currentFrame;
    }

    /**
     * Para a animação
     */
    public void stop(){
        isRunning = false;
        prevTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }

    /**
     * Pausa a animação
     */
    public void pause(){
        frameAtPause = currentFrame;
        isRunning = false;
    }

    /**
     * Resume a animação.
     */
    public void resume(){
        currentFrame = frameAtPause;
        isRunning = true;
    }

    /**
     * @return {@code true} Checa se a animação finalizou
     */
    public boolean isDoneAnimation(){
        if (currentFrame == frames.size()){
            return true;
        }
        return false;
    }

    /**
     * Reseta a animação
     */
    public void reset(){
        currentFrame = 0;
    }
}
