package model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Snake {
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

    public void grow() {
        body.add(prev_tail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

}
