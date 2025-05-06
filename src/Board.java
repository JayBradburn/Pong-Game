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
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);

        ball = new Ball();
        Wall wall1 = new Wall(0, 470);
        Wall wall2 = new Wall(0, 100);
        Paddle paddle1 = new Paddle(0, (BOARD_HEIGHT / 2) + 55 - PADDLE_HEIGHT);
        Paddle paddle2 = new Paddle(630, (BOARD_HEIGHT / 2) + 55 - PADDLE_HEIGHT);
        sprites = new ArrayList<>(List.of(ball, wall1, wall2, paddle1, paddle2));

        activeKeyCodes = new HashSet<>();

        new Timer(TICK_DELAY, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Sprite sprite : sprites) {
            sprite.tick();
        }

        for(Sprite sprite : sprites) {
            if(ball.isColliding(sprite)) {
                ball.handleCollision(sprite);
            }
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

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
