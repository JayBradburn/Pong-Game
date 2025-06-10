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
    private int p1Score;
    private int p2Score;
    private int rally;
    private final Ball ball;
    private final Wall leftWall;
    private final Wall rightWall;
    private final Paddle paddle1;
    private final Paddle paddle2;
    private final Barrier barrier1;
    private final Barrier barrier2;
    private final List<Sprite> sprites;
    private final Set<Integer> activeKeyCodes;
    private List<Integer> rallies = new ArrayList<Integer>();

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        p1Score = 0;
        p2Score = 0;
        rally = 0;
        rallies.add(0);
        ball = new Ball();
        leftWall = new Wall(0, 470);
        rightWall = new Wall(0, 100);
        paddle1 = new Paddle(3, (BOARD_HEIGHT / 2) + 55 - BALL_HEIGHT);
        paddle2 = new Paddle(627, (BOARD_HEIGHT / 2) + 55 - BALL_HEIGHT);
        barrier1 = new Barrier(0, 110);
        barrier2 = new Barrier(637, 110);
        sprites = new ArrayList<>(List.of(ball,leftWall, rightWall, paddle1, paddle2, barrier1, barrier2));

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
            rally = rally + 1;
            ball.handlePaddleCollision("p1");
        }
        if(ball.isColliding(paddle2)) {
            rally = rally + 1;
            ball.handlePaddleCollision("p2");
        }
        if (ball.isColliding(leftWall) || ball.isColliding(rightWall)) {
            ball.handleWallCollision();
        }
        if (ball.isColliding(barrier1)) {
            p2Score = p2Score + 1;
            rallies.add(rally);
            rallies.sort( (a, b) -> { return -1 * a.compareTo(b); } );
            rally = 0;
            ball.handleBarrierCollision();
            if (p2Score == 11){
                ball.stopGame();
            }
        } else if (ball.isColliding(barrier2)) {
            p1Score = p1Score + 1;
            rallies.add(rally);
            rallies.sort( (a, b) -> { return -1 * a.compareTo(b); } );
            rally = 0;
            ball.handleBarrierCollision();
            if (p1Score == 11){
                ball.stopGame();
            }
        }

        if(paddle1.isColliding(leftWall)) {
            paddle1.handleCollision(leftWall);
        } else if (paddle1.isColliding(rightWall)) {
            paddle1.handleCollision(rightWall);
        }

        if(paddle2.isColliding(leftWall)) {
            paddle2.handleCollision(leftWall);
        } else if (paddle2.isColliding(rightWall)) {
            paddle2.handleCollision(rightWall);
        }

        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 24));
        graphics.setColor(Color.WHITE);
        graphics.drawString("P1", 100, 25);
        graphics.drawString("P2",515 , 25);
        graphics.drawString("Rally", 285, 25);
        graphics.drawString("Top Rally: " + rallies.getFirst(), 245, 90);
        graphics.drawString(String.valueOf(p1Score), 105, 75);
        graphics.drawString(String.valueOf(p2Score),520 , 75);
        graphics.drawString(String.valueOf(rally), 305, 60);
        if (p1Score == 11){
            graphics.drawString(("Player one wins!"), 215, 200);
        }
        if (p2Score == 11){
            graphics.drawString(("Player two wins!"), 215, 200);
        }
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
