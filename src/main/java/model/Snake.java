package model;

import javafx.geometry.Point2D;
import view.Platform;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    //score
    private int score=0;

    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private ArrayList<Point2D> body;

    public Snake(Point2D position){
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }

    public void update() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public void setCurrentDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getCurrentDirection() {
        return this.direction;
    }

    public Point2D getHead() {
        return head;
    }

    //5.5.2
    public boolean isCollidingWith(Food food) {
        return head.equals(food.getPosition());
    }

    //f2
    public boolean isCollidingWith2(Food food) {
        return head.equals(food.getPosition2());
    }

    public void grow() {
        score++;
        body.add(prev_tail);
    }

    //f2
    public void grow2() {
        score = score + 5 ; //5 sc
        body.add(prev_tail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

    //5.5.3
    public boolean isDead(){
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() >
                Platform.WIDTH || head.getY() > Platform.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }

    //get score
    public int getScore() {
        return score;
    }

}
