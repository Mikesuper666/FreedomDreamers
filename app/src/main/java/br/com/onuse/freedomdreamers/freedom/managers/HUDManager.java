package br.com.onuse.freedomdreamers.freedom.managers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import br.com.onuse.freedomdreamers.freedom.hudobjects.FadedText;
import br.com.onuse.freedomdreamers.freedom.hudobjects.ParabolicText;
import br.com.onuse.freedomdreamers.freedom.hudobjects.TypingText;
import br.com.onuse.freedomdreamers.freedom.utils.Assets;


public class HUDManager {
    public static int largura = 0;
    public static int altura = 0;
    private static Bitmap play, back, info, start;
    private static ButtonManager buttonManager;
    private static AnimatedTextManager animatedTextManager;
    private static EntityManager entityManager;
    public static int selection = 0;
    public static int infoState = 0;
    public static int hudEndXPositions[] = new int[5];
    public static int hudEndYPositions[] = new int[5];

    public static int[] colors = {Color.RED, Color.rgb(75, 75, 255), Color.CYAN, Color.rgb(255, 80, 0), Color.GREEN};
    public HUDManager(){
        this.largura = CoreManager.largura;
        this.altura = CoreManager.altura;
        // Init new button manager
        buttonManager = new ButtonManager();
        animatedTextManager = new AnimatedTextManager();
        entityManager = new EntityManager();
        // Load all button bitmaps needed
        play = Assets.getBitmapFromMemory("button_play");
        back = Assets.getBitmapFromMemory("button_back");
        info = Assets.getBitmapFromMemory("button_info");
        start = Assets.getBitmapFromMemory("button_start");
        onStateChange(ScreenState.TITLE, ScreenState.TITLE);
    }

    /**
     * Called when the game ticks.
     */
    public void tick(){
        // Tick button manager + faded text
       // entityManager.tick();
       // buttonManager.tick();
        animatedTextManager.tick();
    }

    /**
     * Called when the user lets go of the screen.
     * @param e The {@link MotionEvent} to listen to
     */
    public void touchEvent(MotionEvent e){
       // buttonManager.checkButtons(e);
    }
    /**
     * Called when the user initially taps the screen.
     * @param e The {@link MotionEvent} to listen to
     */
    public void preTouchEvent(MotionEvent e){
        //buttonManager.checkButtonPretouch(e);
    }

