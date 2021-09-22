package controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import model.Direction;
import model.Food;
import model.Snake;
import view.Platform;
import view.Score;


import javax.swing.border.LineBorder;
import java.util.ArrayList;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;

    public GameLoop(Platform platform, Snake snake, Food food) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        running = true;
    }

    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();

        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);

        snake.update();
    }
    //update sc
    private void updateScore(ArrayList<Score> scoreList) {
        javafx.application.Platform.runLater(() -> {
            for (int i = 0; i < scoreList.size(); i++) {
                scoreList.get(i).setPoint(snake.getScore());
            }
        });
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
        }
        //f2
        if (snake.isCollidingWith2(food)) {
            snake.grow2();
            food.respawn2();
        }
        if (snake.isDead()) {
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake,food);
    }

    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();

            //up sc
            updateScore(platform.getScoreList());

            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Game Over
        javafx.application.Platform.runLater(() -> {
            Popup popup = new Popup(); //pop up
            Label label = new Label("Game Over");

            //css
            label.setFont(Font.font("Verdana", FontWeight.BOLD,15));
            label.setTextFill(Color.web("#EF3E36"));
            label.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 2;" +
                    "-fx-background-color: black;");

            popup.getContent().addAll(label);
            popup.show(Launcher.snakeStage);
        });
    }

    //add
    public Platform getPlatform() {
        return platform;
    }

    public Snake getSnake() {
        return snake;
    }
}