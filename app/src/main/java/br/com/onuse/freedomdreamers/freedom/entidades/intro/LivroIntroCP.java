package br.com.onuse.freedomdreamers.freedom.entidades.intro;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.entidades.EAState;
import br.com.onuse.freedomdreamers.freedom.entidades.Entidade;
import br.com.onuse.freedomdreamers.freedom.utils.Animator;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class LivroIntroCP extends Entidade {
    private Bitmap currentSprite;
    private ArrayList<Bitmap> sprites = new ArrayList<>();
    Bitmap zoomBitmap;
    private Animator animator;
    private float x, y;
    /**
     * @param x Posição X do objeto
     * @param y Posição Y do objeto
     */
    public LivroIntroCP(int x, int y){
        super("LivroIntro", 0, 0, x, y, 255);

        // Add ghost sprites (idle, attack, damage)
        for (int i = 0; i <= 5; i++){
            sprites.add(Assets.getBitmapFromMemoryFullscreen("intro_cena_b" + i));
        }

        // 0 = Idle, 1 = Attack, 2 = Damage
        currentSprite = sprites.get(0);


        ArrayList<Bitmap> frames = new ArrayList<>();
        // Add explode frames
        for (int i = 0; i <= 5; i++){
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
    public void setState(EAState newState){
        super.state = newState;
        switch(newState){
            case ESPERA:
                currentSprite = sprites.get(0);
                break;
            case ATAQUE:

                break;
            case DANO:
                //super.fadeOut(100);

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
                drawCenteredBitmap(currentSprite, canvas, paint, (int) super.x, (int) super.y);
                break;
            case ATAQUE:
                drawCenteredBitmap(animator.sprite, canvas, paint, (int) super.x, (int) super.y);
                // Destroy this entity if the animation is done
                if (animator.isDoneAnimation()){
                    animator.pause();
                }
                animator.update(System.currentTimeMillis());
            break;
            case DANO:
               /* drawCenteredBitmap(animator.Frame(4), canvas, paint, (int) super.x, (int) super.y);
                paint.setAlpha(oldAlpha);
                drawCenteredBitmap(animator.Frame(5), canvas, paint, (int) super.x, (int) super.y);
                paint.setAlpha(255);*/
                x+=0.01f;
                y+=0.01f;

                canvas.scale((1.0f+x), 1.0f);
                zoomBitmap = Bitmap.createScaledBitmap(animator.Frame(4),((int) super.x / 2), (int) super.y +(int)y, false);
                drawCenteredBitmap(zoomBitmap, canvas, paint, (int) super.x, (int) super.y);



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
    public void drawCenteredBitmap(Bitmap bitmap, Canvas canvas, Paint paint, int x, int y){
        x -= (bitmap.getWidth() / 2);
        y -= (bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, x, y, paint);
    }
}
