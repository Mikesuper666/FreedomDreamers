package br.com.onuse.freedomdreamers.freedom.hudobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import br.com.onuse.freedomdreamers.freedom.managers.AnimatedTextManager;
import br.com.onuse.freedomdreamers.freedom.managers.HUDManager;

public class TextoAnimado {
    public double x, y;
    public String text;
    public int tamanhoTexto;
    public int color;
    public boolean active = false;
    public int currAlpha;
    public boolean centralizado;

    /**
     *
     * @param text Texto para ser mostrado na tela
     * @param x posição X da tela
     * @param y posição Y da tela
     * @param tamanhoTexto Tamanho da texto
     * @param color Cor do texto
     * @param startingAlpha A opacidade inicial do texto
     */
    public TextoAnimado(String text, int x, int y, int tamanhoTexto, int color, int startingAlpha, boolean centralizado){
        this.text = text;
        this.tamanhoTexto = tamanhoTexto;
        this.color = color;
        this.x = x;
        this.y = y;
        this.centralizado = centralizado;
        currAlpha = startingAlpha;
    }

    /**
     * Chamado quando o jogo ticks
     */
    public void tick(){}

    /**
     * Chamado quando o jogo renderiza
     * Renderiza o textoAnimado na tela
     * @param canvas The {@link Canvas} Objeto para ser compadado
     * @param paint The {@link Paint} Objeto para ser comparado
     */
    public void render(Canvas canvas, Paint paint){
        int adicionar = 0;
            for (String textSegment : text.split("\n")){
                if (centralizado)
                    densenhaTextoCentralizado(textSegment, canvas, (int) x, (int) y + adicionar, paint, tamanhoTexto, color);
                else
                    HUDManager.drawText(textSegment, canvas, (int) x, (int) y, paint, tamanhoTexto, color);
                adicionar += tamanhoTexto * 5.5;
                }
    }

    /**
     * Inicia a animação do texto
     */
    public void play(){
        active = true;
        AnimatedTextManager.addText(this);
    }

    /**
     * Para a animação do texto
     */
    public void destroy(){
        active = false;
        AnimatedTextManager.removeText(this);
    }

    /**
     * Checa se este objeto é igual ao outro objeto
     * @param obj Objeto para ser comparado
     * @return {@code true} verdadeiro se igual, falso se não
     */
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof TextoAnimado)) return false;
        TextoAnimado o = (TextoAnimado) obj;
        return o.x == this.x && o.y == this.y && o.text.equals(this.text) && o.color == this.color;
    }

    /**
     * Desenha o texto na posição passada
     * @param text Texto para desenhar
     * @param canvas A {@link Canvas} painel de desenho
     * @param x Posição X do texto
     * @param y Posição Y do texto
     * @param paint A {@link Paint} objeto de desenho
     * @param tamanhoTexto Tamanho do texto
     * @param color cor do texto
     */
    public void densenhaTextoCentralizado(String text, Canvas canvas, int x, int y, Paint paint, int tamanhoTexto, int color){
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (tamanhoTexto * relation);
        paint.setTextSize(scaledTextSize);
        paint.setColor(color);
        paint.setAlpha(currAlpha);
        Rect bounds = new Rect();
        //Obtenha limites do texto e, em seguida, centralize
        paint.getTextBounds(text, 0, text.length(), bounds);
        x -= bounds.width() / 2;
//        y -= bounds.height() / 2;
        canvas.drawText(text, x, y, paint);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }
}