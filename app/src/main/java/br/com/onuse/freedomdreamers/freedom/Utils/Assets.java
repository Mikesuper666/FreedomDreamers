package br.com.onuse.freedomdreamers.freedom.Utils;

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
    private static int width;
    private static int height;
    public Assets(Context context, int width, int height){
        this.width = width;
        this.height = height;
        this.context = context;
        bitmapDb = new HashMap<>();
        soundDb = new HashMap<>();
        messages = new HashMap<>();
    }

    private void init(){
        bitmapDb.put("background_mountains", getBitmap(context, "backgrounds/mountains.png"));
    }
    /**
     * Returns a {@link Bitmap} objeto de um arquivo, dimensionado de acordo com o tamanho da tela.
     * @param context The {@link Context} to use
     * @param caminhoArquivo o caminho do arquivo que está sendo passado
     * @return A {@link Bitmap}
     */
    public static Bitmap getBitmap(Context context, String caminhoArquivo) {
        AssetManager assetManager = context.getAssets();
        // Crie um novo fluxo de entrada e defini o caminho de pastas
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(caminhoArquivo);
            // Supondo que seja um bitmap, decodifique o fluxo
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {}

        // Bitmap de escala para altura e largura da tela
        double x = bitmap.getWidth() * width / 1920;
        double y = bitmap.getHeight() * height / 1080;

        return Bitmap.createScaledBitmap(bitmap, (int) x, (int) y, true);
    }
}
