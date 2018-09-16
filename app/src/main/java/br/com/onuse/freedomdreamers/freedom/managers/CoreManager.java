package br.com.onuse.freedomdreamers.freedom.managers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class CoreManager {
    public static int largura;
    public static int altura;
    private int tick = 0;
    private int xoff = 0;
    boolean left = true;
    private static HUDManager hud;
    private static Background background;
    private static SEManager se;
    private static GameManager gm;
    private Assets assets;
    public static Context context;
    public static ScreenState state;
    public static boolean allowTouch = true;
    public CoreManager(Context context, int largura, int altura){
        this.context = context;
        this.largura = largura;
        this.altura = altura;
        init();
    }

    /**
     * Chamado quando este manager é inicializado.
     */
    public void init(){
        // Initialize HUD manager, background manager, and SE manager
        // Inicia o gerenciador de HUD, o gerenciador de background e o gerente de SE
        // carrega sound effects
        state = ScreenState.TITLE;
        hud = new HUDManager();
        background = new Background();
        se = new SEManager();
        gm = new GameManager();
        //Sound.loadSounds();
        // Inicia pela primeira vez onStateChange para HUD e Background
        hud.onStateChange(state, state);
        background.onStateChange(state ,state);
        //gm.onStateChange(state, state);
    }

    /**
     * Chamado quando tela pode ser tocada
     * @param e o {@link MotionEvent} é um listener
     */
    public void touchEvent(MotionEvent e){
        if (allowTouch){
         //   hud.touchEvent(e);
        }
    }
    /**
     * Chamado uando o usuário dá o primeiro toque
     * @param e o {@link MotionEvent} é um listener
     */
    public void preTouchEvent(MotionEvent e){
        if (allowTouch){
        //    hud.preTouchEvent(e);
        }
    }

    /**
     * Chamado quando o jogo começa.
     * Toca todos os gerentes em ordem.
     */
    public void tick(){
        // Tick all managers in order
        background.tick();
        hud.tick();
       /// se.tick();
     //   gm.tick();
    }

    /**
     * Seta se a tela pode ou não ser tocada.
     * @param touch parametro de toque
     */
    public static void setAllowTouch(boolean touch){
        allowTouch = touch;
    }

    /**
     * Chamado quando o jogo renderiza
     * Renderiza tudo em ordem.
     * @param canvas The {@link Canvas} para desenhar
     * @param paint The {@link Paint} objetos de desenho
     */
    public void render(Canvas canvas, Paint paint){
        //Renderiza todos manages em ordem, isto determina o layer

        hud.render(canvas, paint);
        background.render(canvas, paint);
      //  se.render(canvas, paint);
    }
}
