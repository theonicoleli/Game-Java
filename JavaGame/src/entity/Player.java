package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = loadImage("/res/player/boy_up_1.png");
            up2 = loadImage("/res/player/boy_up_2.png");
            down1 = loadImage("/res/player/boy_down_1.png");
            down2 = loadImage("/res/player/boy_down_2.png");
            left1 = loadImage("/res/player/boy_left_1.png");
            left2 = loadImage("/res/player/boy_left_2.png");
            right1 = loadImage("/res/player/boy_right_1.png");
            right2 = loadImage("/res/player/boy_right_2.png");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage loadImage(String path) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(path);
        if (inputStream != null) {
            return ImageIO.read(inputStream);
        } else {
            throw new IOException("Imagem não encontrada: " + path);
        }
    }


    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction) {
            case "up":
                switch (spriteNum){
                    case 1:
                        image = up1;
                        break;
                    case 2:
                        image = up2;
                        break;
                }
                break;
            case "down":
                switch (spriteNum){
                    case 1:
                        image = down1;
                        break;
                    case 2:
                        image = down2;
                        break;
                }
                break;
            case "left":
                switch (spriteNum){
                    case 1:
                        image = left1;
                        break;
                    case 2:
                        image = left2;
                        break;
                }
                break;
            case "right":
                switch (spriteNum){
                    case 1:
                        image = right1;
                        break;
                    case 2:
                        image = right2;
                        break;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
