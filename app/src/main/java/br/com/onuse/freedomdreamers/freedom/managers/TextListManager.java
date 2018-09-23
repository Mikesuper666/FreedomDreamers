package br.com.onuse.freedomdreamers.freedom.managers;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.models.TextList;

public class TextListManager {
    private static ArrayList<TextList> textLists = new ArrayList<>();
    /*
     *This process is to fill array postion
     */
    public  static ArrayList<TextList> ManageText(String[] processText, String[] processTimers){

        String[] arrayProcess,arrayTimers;
        for(int i = 0; i < processText.length; i++){
            TextList newtextList = new TextList();
            arrayProcess = processText[i].split("#");//Processa os texto e modos de texto
            arrayTimers = processTimers[i].split("#");//processa o tempo de execução e posições dos textos
            newtextList.setTextToDisplay(arrayProcess[0]);
            newtextList.setxPosition(Integer.parseInt(arrayTimers[1]));
            newtextList.setyPosition(Integer.parseInt(arrayTimers[2]));
            newtextList.setDuration(Integer.parseInt(arrayProcess[2]));
            newtextList.setTextSize(Integer.parseInt(arrayProcess[1]));
            newtextList.setImagem(Integer.parseInt(arrayTimers[3]));
            newtextList.setPausa(Integer.parseInt(arrayTimers[4]));
            newtextList.setTipoTexto(Integer.parseInt(arrayTimers[0]));

            textLists.add(newtextList);
        }
        return textLists;
    }
}
