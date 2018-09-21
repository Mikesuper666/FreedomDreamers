package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Objects;

import br.com.onuse.freedomdreamers.R;
import br.com.onuse.freedomdreamers.freedom.models.TextList;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class CutsceneManager {
    private static int width;
    private static int height;
    private boolean showText = true;
    private boolean showImage = false;
    private int numberText, ticks;
    private ArrayList<TextList> textList;
    private Background background;
    private Bitmap[] bgs = new Bitmap[6];
    private double[] xOffBG = new double[6];
    private int transition[] = NucleoManager.context.getResources().getIntArray(R.array.transitions);//get array from xml

    CutsceneManager(){
        width = HUDManager.largura;
        height = HUDManager.altura;
        String[] introTexts = NucleoManager.context.getResources().getStringArray(R.array.cutscene_intro);//get array from xml
        textList = new ArrayList<>(TextListManager.ManageText(introTexts)); //fills the list with the content kept in xml
        background = new Background();
        //set images to memory
        for(int i=0;i<6;i++){
            bgs[i] = Assets.getBitmapFromMemoryFullscreen("background_intro_"+i);
        }
    }

    public void init(){

    }

    /*
     *ticks manipulates the logic of texts and movements displayed on the screen,
     * updated their respective positions and states
     */
    public void ticks() {
        ticks++;
        if (numberText < textList.size()) {
            if (showText) {
                HUDManager.displayFadeMessage(textList.get(numberText).getTextToDisplay(),  //text
                        width / textList.get(numberText).getxPosition(),                //position x
                        height - textList.get(numberText).getyPosition(),               //position y
                        (textList.get(numberText).getDuration() - 60),                      //duration (-60 to compensate for fading)
                        textList.get(numberText).getTextSize(), Color.WHITE);               //color
                showText = false;
            }

            if (ticks == textList.get(numberText).getDuration()) {
                showText = true;
                ticks = 0;
                numberText++;
            }



        }else {
            ClearText();//free memory
            SEManager.playEffect(SEManager.Effect.FADE_TRANSITION, EstadoTela.TITULO);//goto title screen
        }
    }

    public void render(Canvas canvas) {
        if (numberText < transition.length) {
            if (transition[numberText] == 1){

                xOffBG[3] = background.offsetScrolling(canvas, bgs[3], xOffBG[3], 1800, true);//stars
                xOffBG[5] = background.offsetScrolling(canvas, bgs[5], xOffBG[5], 2500, false);//moon
                xOffBG[4] = background.offsetScrolling(canvas, bgs[4], xOffBG[4], 3000, false);//castle
                xOffBG[2] = background.offsetScrolling(canvas, bgs[2], xOffBG[2], 800, true);//florest back
                xOffBG[1] = background.offsetScrolling(canvas, bgs[1], xOffBG[1], 500, true);//florest front
            }else{
                canvas.drawBitmap(Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("background_black")), 0, 0, null);
            }
        }
    }


    private void ClearText(){TextoAnimadoManager.clear();}
}
