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

        pos.x = Math.clamp(pos.x, 0, BOARD_WIDTH - BALL_WIDTH);
        pos.y = Math.clamp(pos.y, 0, BOARD_HEIGHT - BALL_HEIGHT);
    }
    public void handleActiveKeys(Set<Integer> activeKeyCodes) {
        dx = 0;
        dy = 0;

        if (activeKeyCodes.contains(KeyEvent.VK_UP)) {
            dy -= PADDLE_SPEED;
        }
        if (activeKeyCodes.contains(KeyEvent.VK_DOWN)) {
            dy += PADDLE_SPEED;
        }
    }
}

