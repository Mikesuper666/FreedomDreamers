package br.com.onuse.freedomdreamers.freedom.gameobjects;

import android.os.Handler;

public class Loop {
    private static int FPS = 60;
    public static boolean paused = false;
    private Handler handler;
    private Runnable runnable;
    private final NucleoView c;
    private long beginTime;
    private long timeDiff;
    private int sleepTime;
    private int framesSkipped;
    private int framePeriod = (int) (1000 / FPS);
    private int maxFrameSkips = 5;

    /**
     * Inicializa o Loop do jogo
     * @param nucleoView {@link NucleoView} classe acessada
     */
    Loop(NucleoView nucleoView){
        sleepTime = 0;
        c = nucleoView;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                beginTime = System.currentTimeMillis();
                framesSkipped = 0;
                // Tick and render main manager
                if (!paused){
                    c.tick();
                    c.render();
                }
                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int) (framePeriod - timeDiff);
                // Se ficar para trás, pule alguns quadros e marque sem renderizar
                while (sleepTime < 0 && framesSkipped < maxFrameSkips) {
                    if (!paused){
                        c.tick();
                    }
                    sleepTime += framePeriod;
                    framesSkipped++;
                }
                if (framesSkipped > 0){
                    // mostre o aviso nos Warning da IDE
                    System.out.println("Não pode renderizar. Pulou: " + framesSkipped + " frames");
                }
                // re-inicie o processo
                handler.postDelayed(runnable, sleepTime);
            }
        };
        // inicia o processo pela primeira vez
        handler.post(runnable);
    }
}
