import java.awt.*;
import javax.swing.*;
import static utils.Constants.*;

public class Barrier extends Sprite {
    public Barrier(int x, int y) {
        super(BARRIER_IMAGE_PATH, x, y, BARRIER_WIDTH, BARRIER_HEIGHT);
    }

    @Override
    public void tick() {
        // Unused
    }
}

