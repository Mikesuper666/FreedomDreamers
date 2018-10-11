package br.com.onuse.freedomdreamers.freedom.entidades.intro;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.Objects;

import br.com.onuse.freedomdreamers.freedom.entidades.Entidade;
import br.com.onuse.freedomdreamers.freedom.utils.Animator;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class LivroIntro extends Entidade {
    private Bitmap zoomObject;
    private Animator animator;
    private float xScale = 1, yScale = 1;
    //inicia a escala da matriz
    private Matrix scaleMatrix = new Matrix();
    /**
     * @param x Posição X do objeto
     * @param y Posição Y do objeto
     */
    public LivroIntro(int x, int y){
        super("LivroIntro", 0, 0, x, y, 0);

        // Adiciona os sprites dentro do array para ser acessado em seus respectivos frames
        ArrayList<Bitmap> frames = new ArrayList<>();
        for (int i = 0; i <= 4; i++){
            Bitmap frame = Assets.getBitmapFromMemoryFullscreen("intro_cena_b" + i);
            frames.add(frame);
        }
        animator = new Animator(frames);
        animator.setSpeed(300);
        animator.play();
        animator.update(System.currentTimeMillis());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(CUTEstate newState){
        super.state = newState;
        switch(newState){
            case ESPERA:
                break;
            case ABRINDO_LIVRO:
                super.fadeIn(75);
                break;
            case SUMINDO_CENARIO:
                super.fadeOut(50);
                break;
            case ZOOM:

                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Canvas canvas, Paint paint){
        int oldAlpha = paint.getAlpha();
        paint.setAlpha(super.currAlpha);
        switch(super.state){
            case ESPERA:
                break;
            case MOSTRANDO:
                drawCenteredBitmap(animator.Frame(0), canvas, paint, (int) super.x, (int) super.y);
                paint.setAlpha(oldAlpha);
                break;
            case ABRINDO_LIVRO:
                drawCenteredBitmap(animator.sprite, canvas, paint, (int) super.x, (int) super.y);
                // Destroy this entity if the animation is done
                if (animator.isDoneAnimation()){
                    animator.pause();
                }
                animator.update(System.currentTimeMillis());
                break;
            case SUMINDO_CENARIO:
                drawCenteredBitmap(animator.Frame(4), canvas, paint, (int) super.x, (int) super.y);
                paint.setAlpha(oldAlpha);
                drawCenteredBitmap(Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("intro_cena_b5")), canvas, paint, (int) super.x, (int) super.y);
                paint.setAlpha(255);
                break;
            case ZOOM:
                /*
                FUNCIONA SO SE FOR O PRIMEIRO A SER PASSADO
                PRECISO REVER PORQUE ESSE CÓDIGO ESTÁ PROBLEMATICO

                zoomObject = Objects.requireNonNull(Assets.getBitmapFromMemoryFullscreen("intro_cena_b5"));
                xScale+=0.01f;
                yScale+=0.01f;
                // Seta a escala do X e Y com o valor
                scaleMatrix.setScale(xScale, yScale, zoomObject.getWidth() /2, zoomObject.getHeight() / 1.5f);
                //desenhando já centralizado
                canvas.drawBitmap(zoomObject, scaleMatrix, paint);*/
                break;
        }
    }

    /**
     * Desenha centralizado na posição passado
     *
     * @param bitmap {@link Bitmap} imagem a ser pintada
     * @param canvas {@link Canvas} objeto de painel para a pintura
     * @param paint {@link Paint} objeto de pintura
     * @param x Posição X do objeto
     * @param y Posição Y do objeto
     */
    private void drawCenteredBitmap(Bitmap bitmap, Canvas canvas, Paint paint, int x, int y){
        x -= (bitmap.getWidth() / 2);
        y -= (bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, x, y, paint);
    }
}
