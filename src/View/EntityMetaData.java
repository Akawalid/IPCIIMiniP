package View;


import Model.Direction;
import Model.Farm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


/**
 * This class is responsible for loading and providing access to various entity images used in the game.
 * It includes methods to retrieve specific images based on their type and direction.
 */
public class EntityMetaData {
    public static int LLAMA_SHADOW = 0,
        LLAMA_EAT = 1,
        SHEEP_EAT = 2,
        COW_SHADOW = 3,
        COW_EAT = 4,
        HEN_EAT = 5,
        HEN_SHADOW = 6,
    WOLF = 7,

    FOX = 8;
    private static Random random = new Random();

    private static BufferedImage sheepEat;
    private static BufferedImage llamaShadow;
    private static BufferedImage llamaEat;

    private static BufferedImage cowShadow;
    private static BufferedImage cowEat;
    private static BufferedImage henShadow;
    private static BufferedImage henEat;
    private static BufferedImage wolfImage;
    private static BufferedImage foxImage;

    //private static HashMap<Integer, BufferedImage> cacheMemory;
    static {
        uploadAssets();

    }
    private static void uploadAssets(){
        //cacheMemory = new HashMap<>();
        try {
            sheepEat = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/sheep_eat.png"))
            );
            llamaShadow = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/llama_shadow.png"))
            );
            llamaEat = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/llama_eat.png"))
            );
            cowShadow = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/cow_shadow.png"))
            );
            cowEat = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/cow_eat.png"))
            );
            henEat = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/chicken_eat.png"))
            );
            henShadow = ImageIO.read(
                    Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/chicken_shadow.png"))
            );
            // Charger l'image du loup
            wolfImage = ImageIO.read(Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/wolf.png")));
            wolfImage = toBufferedImage(
                    wolfImage.getScaledInstance(12, 12, Image.SCALE_SMOOTH)
            );
            // Charger l'image du renard
            foxImage = ImageIO.read(Objects.requireNonNull(EntityMetaData.class.getResource("/Assets/images/Animals/fox.png")));
            foxImage = toBufferedImage(
                    foxImage.getScaledInstance(12, 12, Image.SCALE_SMOOTH)
            );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
    private static BufferedImage getPortion(BufferedImage im, Direction dir, int idx, int shadow) {
//        // Get sprite sheet dimensions
//        if(cacheMemory.containsKey(100 * idx + 10 * dir.ordinal() + shadow)){
//            return cacheMemory.get(100 * idx + 10 * dir.ordinal() + shadow);
//        }
        int sheetWidth = im.getWidth();
        int sheetHeight = im.getHeight();

        // Calculate number of animation frames per row
        int framesPerRow;
        if(shadow == 1) framesPerRow = 1;
        else framesPerRow = 4;
        int tileWidth = sheetWidth / framesPerRow;

        // Calculate number of directions (rows)
        int directionCount = Direction.values().length;
        int tileHeight = sheetHeight / directionCount;

        // Calculate coordinates based on direction and frame index
        int x = (idx % framesPerRow) * tileWidth;
        int y = dir.ordinal() * tileHeight;

        // Handle edge cases
        x = Math.min(x, sheetWidth - tileWidth);
        y = Math.min(y, sheetHeight - tileHeight);
//        cacheMemory.put(100 * idx + 10 * dir.ordinal() + shadow,  im.getSubimage(x, y, tileWidth, tileHeight));
        return im.getSubimage(x, y, tileWidth, tileHeight);
    }
    public static BufferedImage getAsset(int which, Direction dir, int idx) {
        if(which == LLAMA_EAT)
            return getPortion(llamaEat, dir, idx, 9);
        if(which == LLAMA_SHADOW)
            return getPortion(llamaShadow, dir, idx, 1);
        if(which == SHEEP_EAT)
            return getPortion(sheepEat, dir, idx, 0);
        if(which == COW_EAT)
            return getPortion(cowEat, dir, idx, 0);
        if(which == COW_SHADOW)
            return getPortion(cowShadow, dir, idx, 0);
        if(which == HEN_SHADOW)
            return getPortion(henShadow, dir, idx, 0);
        if(which == HEN_EAT)
            return getPortion(henEat, dir, idx, 0);
        return null;
    }

    /**
     * M�thode pour r�cup�rer l'image du loup.
     *
     */
    public static BufferedImage getWolfAsset() {
        return wolfImage;
    }

    public static BufferedImage getFoxAsset() {
        return foxImage;
    }

    /**
     * Converts a generic Image to a BufferedImage.
     * This is useful for scaling and processing images.
     *
     * @param img the Image to be converted.
     * @return the corresponding BufferedImage.
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

}