package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Food;
import model.Snake;

import java.util.ArrayList;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;

    //sc list
    private ArrayList<Score> scoreList;

    public Platform(){
        scoreList = new ArrayList(); //add sc list

        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        scoreList.add(new Score(10, 10)); //add : to show sc
        getChildren().addAll(scoreList);//add
        this.getChildren().add(canvas);
    }

    public void render(Snake snake, Food food){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE,
                    TILE_SIZE);
        });
        gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY()
                * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        //f2
        gc.setFill(Color.GREEN);
        gc.fillRect(food.getPosition2().getX() * TILE_SIZE, food.getPosition2().getY()
                * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    public KeyCode getKey() { return key;}
    public void setKey(KeyCode key) { this.key = key; }

    //add sc list
    public ArrayList<Score> getScoreList() {
        return scoreList;
    }
}
