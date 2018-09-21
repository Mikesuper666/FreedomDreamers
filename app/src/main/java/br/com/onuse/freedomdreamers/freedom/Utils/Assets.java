package br.com.onuse.freedomdreamers.freedom.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Assets {
    private static HashMap<String, Bitmap> bitmapDb;
    private static HashMap<String, AssetFileDescriptor> soundDb;
    private static HashMap<String, String> messages;
    private Context context;
    private static int largura;
    private static int altura;
    public Assets(Context context, int largura, int altura){
        this.largura = largura;
        this.altura = altura;
        this.context = context;
        bitmapDb = new HashMap<>();
        soundDb = new HashMap<>();
        messages = new HashMap<>();
        init();
    }

    /**
     * Inicia todos {@link Bitmap} imagems, e sons assets.
     */
    public void init(){
        //intro cutscene (objetos que faremo parallax)
        for (int i = 0; i <= 6; i++) {
            bitmapDb.put("intro_cena_b"+ i, getBitmap(context, "backgrounds/intro/cena_b/intro_"+i+".png"));
        }

        // imagem Tela preta
        bitmapDb.put("background_black", getBitmap(context, "backgrounds/black.png"));
    }
    /**
     * Returna {@link Bitmap} objeto do arquivo, escalado de acordo com o tamanho da tela.
     * @param context {@link Context} contexto da classe ue acessa
     * @param filePath caminho do arquivo que será acessado
     * @return A {@link Bitmap}
     */
    public static Bitmap getBitmap(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        //Cria um novo input stream, e abre o caminho
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            //Assumindo que é um bitmap, decodifica o stream
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {}

        // Escala o bitmap para o tamanho proporcional a tela
        double x = bitmap.getWidth() * largura / 1920;
        double y = bitmap.getHeight() * altura / 1080;

        return Bitmap.createScaledBitmap(bitmap, (int) x, (int) y, true);
    }

    /**
     * Retorna o {@link AssetFileDescriptor} do aruivo de som
     * @param context {@link Context} contexto da classe ue acessa
     * @param filePath caminho do arquivo que será acessado
     * @return {@link AssetFileDescriptor}
     */
    public static AssetFileDescriptor getSoundDesc(Context context, String filePath){
        try{
            AssetFileDescriptor afd = context.getAssets().openFd(filePath);
            return afd;
        }catch(IOException e){
            return null;
        }
    }

    /**
     * Returna {@link AssetFileDescriptor} da memoria
     * @param name o nome do {@link AssetFileDescriptor}
     * @return {@link AssetFileDescriptor}
     */
    public static AssetFileDescriptor getSoundDescFromMemory(String name){
        if (soundDb.containsKey(name)){
            return soundDb.get(name);
        }
        return null;
    }

    /**
     * Returna {@link Bitmap} da memoria
     * @param name o nome de {@link Bitmap}
     * @return {@link Bitmap}
     */
    public static Bitmap getBitmapFromMemory(String name){
        if (bitmapDb.containsKey(name)){
            return bitmapDb.get(name);
        }
        return null;
    }

    /**
     * Returns a {@link Bitmap} from memory.
     * @param name The name of the {@link Bitmap}
     * @return A {@link Bitmap} rescaled for a fullscren backgrounds
     */
    public static Bitmap getBitmapFromMemoryFullscreen(String name){
        if (bitmapDb.containsKey(name)){
            return Bitmap.createScaledBitmap(bitmapDb.get(name), largura, altura, true);
        }
        return null;
    }
}
