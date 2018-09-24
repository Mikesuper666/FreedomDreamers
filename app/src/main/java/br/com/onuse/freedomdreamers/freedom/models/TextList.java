package br.com.onuse.freedomdreamers.freedom.models;

public class TextList {
    private String textToDisplay;   //mostra a menssage
    private int xPosition;          //x pos
    private int yPosition;          //y pos
    private int duration;           //duração
    private int textSize;           //depende da fonte
    private int imagem;             //Imagem que será mostrada em background
    private int tipoTexto;          //texto
    private int pausa;              //pausa o texto para o modo typing

    public  TextList(){}

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(int tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    public int getPausa() {
        return pausa;
    }

    public void setPausa(int pausa) {
        this.pausa = pausa;
    }
}
