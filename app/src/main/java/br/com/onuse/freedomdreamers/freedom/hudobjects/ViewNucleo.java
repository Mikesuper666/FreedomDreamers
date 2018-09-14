package br.com.onuse.freedomdreamers.freedom.hudobjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.View;

public class ViewNucleo extends View{
    public Canvas canvas;
    Typeface font;
    private int width;
    private int height;
    public static Resources resources;
    /**
     * @param context The {@link Context} ojeto de contexto para indicar qual activity está chamando
     * @param width A largura da tela
     * @param height A altura da tela
     */
    public ViewNucleo(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        this.resources = getResources();
    }
}
