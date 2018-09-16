package br.com.onuse.freedomdreamers.freedom.gameobjects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;


import br.com.onuse.freedomdreamers.freedom.managers.NucleoManager;
import br.com.onuse.freedomdreamers.freedom.managers.Soundtrack;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class NucleoView extends View {
    public Canvas canvas;
    Typeface font;
    private NucleoManager manager;
    private Soundtrack soundtrack;
    private Assets assets;
    private Loop loop;
    private int largura;
    private int altura;
    public static Resources resources;

    /**
     * @param context O objeto {@link Context} passado pela activity starter
     * @param largura A largura da View
     * @param altura A altura da view
     */
    public NucleoView(Context context, int largura, int altura) {
        super(context);
        this.largura = largura;
        this.altura = altura;
        this.resources = getResources();
        init();
    }

    /**
     * Inicializa o gerenciador de assets, o gerenciador principal, o loop de jogos e salva o arquivo  e fontes.
     */
    private void init(){
        assets = new Assets(getContext(), largura, altura);
        manager = new NucleoManager(getContext(), largura, altura);
        loop = new Loop(this);
        font = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // Onde tudo é desenhado
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onTouchEvent(MotionEvent e){
        // Fonte de detecção de toques. Verificar apenas quando o usuário soltar
        if (e.getAction() == MotionEvent.ACTION_UP){
            manager.touchEvent(e);
        }else if (e.getAction() == MotionEvent.ACTION_DOWN){
            manager.preTouchEvent(e);
        }
        return true;
    }
    /**
     * Chamado quando o jogo é processado. Desenha gráficos.
     */
    public void render(){
        // Invalidar recupera onDraw ()
        invalidate();
    }

    /**
     * Chamado quando o jogo começa. Gerenciador de managers.
     */
    public void tick(){
        // Ticks gerenciador de ticks
        manager.tick();
    }
}
