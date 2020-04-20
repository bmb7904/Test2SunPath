package bernardi.test2.sunpath;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.security.provider.Sun;

/**
 * A javafx program that will show the sun moving across a black sky as described below.
 * I affirm that I did not communicate with anyone during this exam. It's probably quite
 * obvious anyway, considering how poorly I did. This is not my finest work. I ran out
 * of time and scrambled.
 *
 * @author Brett Bernardi
 */


public class SunPath extends Application {

    final private static Circle sun = new Circle(25);
    private static int rbSelected ;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(5);
        root.setPrefWidth(500);
        root.setPrefHeight(300);
        Scene scene = new Scene(root);




        RadioButton radioButton[] = new RadioButton[3];
        String style = "-fx-background-color:BLACK;";
        root.setStyle(style);

        Pane pane = new Pane();


       // Rectangle background = new Rectangle(500,300);
       // pane.getChildren().add(background);

        String labelStyle = "-fx-font-weight: BOLD; " + "-fx-font-weight: BOLD; " +
                "-fx-text-fill: MIDNIGHTBLUE; -fx-font-size: 16px;";


        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(0,20,1,20));
        flowPane.setHgap(20);

        RadioButton winter = new RadioButton("Winter");
        RadioButton equinox = new RadioButton("Equinox");
        RadioButton summer = new RadioButton("Summer");
        Button button = new Button("Start");
        Label label = new Label("00:00.0");
        label.setStyle(labelStyle);

        flowPane.getChildren().addAll(winter,equinox,summer,button,label);

        Arc arc = new Arc();

        arc.setCenterX(200);
        arc.setCenterY(200);
        arc.setRadiusX(290);
        arc.setRadiusY(300);
        arc.setStartAngle(180);
        arc.setLength(-200);
        arc.setVisible(false);
        pane.getChildren().add(arc);


        root.getChildren().addAll(pane,flowPane);
        sun.setFill(getRadialGradient(Color.ORANGE, Color.RED));
        root.getChildren().add(sun);

        PathTransition pt = new PathTransition();

        pt.setNode(sun);
        pt.setPath(arc);



        // Lambda expression
        button.setOnAction(event -> {
            pt.play();


            System.out.println("Hello");
        });


        class RadioButtonHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                if(event.getSource() == summer) {
                    pt.setDuration(Duration.millis(6000));
                }
                else if(event.getSource() == winter) {
                    pt.setDuration(Duration.millis(1000));
                }
                else {
                    pt.setDuration(Duration.millis(3000));
                }
            }
        }
        summer.setOnAction(new RadioButtonHandler());
        equinox.setOnAction(new RadioButtonHandler());
        winter.setOnAction(new RadioButtonHandler());







        primaryStage.setTitle("Sun Path");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();



    }

    /**
     * Helper Method that returns a radial gradient
     */
    private static RadialGradient getRadialGradient(Color color1, Color color2) {
        Stop[] stops = new Stop[2];
        stops[0] = new Stop(0, color1);
        stops[1] = new Stop(1, color2);;

        return new RadialGradient(90, 150, sun.getCenterX(), sun.getCenterY(),
                sun.getRadius(),
                false,
                CycleMethod.NO_CYCLE,
                stops);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
