package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import automate.Automate;
import info3.game.Camera;

public abstract class Entity {
    public int x;
    public int y;

    int width;
    int height;

    long moveElapsed;

    public int imageIndex;

    Automate automate;
    Hitbox hitbox;

    public BufferedImage[] m_images;

    public Entity(int x, int y, Automate automate, String filename, int nrows, int ncols) throws IOException {
        this.x = x;
        this.y = y;

        this.m_images = loadSprite(filename, nrows, ncols);
        this.width = m_images[0].getWidth();
        this.height = m_images[0].getHeight();
        this.automate = automate;
    }

    public abstract void tick(long elapsed);

    public abstract void paint(Graphics g);

    public void camPaint(Graphics g) {
        BufferedImage img = m_images[imageIndex];
        Camera.drawImage(g, img, x, y, width, height);
        // OR
        // Camera.drawEntity(this, g); (same result)
    }

    public abstract void move(String direction);

    public abstract void wizz();

    public abstract void pop();

    public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
        File imageFile = new File(filename);
        if (imageFile.exists()) {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth(null) / ncols;
            int height = image.getHeight(null) / nrows;

            BufferedImage[] images = new BufferedImage[nrows * ncols];
            for (int i = 0; i < nrows; i++) {
                for (int j = 0; j < ncols; j++) {
                    int x = j * width;
                    int y = i * height;
                    images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
                }
            }
            return images;
        }
        return null;
    }

}