    /**
     * Called when the {@link ScreenState} changes.
     * Creates all buttons.
     * @param oldState The previous {@link ScreenState}
     * @param novoEstado The new {@link ScreenState}
     */
    public static void onStateChange(ScreenState oldState, ScreenState novoEstado){
        // Clear buttons no matter what
        //buttonManager.clearButtons();
       // entityManager.clear();
        animatedTextManager.clear();
        //Dependendo do estado de tela de cada estado
        switch (novoEstado){

            case TITLE:
                // Create play and info buttons
                HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 8, 11, Color.rgb(0,191,255), true);
                HUDManager.displayTypingText("Choose a character...", largura / 3, altura / 3, 10, 11, Color.rgb(0,191,255), false);

                // Reset info state
                infoState = 0;
                break;


            case INFO:
                // Create back and next button
                break;

            case LOAD:
                // Create back and start buttons

                break;

            case CHAR_SELECT:
                HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                break;

            case BATTLE:
                break;

            case INVENTORY:
                // When the player enters the inventory screen (0)
                HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                break;
            case SHOP:
                HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                break;
        }
    }

    /**
     * Called when the game renders.
     * Renders all text and images.
     * @param canvas The {@link Canvas} to draw on
     * @param paint The {@link Paint} object to draw with
     */
    public void render(Canvas canvas, Paint paint){
        //Rebderiza todos os textos, HUDs, items, etc... depende do estado de tela.
        switch(CoreManager.state){

            // Tela titulo do jogo
            case TITLE:
                // Title
                drawText("texto",canvas, largura / 4, altura / 4, paint, 11, Color.RED);
                break;

            // Info Screen
            case INFO:
                // Draw page number
                HUDManager.displayTypingText("Choose a character...", largura / 4, altura / 4, 2, 11, Color.rgb(0,191,255), true);
                // Info title and info
                switch(infoState){
                    // Info screen
                    case 0:
                        HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                        break;
                    // Combat Screen
                    case 1:
                        HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                        break;
                    // Skills screen
                    case 2:
                        HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                        break;
                    // Items screen
                    case 3:
                        HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                        break;
                }
                break;

            // Load screen
            case LOAD:
           /*     drawCenteredText("Continue?", canvas, width / 2, height / 4, paint, 25, Color.WHITE);
                drawCenteredText("Stage " + GameManager.getStage(), canvas, width / 2, (int) (height * 0.83), paint, 20, Color.YELLOW);
                Bitmap portrait = null;
                // Set role text and portrait depending on role
                switch(Player.getRole()){
                    case MAGE:
                        portrait = Assets.getBitmapFromMemory("sprites_wizard");
                        break;
                    case WARRIOR:
                        portrait = Assets.getBitmapFromMemory("sprites_warrior");
                        break;
                    case TANK:
                        portrait = Assets.getBitmapFromMemory("sprites_tank");
                        break;
                }
                drawCenteredBitmap(portrait, canvas, paint, width / 2, height / 2);*/
                break;


            // Character Selection Screen
            case CHAR_SELECT:
                // Char select title
                HUDManager.displayTypingText("Choose a character...", largura / 2, altura / 2, 2, 11, Color.rgb(0,191,255), true);
                break;

            // Stage Transition Screen
            case STAGE_TRANSITION:
               /* // Stage transition title
                drawCenteredText("Stage " + GameManager.getStage(), canvas, width / 2, height / 4, paint, 25, Color.YELLOW);
                String role = "";
                Bitmap picture = null;
                // Set role text and portrait depending on role
                switch(Player.getRole()){
                    case MAGE:
                        role = "Mage";
                        picture = Assets.getBitmapFromMemory("sprites_wizard");
                        break;
                    case WARRIOR:
                        role = "Warrior";
                        picture = Assets.getBitmapFromMemory("sprites_warrior");
                        break;
                    case TANK:
                        role = "Tank";
                        picture = Assets.getBitmapFromMemory("sprites_tank");
                        break;
                }
                // Draw role name and portrait
                drawCenteredText(role, canvas, (int) (width * 0.3), (int) (height / 2.8), paint, 20, Color.WHITE);
                drawCenteredBitmap(picture, canvas, paint, (int) (width * 0.3), (int) (height * 0.6));

                // Draw gold count underneath
                drawCenteredText(Player.getGold() + " G", canvas, (int) (width * 0.3), (int) (height * 0.91), paint, 17, Color.YELLOW);

                String[] info = {
                        "HP: " + Player.getHP() + "/" + Player.getMaxHP(),
                        "MP: " + Player.getMana() + "/" + Player.getMaxMana(),
                        "AMR: " + Player.getArmor() + "/" + Player.getMaxArmor(),
                        "ATK: " + Player.getATK() + " (" + Player.getATKChance() + "%)",
                        "EVA: " + Player.getEvade() + "%"
                };

                double factor = 0.44;
                // Draw all player info
                for (int i = 0; i < 5; i++){
                    drawText(info[i], canvas, (int) (width * 0.5), (int) (height * factor), paint, 18, colors[i]);
                    factor += 0.09;
                }*/
                break;

            // Battle Screen
            case BATTLE:
/*
                Bitmap hpIcon = Assets.getBitmapFromMemory("icons_hp");
                Bitmap mpIcon = Assets.getBitmapFromMemory("icons_mana");
                Bitmap armorIcon = Assets.getBitmapFromMemory("icons_armor");
                Bitmap evadeIcon = Assets.getBitmapFromMemory("icons_evade");
                Bitmap swordsIcon = Assets.getBitmapFromMemory("icons_swords");

                String iconInfo[] = {
                        Player.getHP() + "/" + Player.getMaxHP(),
                        Player.getMana() + "/" + Player.getMaxMana(),
                        Player.getArmor() + "/" + Player.getMaxArmor(),
                        Player.getATK() + " (" + Player.getATKChance() + "%)",
                        Player.getEvade() + "%"
                };


                int[] iconColors = {Color.RED, Color.rgb(75, 75, 255), Color.CYAN, Color.rgb(255, 80, 0), Color.GREEN};

                // Draw icons
                drawCenteredBitmap(hpIcon, canvas, paint, (int) (width * 0.05), (int) (height * 0.08));
                drawCenteredBitmap(mpIcon, canvas, paint, (int) (width * 0.05), (int) (height * 0.17));
                drawCenteredBitmap(armorIcon, canvas, paint, (int) (width * 0.05), (int) (height * 0.27));
                drawCenteredBitmap(swordsIcon, canvas, paint, (int) (width * 0.05), (int) (height * 0.36));
                drawCenteredBitmap(evadeIcon, canvas, paint, (int) (width * 0.05), (int) (height * 0.45));

                // Draw stats
                double iconFactor = 0.098;
                for (int i = 0; i < 5; i++){
                    drawTextWithBorder(iconInfo[i], canvas, (int) (width * 0.1), (int) (height * iconFactor), paint, 12, iconColors[i]);
                    hudEndXPositions[i] = getTextElementEndPosition(iconInfo[i], canvas, paint, 18);
                    hudEndYPositions[i] = (int) (height * iconFactor);
                    iconFactor += 0.093;
                }

                // Draw current enemy HP
                Entity currEnemy = BattleManager.getCurrentEnemy();
                if (currEnemy != null){
                    drawCenteredTextWithBorder(currEnemy.getName() + ": " + currEnemy.getHP() + "/" + currEnemy.getMaxHP(), canvas, width / 2,
                            (int) (height * 0.08), paint, 12, Color.rgb(0,191,255));
                }else{
                    drawCenteredTextWithBorder("Waiting for enemy...", canvas, width / 2, (int) (height * 0.08), paint, 12, Color.rgb(0,191,255));
                }

                // Draw stage count and highest stage
                paint.setTextAlign(Paint.Align.RIGHT);
                drawTextWithBorder("Current: " + GameManager.getStage(), canvas, (int) (width * 0.97), (int) (height * 0.08), paint, 12, Color.YELLOW);
                drawTextWithBorder("High: " + GameManager.getHighStage(), canvas, (int) (width * 0.97), (int) (height * 0.17), paint, 12, Color.YELLOW);
                paint.setTextAlign(Paint.Align.LEFT);

                // If the player doesn't have a spell, draw the empty button
                if (Player.getCurrentSpell() == null){
                    Bitmap emptyButton = Assets.getBitmapFromMemory("button_empty");
                    drawCenteredBitmap(emptyButton, canvas, paint, (int) (width * 0.08), (int) (height * 0.64));
                }*/
                break;

            case INVENTORY:
                /*Bitmap invMenu = Assets.getBitmapFromMemory("menu_inventory_items");
                Bitmap invSelected = Assets.getBitmapFromMemory("menu_selected_item");

                // Draw inventory menu
                canvas.drawBitmap(invMenu, 0, 0, paint);

                // Inventory title
                drawCenteredText("Inventory", canvas, width / 2, (int) (height * 0.18), paint, 20, Color.WHITE);
                // Show info for the selected item
                if (GameManager.invSelection > 0 && GameManager.invSelection < 5){
                    int invSel = GameManager.invSelection - 1;
                    // Draw green highlight, and description
                    drawCenteredBitmap(invSelected, canvas, paint, (int) (width * (0.285 + (0.1435 * invSel))), (int) (height * 0.498));
                }*/
                break;
            case SHOP:

               /* Bitmap itemMenu = Assets.getBitmapFromMemory("menu_shop_items");
                Bitmap shopSelected = Assets.getBitmapFromMemory("menu_selected_item");

                // Draw shop menu
                canvas.drawBitmap(itemMenu, 0, 0, paint);
                drawCenteredText("Stats", canvas, (int) (width * 0.27), (int) (height * 0.17), paint, 18, Color.WHITE);
                int[] statColors = {Color.RED, Color.rgb(50, 50, 255), Color.CYAN, Color.rgb(255, 80, 0), Color.GREEN, Color.YELLOW};
                String playerInfo[] = {
                        Player.getHP() + "/" + Player.getMaxHP() + " HP",
                        Player.getMana() + "/" + Player.getMaxMana() + " MP",
                        Player.getArmor() + "/" + Player.getMaxArmor() + " AMR",
                        Player.getATK() + " (" + Player.getATKChance() + "%) ATK",
                        Player.getEvade() + "% EVA",
                        Player.getGold() + " G"
                };
                // Draw stats
                double shopFactor = 0.26;
                for (int i = 0; i < 6; i++){
                    drawCenteredTextWithBorder(playerInfo[i], canvas, (int) (width * 0.27), (int) (height * shopFactor), paint, 12, statColors[i]);
                    shopFactor += 0.08;
                }

                // Draw item costs for each shop item
                ArrayList<Item> spellItems = new ArrayList<>(GameManager.getShopSpellInventory().getItems());
                ArrayList<Item> consumableItems = new ArrayList<>(GameManager.getShopConsumableInventory().getItems());
                double sFactor = 0.613;
                for (Item item : spellItems){
                    drawCenteredText(item.getCost() + " G", canvas, (int) (width * sFactor), (int) (height * 0.32), paint, 13, Color.YELLOW);
                    sFactor += 0.144;
                }
                sFactor = 0.613;
                for (Item item : consumableItems){
                    drawCenteredText(item.getCost() + " G", canvas, (int) (width * sFactor), (int) (height * 0.64), paint, 13, Color.YELLOW);
                    sFactor += 0.144;
                }

                int shopSel = GameManager.shopSelection;

                // Show info for the selected item (spell)
                if (shopSel > 0 && shopSel < 4){

                    // Highlight selected item, and draw description
                    drawCenteredBitmap(shopSelected, canvas, paint, (int) (width * (0.469 + (0.1438 * shopSel))), height / 6);
                }

                // Show info for the selected item (consumable)
                else if (shopSel > 3 && shopSel < 7) {

                    // Highlight and draw description
                    drawCenteredBitmap(shopSelected, canvas, paint, (int) (width * (0.469 + (0.1438 * (shopSel - 3)))), (int) (height * 0.488));

                }*/
                break;


        }
        // Render all buttons + faded text
      //  buttonManager.render(canvas, paint);
       // entityManager.render(canvas, paint);
        animatedTextManager.render(canvas, paint);
    }

    private static int getTextElementEndPosition(String text, Canvas canvas, Paint paint, int textSize){
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);
        paint.setTextSize(scaledTextSize);
        Rect bounds = new Rect();

        paint.getTextBounds(text, 0, text.length(), bounds);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);

        int padding = largura / 15;
        return bounds.centerX() + (bounds.width() / 2) + padding;
    }

    /**
     * Draws text, centered to the position given.
     * @param text The text to draw
     * @param canvas The {@link Canvas} to draw on
     * @param x The x position of the text
     * @param y The y position of the text
     * @param paint The {@link Paint} object to draw with
     * @param textSize The size of the text
     * @param color The color of the text
     */
    public static void drawCenteredText(String text, Canvas canvas, int x, int y, Paint paint, int textSize, int color){
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);
        paint.setColor(color);
        paint.setTextSize(scaledTextSize);
        Rect bounds = new Rect();
        // Get bounds of the text, then center
        paint.getTextBounds(text, 0, text.length(), bounds);
        x -= bounds.width() / 2;
