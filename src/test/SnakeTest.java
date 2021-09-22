package test;

import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import javafx.geometry.Point2D;
import model.Direction;
import model.Food;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.*;

//Imports are omitted
@RunWith(JfxRunner.class)

public class SnakeTest {
    private Snake snake;

    @Before
    public void setup() {
        snake = new Snake(new Point2D(0, 0));
    }

    @Test
    public void snakeShouldBeSpawnAtTheCoordinateItWasCreated() {
        assertEquals(snake.getHead(), new Point2D(0, 0));
    }

    @Test
    public void snakeShouldMoveDownwardIfItIsHeadingDownward() {
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        assertEquals(snake.getHead(), new Point2D(0, 1));
    }

}
