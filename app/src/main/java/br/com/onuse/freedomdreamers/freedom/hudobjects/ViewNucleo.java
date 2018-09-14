package br.com.onuse.freedomdreamers.freedom.hudobjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import br.com.onuse.freedomdreamers.freedom.Utils.Assets;

public class ViewNucleo extends View{
    public Canvas canvas;
    private Assets assets;
    Typeface font;
    private int width;
    private int height;
    public static Resources resources;
    /**
     * @param context The {@link Context} objeto de contexto para indicar qual activity está chamando
     * @param width A largura da tela
     * @param height A altura da tela
     */
    public ViewNucleo(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        resources = getResources();
        inicializar();
    }

    private void inicializar(){
        //inicia, assets, main mangers, loops e seta as fontes
        assets = new Assets(getContext(), width, height);
        font = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // Onde tudo será desenhado
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTypeface(font);
        paint.setTextSize(150);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        // Always render the core manager
        manager.render(canvas, paint);
    }
}