//        y -= bounds.height() / 2;
        canvas.drawText(text, x, y, paint);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }
    /**
     * Draws text, starting from the position given, from left to right.
     * @param text The text to draw
     * @param canvas The {@link Canvas} to draw on
     * @param x The x position of the text
     * @param y The y position of the text
     * @param paint The {@link Paint} object to draw with
     * @param textSize The size of the text
     * @param color The color of the text
     */
    public static void drawText(String text, Canvas canvas, int x, int y, Paint paint, int textSize, int color){
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);

        paint.setColor(color);
        paint.setTextSize(scaledTextSize);

        canvas.drawText(text, x, y, paint);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }
    /**
     * Draws a bitmap, centered to the position given.
     *
     * @param bitmap The {@link Bitmap} image to be drawn
     * @param canvas The {@link Canvas} object to draw to
     * @param paint The {@link Paint} object to draw with
     * @param x The x position of the bitmap
     * @param y The y position of the bitmap
     */
    public static void drawCenteredBitmap(Bitmap bitmap, Canvas canvas, Paint paint, int x, int y){
        x -= (bitmap.getWidth() / 2);
        y -= (bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    /**
     * Draws text, with a dark border.
     * @param text The text to draw
     * @param canvas The {@link Canvas} to draw on
     * @param x The x position of the text
     * @param y The y position of the text
     * @param paint The {@link Paint} object to draw with
     * @param textSize The size of the text
     * @param color The color of the text
     */
    public static void drawTextWithBorder(String text, Canvas canvas, int x, int y, Paint paint, int textSize, int color){
        paint.setColor(color);
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);
        paint.setTextSize(scaledTextSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(3, 0, 0, Color.BLACK);
        // Draw normal text
        canvas.drawText(text, x, y, paint);
        paint.setShadowLayer(0, 0, 0, Color.BLACK);
        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }

    /**
     * Draws text, centered to the position given, with a dark border.
     * @param text The text to draw
     * @param canvas The {@link Canvas} to draw on
     * @param x The x position of the text
     * @param y The y position of the text
     * @param paint The {@link Paint} object to draw with
     * @param textSize The size of the text
     * @param color The color of the text
     */
    public static void drawCenteredTextWithBorder(String text, Canvas canvas, int x, int y, Paint paint, int textSize, int color){
        paint.setColor(color);
        float old = paint.getTextSize();
        double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight()) / 250;
        float scaledTextSize = (float) (textSize * relation);
        paint.setTextSize(scaledTextSize);
        paint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();
        // Get bounds of the text, then center
        paint.getTextBounds(text, 0, text.length(), bounds);
        x -= bounds.width() / 2;
//        y -= bounds.height() / 2;

        // Draw normal text
        paint.setShadowLayer(3, 0, 0, Color.BLACK);
        canvas.drawText(text, x, y, paint);
        paint.setShadowLayer(0, 0, 0, Color.BLACK);
        // Draw black border

        paint.setTextSize(old);
        paint.setColor(Color.WHITE);
    }

    /**
     * Displays a message that fades in and out.
     * @param message The message to draw
     * @param x The x position of the message
     * @param y The y position of the message
     * @param ticks The time the message stays visible
     * @param textSize The size of the message
     * @param color The color of the message
     */
    public static void displayFadeMessage(String message, int x, int y, int ticks, int textSize, int color){
       // FadedText fade = new FadedText(message, ticks, x, y, textSize, color);
       // fade.play();
    }

    /**
     * Displays text that flies downward in an arch.
     * @param message The message to draw
     * @param x The initial x position of the message
     * @param y The initial y position of the message
     * @param ticks The time the message stays visible
     * @param textSize The size of the message
     * @param color The color of the message
     * @param directionVec The direction vector of the message
     */
    public static void displayParabolicText(String message, int x, int y, int ticks, int textSize, int color, double directionVec){
       // ParabolicText parabola = new ParabolicText(message, ticks, x, y, textSize, color, directionVec);
       // parabola.play();
    }
    /**
     * Displays text that flies downward in an arch.
     * @param message The message to draw
     * @param x The initial x position of the message
     * @param y The initial y position of the message
     * @param ticks The time the message stays visible
     * @param textSize The size of the message
     * @param color The color of the message
     * @param directionVec The direction vector of the message
     * @param initialA The initial a value of the parabola
     */
    public static void displayParabolicText(String message, int x, int y, int ticks, int textSize, int color, double directionVec, double initialA){
      //  ParabolicText parabola = new ParabolicText(message, ticks, x, y, textSize, color, directionVec, initialA);
     //   parabola.play();
    }

    //AVISO BUG DE EXCEPTION SE NO FINAL DA FRASE ECISTIR UM ESPAÇO 'trbalhar nesse bug'
    public static void displayTypingText(String message, int x, int y, int tickDelay, int textSize, int color, boolean centered){
        TypingText type = new TypingText(message, x, y, tickDelay, textSize, color, centered);
        type.play();
    }

    /**
     * Returns the speed of an object, given the distance to travel and amount of time taken.
     * Scales with screen size.
     * @param distance The distance to travel
     * @param ticksToReach The amount of time needed
     * @return The speed in pixels
     */
    public static double getSpeed(double distance, int ticksToReach){
        // Determine velocity (distance / time), should be used to determine the correct speeds
        // on different screen sizes
        return distance / (double) ticksToReach;
    }

    /**
     * Displays the particle effect.
     * @param y The y level of the upper particles.
     * @param y2 The y level of the lower particles.
     */
    public static void displayParticleEffect(int y, int y2, int color){
        double exclamationSpeedA = HUDManager.getSpeed(CoreManager.largura, 304);
        double exclamationSpeedB = HUDManager.getSpeed(CoreManager.largura, 274);
        displayParabolicText(".", (int) (largura * 0.25), y, 120, 15, color, -exclamationSpeedA);
        displayParabolicText(".", (int) (largura * 0.28), y2, 120, 15, color, -exclamationSpeedB);
        displayParabolicText(".", (int) (largura * 0.75), y, 120, 15, color, exclamationSpeedA);
        displayParabolicText(".", (int) (largura * 0.72), y2, 120, 15, color, exclamationSpeedB);
    }
}
