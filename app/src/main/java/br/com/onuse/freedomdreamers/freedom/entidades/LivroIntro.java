package br.com.onuse.freedomdreamers.freedom.entidades;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import br.com.onuse.freedomdreamers.freedom.utils.Animator;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;

public class LivroIntro extends Entidade{
    private Animator animator;
    /**
     * @param x Posição X do objeto
     * @param y Posição Y do objeto
     */
    public LivroIntro(int x, int y){
        super("livro_intro", 0, 0, x, y, 255);

        ArrayList<Bitmap> frames = new ArrayList<>();
        // Adiciona os frames
        for (int i = 0; i <= 6; i++){
            Bitmap frame = Assets.getBitmapFromMemory("intro_cena_b" + i);
            frames.add(frame);
        }
        animator = new Animator(frames);
        animator.setSpeed(90);
        animator.play();
        animator.update(System.currentTimeMillis());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Canvas canvas, Paint paint){
        drawCenteredBitmap(animator.sprite, canvas, paint, (int) x, (int) y);
        // Destroi esta entidade quando a animação estiver pronta
        if (animator.isDoneAnimation()){
            super.destroy();
            animator.stop();
        }
        animator.update(System.currentTimeMillis());
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
