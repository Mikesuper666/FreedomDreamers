package br.com.onuse.freedomdreamers.freedom.hudobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import br.com.onuse.freedomdreamers.freedom.managers.AnimatedTextManager;
import br.com.onuse.freedomdreamers.freedom.managers.HUDManager;

public class AnimatedText {
    public double x, y;
    public String text;
    public int textSize;
    public int color;
    public boolean active = false;
    public int currAlpha;
    public boolean centralizado;

    /**
     *
     * @param text Texto para ser mostrado na tela
     * @param x posição X da tela
     * @param y posição Y da tela
     * @param textSize Tamanho da tela
     * @param color Cor do texto
     * @param startingAlpha A opacidade inicial do texto
     */
    public AnimatedText(String text, int x, int y, int textSize, int color, int startingAlpha, boolean centralizado){
        this.text = text;
        this.textSize = textSize;
        this.color = color;
        this.x = x;
        this.y = y;
        this.centralizado = centralizado;
        currAlpha = startingAlpha;
    }

    /**
     * Called when the game ticks.
     */
    public void tick(){

    }

    /**
     * Chamado quando o jogo renderiza
     * Renderiza o textoAnimado na tela
     * @param canvas The {@link Canvas} object to render to
     * @param paint The {@link Paint} object to render with
     */
    public void render(Canvas canvas, Paint paint){
        int adicionar = 0;
            for (String textSegment : text.split("\n")){
                if (centralizado)
                    drawCenteredText(textSegment, canvas, (int) x, (int) y + adicionar, paint, textSize, color);
                else
                    HUDManager.drawText(textSegment, canvas, (int) x, (int) y, paint, textSize, color);
                adicionar += textSize * 5.5;
                }
    }


    /**
     * Starts animating the text.
     */
    public void play(){
        active = true;
        AnimatedTextManager.addText(this);
    }

    /**
     * Stops the animated text.
     */
    public void destroy(){
        active = false;
        AnimatedTextManager.removeText(this);
    }

    /**
     * Checks if it is equal to another object.
     * @param obj The object to compare to
     * @return {@code true} if the it is equal
     */
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof AnimatedText)) return false;
        AnimatedText o = (AnimatedText) obj;
        if (o.x == this.x && o.y == this.y && o.text.equals(this.text) && o.color == this.color){
            return true;
        }
        return false;
    }

    /**
     * Draws text, centered to the position given.
     * @param text The text to draw
     * @param canvas The {@link Canvas} to draw on
     * @param x The x position of the text
     * @param y The y position of the text
     * @param paint The {@link Paint} object to draw with
     * @param textSize The size of the text
     * @param color The color of the text
     */
    public void drawCenteredText(String text, Canvas canvas, int x, int y, Paint paint, int textSize, int color){
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);
        paint.setTextSize(scaledTextSize);
        paint.setColor(color);
        paint.setAlpha(currAlpha);
        Rect bounds = new Rect();
        // Get bounds of the text, then center
        paint.getTextBounds(text, 0, text.length(), bounds);
        x -= bounds.width() / 2;
//        y -= bounds.height() / 2;
        canvas.drawText(text, x, y, paint);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }
}