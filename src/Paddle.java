import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

import static utils.Constants.*;

public class Paddle extends Sprite {
    private double dx;
    private double dy;
    public Paddle(int x, int y) {
        super(PADDLE_IMAGE_PATH, x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void tick() {
        pos.translate((int)dx, (int)dy);

    }
    public void handleActiveKeys(String paddle, Set<Integer> activeKeyCodes) {
        dx = 0;
        dy = 0;
        if (paddle.equals("p1")) {
            if (activeKeyCodes.contains(KeyEvent.VK_W)) {
                dy -= PADDLE_SPEED;
            }
            if (activeKeyCodes.contains(KeyEvent.VK_S)) {
                dy += PADDLE_SPEED;
            }
        } else if (paddle.equals("p2")) {
            if (activeKeyCodes.contains(KeyEvent.VK_UP)) {
                dy -= PADDLE_SPEED;
            }
            if (activeKeyCodes.contains(KeyEvent.VK_DOWN)) {
                dy += PADDLE_SPEED;
            }
        }
    }
    public void handleCollision(Sprite other) {
        if(other.getClass().equals(Wall.class)) {
            Point previousPos = new Point(pos.x - (int)dx, pos.y - (int)dy);

            if(dx > 0 && previousPos.x + size.width <= other.getTopLeft().x) {
                pos.x = other.getTopLeft().x - size.width;
            }
            else if(dx < 0 && previousPos.x >= other.getBottomRight().x) {
                pos.x = other.getBottomRight().x;
            }

            if(dy > 0 && previousPos.y + size.height <= other.getTopLeft().y) {
                pos.y = other.getTopLeft().y - size.height;
            }
            else if(dy < 0 && previousPos.y >= other.getBottomRight().y) {
                pos.y = other.getBottomRight().y;
            }
        }
    }
}

