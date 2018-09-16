package br.com.onuse.freedomdreamers.freedom.hudobjects;

public class EsmaecimentoTexto extends TextoAnimado{
    private int tick = 0;
    private int maxTick;
    private int time;
    /**
     *
     * @param text Texto para ser desenhado
     * @param ticks tempo de duração na tela
     * @param x posição X do texto
     * @param y posição Y do texto
     * @param tamanhoTexto tamanho do texto
     * @param color The color of the text
     */
    public EsmaecimentoTexto(String text, int ticks, int x, int y, int tamanhoTexto, int color){

        super(text, x, y, tamanhoTexto, color, 0, true);
        // permite 30 ticks de fade in e fade out
        maxTick = ticks + 60;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(){
        if (super.active){
            // Esmaecimento por 30 tricks
            if (tick >= 0 && tick <= 30){
                // Increase alpha by 8 until 255
                if (super.currAlpha + 8 > 255){
                    super.currAlpha = 255;
                }else{
                    super.currAlpha += 8;
                }
            }
            // Esmaecimento por 30 tricks
            if (tick >= (maxTick - 30) && tick <= maxTick){
                // Decrease alpha by 8 until 0
                if (super.currAlpha - 8 < 0){
                    super.currAlpha = 0;
                }else{
                    super.currAlpha -= 8;
                }
            }
            // fim
            if (tick == maxTick){
                super.destroy();
            }
            tick++;
        }
    }
}
