
import java.util.*;
import java.io.*;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

import javafx.geometry.HPos;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseButton;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.KeyCode;

import javafx.geometry.Pos;

import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ScrollBar;

public class DinoGame extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	int countHod = 0 ;
	int figur = 0 ;
	int countUP = 0 ;
	boolean papal = false ;

	BorderPane mPane = new BorderPane();
	int x = 0 ;
	int y = 300 ;
	Rectangle r1 = new Rectangle(x, y, 50, 50);
	Circle c1 = new Circle(x, y + 25, 25);

	/*Rectangle body = new Rectangle(225, 300, 10, 50);*/
	Timeline ups = new Timeline(new KeyFrame(Duration.millis(15), up -> upRUN() ));
	Circle c2 = new Circle(110, 300 + 25, 5);
	Text score  = new Text("score: 0");
	int scoreINT = 0 ;

	Image dStr = new Image("DinoGameIMG/DinoStart1.png");
	Image d1 = new Image("DinoGameIMG/dino1.png");
	/*Image d2 = new Image("DinoGameIMG/dino2.png");*/
	Image d3 = new Image("DinoGameIMG/dino3.png");
	Image d4 = new Image("DinoGameIMG/dino4.png");
	/*Image d5 = new Image("DinoGameIMG/dino5.png");
	Image d6 = new Image("DinoGameIMG/dino6.png");*/

	ImageView body = new ImageView(dStr);
	int countDinoAct = 0 ;
	Timeline dinoAction = new Timeline(new KeyFrame(Duration.millis(70), act -> dinoAct() ));

	@Override
	public void start(Stage primaryStage) {

		r1.setFill(Color.BLACK);
		/*r1.setFill(Color.BLACK);*/
		BorderPane sc = new BorderPane();
		Line l = new Line(0, 350, 1200, 350);
		score.setFill(Color.BLACK);
		score.setFont(Font.font("Sans", FontWeight.BOLD, 30));
		sc.setRight(score);

		HBox p = new HBox(20);
		Button st = new Button("START");
		Button cl = new Button("CLEAR");
		p.getChildren().addAll(st, cl);
		p.setAlignment(Pos.CENTER);

		mPane.setBottom(p);

		mPane.setTop(sc);
		mPane.setMargin(sc, new Insets(10, 20, 0, 0));

		r1.setX(800 + 100);
		r1.setY(300);
		c1.setCenterX(800 + 100);
		c1.setCenterY(300 + 25);

		mPane.getChildren().add(r1);
		mPane.getChildren().add(c1);
		mPane.getChildren().add(l);
		body.setX(225);
		body.setY(268);

		mPane.getChildren().add(body);

		c2.setFill(Color.BLACK);
		mPane.getChildren().add(c2);

		Timeline q = new Timeline(new KeyFrame(Duration.millis(15), e -> move() ));
		q.setCycleCount(Timeline.INDEFINITE);

		
		ups.setCycleCount(Timeline.INDEFINITE);
		dinoAction.setCycleCount(Timeline.INDEFINITE);
		
		st.setOnAction(r -> {
			q.play();
			dinoAction.play();
		});
		cl.setOnAction(e -> {
			q.stop();
			papal = false ;
		});

		mPane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				ups.play();
			}
			/*else if (e.getCode() == KeyCode.D) {
				
			}*/
		});

		
		primaryStage.setTitle("DinoGame");
		primaryStage.setScene(new Scene(mPane, 800, 600));
		primaryStage.show();
		st.requestFocus();
		
	}

	public void dinoAct() {
		countDinoAct++;
		switch(countDinoAct) {
			case 1 : body.setImage(d1); break ;
			case 2 : body.setImage(d3); break ;
			case 3 : body.setImage(d4); countDinoAct = 0; break ;
		}
	}

	public void upRUN() {

		countUP++;
		if (countUP < 21) {
			body.setY(body.getY() - 9);
		} 
		else if (countUP > 20 && countUP <= 40) {
			body.setY(body.getY() + 9);
		} else {
			countUP = 0 ;
			ups.stop();
		}


		if (figur == 0) {
			if (r1.contains(225, body.getY() + 88) && r1.contains(319, body.getY() + 88)) {
				papal = true ;
			} 
			else if (countUP == 40 && papal == false && (r1.contains(95, 300 + 25) || r1.contains(110, 300 + 25) || r1.contains(118, 300 + 25) || r1.contains(125, 300 + 25) || r1.contains(135, 300 + 25) || r1.contains(143, 300 + 25) || r1.contains(147, 300 + 25) || r1.contains(150, 300 + 25) || r1.contains(153, 300 + 25) || r1.contains(160, 300 + 25) || r1.contains(156, 300 + 25) || r1.contains(170, 300 + 25) || r1.contains(180, 300 + 25) || r1.contains(190, 300 + 25)) ) {
				System.out.println("AAS");
				scoreINT = scoreINT + 1 ;
				score.setText("score: " + scoreINT);
			}
		} 
		else if (figur == 1) {
			if (c1.contains(225, body.getY() + 88) && c1.contains(319, body.getY() + 88)) {
				papal = true ;
			} 
			else if (countUP == 40 && papal == false && (c1.contains(95, 300 + 25) || c1.contains(110, 300 + 25) || c1.contains(118, 300 + 25) || c1.contains(125, 300 + 25) || c1.contains(135, 300 + 25) || c1.contains(143, 300 + 25) || c1.contains(147, 300 + 25) || c1.contains(150, 300 + 25) || c1.contains(153, 300 + 25) || c1.contains(160, 300 + 25) || c1.contains(156, 300 + 25) || c1.contains(170, 300 + 25) || c1.contains(180, 300 + 25) || c1.contains(190, 300 + 25)) ) {
				scoreINT = scoreINT + 1 ;
				score.setText("score: " + scoreINT);
			}
		}
	}

	public void move() {

		if (figur == 0) {
			if (r1.contains(225, body.getY() + 44) || r1.contains(319, body.getY() + 44)) {
				System.out.println("PAPAL");
			}	
		} 
		else if (figur == 1) {
			if (c1.contains(225, body.getY() + 44) || c1.contains(319, body.getY() + 44)) {
				System.out.println("PAPAL");
			}
		}

		countHod++;
		if (countHod == 1) {
			figur = (int)(Math.random()*2);
		}
		if (figur == 0 && countHod != 200) {
			r1.setX(r1.getX() - 5);
			r1.setY(y);
		}
		else if (figur == 1 && countHod != 200) {
			c1.setCenterX(c1.getCenterX() - 5);
			c1.setCenterY(y + 25);
		}
		else if (figur == 0 && countHod == 200) {
			r1.setX(800 + 100);
			r1.setY(y);
			countHod = 0 ;
		} 
		else if (figur == 1 && countHod == 200) {
			c1.setCenterX(800 + 100);
			c1.setCenterY(y + 25);
			countHod = 0 ;
		} 
		
 	}

}