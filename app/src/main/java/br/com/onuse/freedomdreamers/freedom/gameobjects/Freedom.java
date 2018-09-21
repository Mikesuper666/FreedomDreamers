package br.com.onuse.freedomdreamers.freedom.gameobjects;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class Freedom extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Seta a orientação da tela
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Usado para determinar em que tamanho tudo deve ser dimensionado. A única coisa ruim é que ele exige um nível de API 17+ devido a getRealMetrics ()
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int altura = displaymetrics.heightPixels;
        int largura = displaymetrics.widthPixels;
        //  Criar nova View com largura e altura da tela
        setContentView(new NucleoView(this, largura, altura));
        View decorView = getWindow().getDecorView();
        // Definir sinalizadores do sistema para permitir a barra de navegação em tela inteira e transparente não aderente
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }

    @Override
    public void onBackPressed() {}
    @Override
    public void onPause(){
        super.onPause();
        //Soundtrack.pause();
        Loop.paused = true;
    }
    @Override
    public void onResume(){
        super.onResume();
        //Soundtrack.resume();
        Loop.paused = false;

        //Reset e atualização de posição
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }
}
