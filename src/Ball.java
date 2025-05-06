import java.awt.*;
import java.util.Random;
import static utils.Constants.*;

public class Ball extends Sprite {
    private double vx;
    private double vy;

    public Ball() {
        super(BALL_IMAGE_PATH, 0, 0, BALL_WIDTH, BALL_HEIGHT);
        reset();
    }

    public void reset() {
        // Move the ball to the center of the screen
        // Look at how this is done to Wall in Board.java for inspiration
        pos.x = (BOARD_WIDTH / 2) - BALL_WIDTH;
        pos.y = (BOARD_HEIGHT / 2) + 55 - BALL_HEIGHT;
        vx = BALL_SPEED;
        vy = BALL_SPEED;
        int randomNum = (int)(Math.random() * 4);
        if (randomNum == 0) {
            vx = vx * 1;
            vy = vy * 1;
        } else if (randomNum == 1) {
            vx = vx * -1;
            vy = vy * 1;
        } else if (randomNum == 2) {
            vx = vx * 1;
            vy = vy * -1;
        } else {
            vx = vx * -1;
            vy = vy * -1;
        }
        // Reset the ball's velociy
        // It should randomly move up left, up right, down left, or down right
        // At first, make it move in one direction and add randomness later
    }

    @Override
    public void tick() {
        // Move the ball based on vx and vy
        // Look at Player.java for inspiration
        pos.translate((int)vx, (int)vy);

        pos.x = Math.clamp(pos.x, 0, BOARD_WIDTH - BALL_WIDTH);
        pos.y = Math.clamp(pos.y, 0, BOARD_HEIGHT - BALL_HEIGHT);
    }
    public void handleCollision(Sprite other) {
        if(other.getClass().equals(Wall.class)) {
            Point previousPos = new Point(pos.x - (int)vx, pos.y - (int)vy);

            if(vx > 0 && previousPos.x + size.width <= other.getTopLeft().x) {
                pos.x = other.getTopLeft().x - size.width;
            }
            else if(vx < 0 && previousPos.x >= other.getBottomRight().x) {
                pos.x = other.getBottomRight().x;
            }

            if(vy > 0 && previousPos.y + size.height <= other.getTopLeft().y) {
                pos.y = other.getTopLeft().y - size.height;
            }
            else if(vy < 0 && previousPos.y >= other.getBottomRight().y) {
                pos.y = other.getBottomRight().y;
            }
        }
    }
}