import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.Constants.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final Ball ball;
    private final Wall wall1;
    private final Wall wall2;
    private final Paddle paddle1;
    private final Paddle paddle2;
    private final Barrier barrier1;
    private final Barrier barrier2;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);

        ball = new Ball();
        wall1 = new Wall(0, 470);
        wall2 = new Wall(0, 100);
        paddle1 = new Paddle(3, (BOARD_HEIGHT / 2) + 55 - BALL_HEIGHT);
        paddle2 = new Paddle(627, (BOARD_HEIGHT / 2) + 55 - BALL_HEIGHT);
        barrier1 = new Barrier(0, 110);
        barrier2 = new Barrier(637, 110);
        sprites = new ArrayList<>(List.of(ball, wall1, wall2, paddle1, paddle2, barrier1, barrier2));

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paddle1.handleActiveKeys("p1", activeKeyCodes);
        paddle2.handleActiveKeys("p2", activeKeyCodes);
        for(Sprite sprite : sprites) {
            sprite.tick();
        }



        if(ball.isColliding(paddle1)) {
            ball.handlePaddleCollision("p1");
        }
        if(ball.isColliding(paddle2)) {
            ball.handlePaddleCollision("p2");
        }
        if (ball.isColliding(wall1) || ball.isColliding(wall2)) {
            ball.handleWallCollision();
        }
        if (ball.isColliding(barrier1)) {
            // increase player 2 score
            ball.handleBarrierCollision();
        } else if (ball.isColliding(barrier2)) {
            // increase player 1 score
            ball.handleBarrierCollision();
        }

        if(paddle1.isColliding(wall1)) {
            paddle1.handleCollision(wall1);
        } else if (paddle1.isColliding(wall2)) {
            paddle1.handleCollision(wall2);
        }

        if(paddle2.isColliding(wall1)) {
            paddle2.handleCollision(wall1);
        } else if (paddle2.isColliding(wall2)) {
            paddle2.handleCollision(wall2);
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawString("Test text", 10, 10);

        for(Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        activeKeyCodes.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        activeKeyCodes.remove(keyEvent.getKeyCode());
    }
}
