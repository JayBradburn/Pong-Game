package utils;

public final class Constants {
    private Constants() {
        // prevents instantiation
    }

    // Board constants
    public static final int BOARD_WIDTH = 640;
    public static final int BOARD_HEIGHT = 480;
    // A delay of 25 milliseconds results in a frame rate of 45 FPS.
    public static final int TICK_DELAY = 25;

    // Ball constants
    public static final String BALL_IMAGE_PATH = "resources/Pong-Ball.jpg";
    public static final int BALL_WIDTH = 50;
    public static final int BALL_HEIGHT = 50;
    public static final int BALL_SPEED = 3;

    // Paddle constants
    public static final String PADDLE_IMAGE_PATH = "resources/Paddle.PNG";
    public static final int PADDLE_WIDTH = 10;
    public static final int PADDLE_HEIGHT = 100;
    public static final int PADDLE_SPEED = 3;

    // Wall constants
    public static final String WALL_IMAGE_PATH = "resources/wall.PNG";
    public static final int WALL_WIDTH = 640;
    public static final int WALL_HEIGHT = 10;
}
