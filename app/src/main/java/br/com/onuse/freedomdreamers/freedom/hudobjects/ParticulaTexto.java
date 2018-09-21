package br.com.onuse.freedomdreamers.freedom.hudobjects;
import br.com.onuse.freedomdreamers.freedom.managers.HUDManager;

public class ParticulaTexto extends TextoAnimado{
    private int ticks;
    private int tick = 0;
    private double directionVec;
    private int vertexX;
    private int vertexY;
    private double initialA;
    /**
     * @param text Texto para ser desenhado
     * @param ticks Tempo de duração da animação
     * @param x posição X do texto
     * @param y posição Y do texto
     * @param textSize Tamanho do texto
     * @param color cor do texto
     * @param directionVec direção do texto vetor(negativa ou positiva)
     */
    public ParticulaTexto(String text, int ticks, int x, int y, int textSize, int color, double directionVec){
        super(text, x, y, textSize, color, 255, true);
        this.ticks = ticks;
        this.directionVec = directionVec;
        this.vertexX = x;
        this.vertexY = y;
        initialA = 0.004;
    }
    public ParticulaTexto(String text, int ticks, int x, int y, int textSize, int color, double directionVec, double initialA){
        super(text, x, y, textSize, color, 255, true);
        this.ticks = ticks;
        this.directionVec = directionVec;
        this.vertexX = x;
        this.vertexY = y;
        this.initialA = initialA;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(){
        if (super.active){
            if (tick != ticks){
                // Incrementar x pelo valor dado (se positivo, viaja direito, se negativo, viaja para a esquerda)
                super.x += directionVec;
                // Use the equation y = -a(x - vertexX)^2 + vertexY
                // Escala o valor com a largura
                double a = initialA * 1920 / HUDManager.largura;
                super.y = (int) (a * Math.pow(x - this.vertexX, 2) + this.vertexY);
            }else{
                super.destroy();
            }
            tick++;
        }
    }
}
