import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import java.awt.*;

public class Main extends Application {


    Stage stage;
    Scene scene;
    VBox layout;
    Label textField;
    Button clear, negate, percent, divide;
    Button seven, eight, nine, multiply;
    Button four, five, six, subtract;
    Button one, two, three, add;
    Button zero, decimal, equals;
    float num = 0;
    String numString = "0";
    String operator;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        numString = String.valueOf(num);

        //First row: input
        textField = new Label("0");
        textField.setPadding(new Insets(0, 10, 5, 5));
        textField.setAlignment(Pos.BASELINE_RIGHT);
        textField.setFont(new Font(50));
        textField.setPrefSize(240, 60);

        HBox hBoxOne = new HBox(textField);


        //Second row
        clear = new Button("AC");
        clear.setOnAction(new EventHandler<ActionEvent>(){      //clear button
            @Override
            public void handle(ActionEvent event) {
                num = 0;
                numString = "0";
                textField.setText(numString);
            }
        });
        negate = new Button("+/-");
        percent = new Button("%");
        divide = new Button("÷");
        clear.setPrefSize(60, 50);
        percent.setPrefSize(60, 50);
        negate.setPrefSize(60, 50);
        divide.setPrefSize(60, 50);
        HBox hBoxTwo = new HBox(clear, negate, percent, divide);

        //Third row
        seven = new Button("7");
        eight = new Button("8");
        nine = new Button("9");
        multiply = new Button("X");
        seven.setPrefSize(60, 50);
        eight.setPrefSize(60, 50);
        nine.setPrefSize(60, 50);
        multiply.setPrefSize(60, 50);
        HBox hBoxThree = new HBox(seven, eight, nine, multiply);

        //Fourth row
        four = new Button("4");
        five = new Button("5");
        six = new Button("6");
        subtract = new Button("-");
        four.setPrefSize(60, 50);
        five.setPrefSize(60, 50);
        six.setPrefSize(60, 50);
        subtract.setPrefSize(60, 50);
        HBox hBoxFour = new HBox(four, five, six, subtract);

        //Fifth row
        one = new Button("1");
        two = new Button("2");
        three = new Button("3");
        add = new Button("+");
        one.setPrefSize(60, 50);
        two.setPrefSize(60, 50);
        three.setPrefSize(60, 50);
        add.setPrefSize(60, 50);
        HBox hBoxFive = new HBox(one, two, three, add);

        //Sixth row
        zero = new Button("0");
        decimal = new Button(".");
        equals = new Button("=");
        zero.setPrefSize(120, 50);
        decimal.setPrefSize(60, 50);
        equals.setPrefSize(60, 50);
        HBox hBoxSix = new HBox(zero, decimal, equals);

        layout = new VBox(hBoxOne, hBoxTwo, hBoxThree, hBoxFour, hBoxFive, hBoxSix);

        scene = new Scene(layout, 240, 310);

        //Handling key events with event handler on scene
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            String keyString = key.getText().toString();

            if(key.getCode().isDigitKey()){     //Handling digit keys 0-9
                if(num != 0){
                    numString += keyString;
                    num = Float.valueOf(numString);
                } else{
                    numString = keyString;
                    num = Float.valueOf(keyString);
                }
                textField.setText(numString);
            } else if(key.getCode() == KeyCode.PERIOD && !numString.contains(".")){     //Handling decimal point key
                numString += ".";
                num = Float.valueOf(numString);
                textField.setText(numString);
            } else if(key.getCode() == KeyCode.BACK_SPACE && !numString.isEmpty()){     //Handling backspace key
                if(numString.length() <= 1){
                    System.out.println("Empty");
                    numString = "0";
                    num = 0;
                } else{
                    numString = numString.substring(0, numString.length()-1);
                    num = Float.valueOf(numString);
                }
                textField.setText(numString);
            }
        });

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
