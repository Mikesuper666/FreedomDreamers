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
     * Initializes all {@link Bitmap} image assets, and sound assets.
     */
    public void init(){
        // Backgrounds
        bitmapDb.put("nuvens", getBitmap(context, "backgrounds/intro/nuvens.png", false));
        bitmapDb.put("intro", getBitmap(context, "backgrounds/intro/intro_a.png", false));
    }
    /**
     * Returns a {@link Bitmap} object from a file, scaled accordingly to the screen size.
     * @param context The {@link Context} to use
     * @param filePath The file path
     * @return A {@link Bitmap}
     */
    public static Bitmap getBitmap(Context context, String filePath, boolean fitLargura) {
        AssetManager assetManager = context.getAssets();
        // Create a new input stream, and open the path
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            // Assuming it is a bitmap, decode the stream
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {}

        // Escala o bitmap para o tamanho proporcional a tela
        double x = (fitLargura) ? largura :bitmap.getWidth() * largura / 1080;
        double y = (fitLargura) ? altura : bitmap.getHeight() * altura / 1920;


        return Bitmap.createScaledBitmap(bitmap, (int) x, (int) y, true);
    }

    /**
     * Returns the {@link AssetFileDescriptor} of a sound file.
     * @param context The {@link Context} to use
     * @param filePath The file path
     * @return A {@link AssetFileDescriptor}
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
     * Returns a {@link AssetFileDescriptor} from memory.
     * @param name The name of the {@link AssetFileDescriptor}
     * @return A {@link AssetFileDescriptor}
     */
    public static AssetFileDescriptor getSoundDescFromMemory(String name){
        if (soundDb.containsKey(name)){
            return soundDb.get(name);
        }
        return null;
    }

    /**
     * Returns a {@link Bitmap} from memory.
     * @param name The name of the {@link Bitmap}
     * @return A {@link Bitmap}
     */
    public static Bitmap getBitmapFromMemory(String name){
        if (bitmapDb.containsKey(name)){
            return bitmapDb.get(name);
        }
        return null;
    }
}
