package br.com.onuse.freedomdreamers.freedom.hudobjects;

public class DigitandoTexto extends TextoAnimado {
    private int tick = 0;
    private int wait = 0;
    private int tickDelay;
    private boolean typing = true;
    private String currentText = "";
    private int currentIndex = 0;
    private String text;

    /**
     *
     * @param text Texto para desenhar
     * @param tickDelay tempo de espera em cada caractere
     * @param x posição X do texto
     * @param y posição Y do texto
     * @param tamanhoTexto tamanho do texto
     * @param color cor Texto
     * @param centralizado centraliza o texto (true=sim false=não)
     */
    public DigitandoTexto(String text, int x, int y, int tickDelay, int tamanhoTexto, int color, boolean centralizado){
        super("", x, y, tamanhoTexto, color, 255, centralizado);
        this.tickDelay = tickDelay;
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(){
        if (super.active){
            if (typing){
                // Checagem inicial para ticks de espera
                if (wait > 0){
                    wait--;
                    return;
                }
                // Digite next char a cada intervalo de acordo com o atraso do tick
                if (tick % tickDelay == 0){
                    if (currentIndex <= text.length() - 1){
                        // Pular espaçoes brancos
                        while (text.charAt(currentIndex) == ' '){
                            currentText += " ";
                            currentIndex++;
                        }
                        // Checa por simbolos para pausas ('§' tempo definido)
                        if (text.charAt(currentIndex) == '§'){
                            wait += 100;
                            currentIndex++;
                            return;
                        }
                        // Anexe o próximo caractere e continue
                        currentText += text.charAt(currentIndex);
                        super.text = currentText;
                        currentIndex++;
                    }else{
                        typing = false;
                    }
                }
                tick++;
            }
        }
    }
    public boolean finished(){
        return typing;
    }
}
