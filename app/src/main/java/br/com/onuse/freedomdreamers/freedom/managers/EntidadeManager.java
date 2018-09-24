package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import br.com.onuse.freedomdreamers.freedom.entidades.Entidade;

public class EntidadeManager {
    private static ArrayList<Entidade> entidades;
    public EntidadeManager(){
        entidades = new ArrayList<>();
    }
    /**
     * Adiciona um {@link Entidade} para lista.
     * @param entidade O {@link Entidade} para adicionar
     */
    public static void adicionarEntidade(Entidade entidade){
        entidades.add(entidade);
    }
    /**
     * Remove uma {@link Entidade} from the list.
     * @param entidade A {@link Entidade} para se removida
     */
    public static void removerEntidade(Entidade entidade){
        entidades.remove(entidade);
    }
    /**
     * Remove todas entidades da lista
     */
    public static void clear(){
        entidades.clear();
    }
    /**
     * Chamado quando o jogo ticks
     * Ticks para todas entidades
     */
    public void tick(){
        //Cria um backup para evitar exeções
        ArrayList<Entidade> temp = new ArrayList<>(entidades);
        for (Entidade e : temp){
            e.tick();
        }
    }
    /**
     * É chamado quando o jogo renderiza
     * Renderiza todas entidades
     * @param canvas {@link Canvas} paine de pintura
     * @param paint {@link Paint} objeto
     */
    public void render(Canvas canvas, Paint paint){
        //Cria um backup para evitar exeções
        ArrayList<Entidade> temp = new ArrayList<>(entidades);
        for (Entidade e : temp){
            e.render(canvas, paint);
        }
    }
}
