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
import javafx.scene.paint.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.events.Event;

public class SunPath2 extends Application {

    final private static Circle sun = new Circle();


    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Pane window = new Pane();
        FlowPane flowPane = new FlowPane();
        Scene scene = new Scene(root,500,300);


        String paneStyle = "-fx-background-color:BLACK";

        String labelStyle = "-fx-font-weight: BOLD; " +
                "-fx-text-fill: MIDNIGHTBLUE; -fx-font-size: 16px;";


        RadioButton winterRadioButton = new RadioButton("Winter");
        RadioButton equinoxRadioButton = new RadioButton("Equinox");
        RadioButton summerRadioButton = new RadioButton("Summer");

        RadioButton[] radioButtons = new RadioButton[3];
        radioButtons[0] = winterRadioButton;
        radioButtons[1] = equinoxRadioButton;
        radioButtons[2] = summerRadioButton;

        Button button = new Button("Start");
        Label label = new Label("0:00.0");

        label.setStyle(labelStyle);
        window.setStyle(paneStyle);

        flowPane.setPadding(new Insets(5,10,5,100));
        flowPane.setHgap(20);

        // Ellipse e = new Ellipse(255,300,250,200);
        // e.setFill(Color.GREEN);

        //window.getChildren().add(e);

        Arc arc = new Arc();
        arc.setCenterX(250);
        arc.setCenterY(237);
        arc.setRadiusX(220);
        arc.setRadiusY(210);
        arc.setLength(-180);
        arc.setStartAngle(180);
        arc.setFill(Color.GREEN);
        arc.setVisible(true);

        window.getChildren().add(arc);

        sun.setFill(getLinearGradient(Color.RED, Color.YELLOW,Color.ORANGE));
        sun.setCenterY(237);
        sun.setCenterX(27);
        sun.setRadius(27);

        window.getChildren().add(sun);


        root.getChildren().add(window);
        root.getChildren().add(flowPane);


        flowPane.getChildren().addAll(winterRadioButton,equinoxRadioButton,summerRadioButton,button,label);

        PathTransition pt = new PathTransition();

        pt.setNode(sun);
        pt.setPath(arc);

        class RadioButtonHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {

                for(RadioButton button: radioButtons) {
                    if(event.getSource() != button) {
                        button.setSelected(false);
                    }
                    else {
                        button.setSelected(true);
                    }

                }
            }

        }

        for(RadioButton b: radioButtons) {
            b.setOnAction(new RadioButtonHandler());
        }


        // Lambda expression that deals with what happens when start button is pressed.
        button.setOnAction(event -> {
            if(winterRadioButton.isSelected()) {
                pt.setDuration(Duration.millis(1000));
                pt.play();
            }
            else if(equinoxRadioButton.isSelected()) {
                pt.setDuration(Duration.millis(3000));
                pt.play();
            }
            else if(summerRadioButton.isSelected()) {
                pt.setDuration(Duration.millis(6000));
                pt.play();
            }
            else {

            }
        });

        primaryStage.setTitle("Sun Path");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Helper method that returns a radial gradient.
     */
    private static RadialGradient getRadialGradient(Color c1, Color c2) {
        Stop stops[] = new Stop[2];
        stops[0] = new Stop(0, c1);
        stops[1] = new Stop(1, c2);
        return new RadialGradient(0, 0, 30, 255, 30, false, CycleMethod.REFLECT, stops);

    }

    /**
     * Helper method that returns a new linear gradient with two colors specified by the parameters.
     */
    private static LinearGradient getLinearGradient(Color c1, Color c2,Color c3) {
        Stop stops[] = new Stop[3];
        stops[0] = new Stop(0, c1);
        stops[1] = new Stop(1, c2);
        stops[2] = new Stop(2, c3);

        return new LinearGradient(0,0,1,1,true,CycleMethod.REPEAT,stops);
    }



    public static void main(String[] args) {
        launch(args);
        System.out.println(sun.getCenterX());
    }
}
