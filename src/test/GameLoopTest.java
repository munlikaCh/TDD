package test;

import controller.GameLoop;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import model.Food;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update;
    private Method collision;
    private Method redraw;

    @Before
    public void init() throws NoSuchMethodException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food());
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);
    }

    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
    }

    @Test
    public void testClockTick() throws InvocationTargetException, IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)), new Food());
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
    }

    @Test
    public void testNoBack() throws InvocationTargetException, IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)), new Food());

        //DOWN
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));

        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,2));

        //UP
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,3));
    }
}
