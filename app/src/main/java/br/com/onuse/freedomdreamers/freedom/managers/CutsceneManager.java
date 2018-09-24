package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Objects;

import br.com.onuse.freedomdreamers.R;
import br.com.onuse.freedomdreamers.freedom.entidades.EAState;
import br.com.onuse.freedomdreamers.freedom.entidades.Entidade;
import br.com.onuse.freedomdreamers.freedom.entidades.intro.LivroIntro;
import br.com.onuse.freedomdreamers.freedom.models.TextList;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class CutsceneManager {
    private int largura;
    private int altura;
    private boolean showText = true;
    private int numberText, ticks, tempoDuracao;
    private ArrayList<TextList> textList;
    private Background background;
   // private Bitmap[] bgs = new Bitmap[7];
    private double[] xOffBG = new double[7];
    private static Entidade atualEntidade = null;

    CutsceneManager(){
        largura = HUDManager.largura;
        altura = HUDManager.altura;
        String[] transition = NucleoManager.context.getResources().getStringArray(R.array.transition_intro);//get array from xml
        String[] introTexts = NucleoManager.context.getResources().getStringArray(R.array.cutscene_intro);//get array from xml
        textList = new ArrayList<>(TextListManager.ManageText(introTexts,transition)); //fills the list with the content kept in xml
        background = new Background();
        //set images to memory
       /* for (int i = 0; i <= 6; i++) {
            bgs[i] = Assets.getBitmapFromMemoryFullscreen("intro_cena_b"+i);
        }*/
    }

    public void init(){
        iniciaEntidade(new LivroIntro(largura / 2,altura / 2));
    }

    /**
     * Inicia uma nova entidade {@link Entidade}
     * @param entidade {@link Entidade} objeto
     */
    private static void iniciaEntidade(Entidade entidade){
        // Inicia uma nova entidade
        atualEntidade = entidade;
        atualEntidade.fadeIn(100);
    }
    /*
     * ticks para manipular a logica de texto e movimentos da tela
     * atualiza os repectivas posições e estados
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
                    HUDManager.displayTypingText(textList.get(numberText).getTextToDisplay(),      //text
                            textList.get(numberText).getxPosition(),                               //xposition
                            textList.get(numberText).getyPosition(),
                            textList.get(numberText).getPausa(),                                   //pausa entre cada letra
                            textList.get(numberText).getTextSize(),
                            Color.WHITE,
                            false,
                            textList.get(numberText).getDuration());                               //pausa o texto após o digitar
                    tempoDuracao = (textList.get(numberText).getTextToDisplay().replace(" ","").length() * textList.get(numberText).getPausa())
                            + textList.get(numberText).getDuration() * textList.get(numberText).getTextToDisplay().split("§").length;
                }
                showText = false;
            }

            if(textList.get(numberText).getTipoTexto() == 0 && ticks == textList.get(numberText).getDuration()){
                ticksContador();
            }else if(textList.get(numberText).getTipoTexto() == 1 && ticks == tempoDuracao) {
                ticksContador();
            }
        }else {
            SEManager.playEffect(SEManager.Effect.FADE_TRANSITION, EstadoTela.TITULO);//goto title screen
        }
    }

    private void ticksContador(){
        showText = true;
        ticks = 0;
        numberText++;
    }

    public void render(Canvas canvas) {

        if(textList.get(numberText).getImagem() == 1) {
            canvas.drawBitmap(Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("background_black")), 0, 0, null);
            atualEntidade.setState(EAState.ESPERA);
        }else if (textList.get(numberText).getImagem() == 2)
            atualEntidade.setState(EAState.ATAQUE);
        else if(textList.get(numberText).getImagem() == 3) {
            atualEntidade.setState(EAState.DANO);
        }
    }
}
