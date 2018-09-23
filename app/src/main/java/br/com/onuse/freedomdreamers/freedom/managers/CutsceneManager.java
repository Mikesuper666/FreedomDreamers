package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Objects;

import br.com.onuse.freedomdreamers.R;
import br.com.onuse.freedomdreamers.freedom.models.TextList;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class CutsceneManager {
    private int largura;
    private int altura;
    private boolean showText = true;
    private int numberText, ticks;
    private ArrayList<TextList> textList;
    private Background background;
    private Bitmap[] bgs = new Bitmap[7];
    private double[] xOffBG = new double[7];

    CutsceneManager(){
        largura = HUDManager.largura;
        altura = HUDManager.altura;
        String[] transition = NucleoManager.context.getResources().getStringArray(R.array.transition_intro);//get array from xml
        String[] introTexts = NucleoManager.context.getResources().getStringArray(R.array.cutscene_intro);//get array from xml
        textList = new ArrayList<>(TextListManager.ManageText(introTexts,transition)); //fills the list with the content kept in xml
        background = new Background();
        //set images to memory
        for (int i = 0; i <= 6; i++) {
            bgs[i] = Assets.getBitmapFromMemoryFullscreen("intro_cena_b"+i);
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
                if(textList.get(numberText).getTipoTexto() == 0) {
                    HUDManager.displayFadeMessage(textList.get(numberText).getTextToDisplay(),  //text
                            largura / textList.get(numberText).getxPosition(),                //position x
                            altura - textList.get(numberText).getyPosition(),               //position y
                            (textList.get(numberText).getDuration() - 60),                      //duration (-60 to compensate for fading)
                            textList.get(numberText).getTextSize(), Color.WHITE);               //color
                }else{
                    HUDManager.displayTypingText(textList.get(numberText).getTextToDisplay(),  //text
                            largura / textList.get(numberText).getxPosition(),              //xposition
                            altura / textList.get(numberText).getyPosition(),
                            textList.get(numberText).getPausa(),                                //pausa entre cada letra
                            textList.get(numberText).getTextSize(),
                            Color.BLACK,
                            false,
                            textList.get(numberText).getDuration());                               //pausa o texto após o digitar
                }
                showText = false;
            }

            if(textList.get(numberText).getTipoTexto() == 0 && ticks == textList.get(numberText).getDuration()){
                ticksContador();
            }else if(textList.get(numberText).getTipoTexto() == 1 && ticks == (textList.get(numberText).getTextToDisplay().length()
                    * textList.get(numberText).getPausa() + textList.get(numberText).getDuration())){
                ticksContador();
            }
        }else {
            ClearText();//free memory
            SEManager.playEffect(SEManager.Effect.FADE_TRANSITION, EstadoTela.TITULO);//goto title screen
        }
    }

    private void ticksContador(){
        showText = true;
        ticks = 0;
        numberText++;
ClearText();
    }

    public void render(Canvas canvas) {
       if (numberText < textList.size()) {
            if (textList.get(numberText).getImagem() != 0){
                //xOffBG[numberText] = background.offsetScrolling(canvas, bgs[]);
                //xOffBG[numberText] = ImageManager.processarImagem(canvas, bgs[numberText],textList.get(numberText), background);
            }else{
                canvas.drawBitmap(Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("background_black")), 0, 0, null);
            }
        }
    }


    private void ClearText(){TextoAnimadoManager.clear();}
}
