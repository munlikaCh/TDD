package model;

import javafx.geometry.Point2D;
import view.Platform;

import java.util.Random;

public class Food {
    private Point2D position;
    private Random rn;

    //food2
    private Point2D position2;
    private Random rn2;

    public Food(Point2D position){
        this.rn = new Random();
        this.position = position;

        //f2
        this.rn2 = new Random();
        this.position2 = position2;
    }

    public Food(){
        this.rn = new Random();
        respawn();

        //f2
        this.rn2 = new Random();
        respawn2();
    }

    public void respawn() {
        Point2D prev_position = this.position;
        do{
            this.position = new Point2D(rn.nextInt(Platform.WIDTH),
                    rn.nextInt(Platform.HEIGHT));
        }
        while(prev_position == this.position);
    }

    //f2
    public void respawn2() {
        Point2D prev_position2 = this.position2;
        do{
            this.position2 = new Point2D(rn2.nextInt(Platform.WIDTH),
                    rn2.nextInt(Platform.HEIGHT));
        }
        while(prev_position2 == this.position2);
    }

    public Point2D getPosition() {
        return position;
    }

    //f2
    public Point2D getPosition2() {
        return position2;
    }
}
