package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//Imports are omitted
public class Score extends Pane {
    Label point;

    public Score(int x, int y) {
        point = new Label("0");
        setTranslateX(x);
        setTranslateY(y);
        point.setFont(Font.font("Verdana", FontWeight.BOLD,25));
        point.setTextFill(Color.web("#F5D547"));
        getChildren().addAll(point);
    }
    public void setPoint(int score) {
        this.point.setText(Integer.toString(score));
    }
}