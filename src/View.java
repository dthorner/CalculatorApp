import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {


    Stage stage;
    Scene scene;
    VBox layout;
    Label textField;
    Button clear, negate, percent, divide;
    Button seven, eight, nine, multiply;
    Button four, five, six, subtract;
    Button one, two, three, add;
    Button zero, decimal, equals;
    Model model;
    Controller controller;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        model = new Model();
        controller = new Controller(this.model, this);

        //First row: input (textField)
        textField = new Label(model.getCurrentString());
        textField.setPadding(new Insets(0, 10, 5, 5));
        textField.setAlignment(Pos.BASELINE_RIGHT);
        textField.setFont(new Font(50));
        textField.setPrefSize(240, 60);
        HBox hBoxOne = new HBox(textField);
        //Second row
        clear = new Button("AC");
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

        //Layout is a VBox full of HBoxes
        layout = new VBox(hBoxOne, hBoxTwo, hBoxThree, hBoxFour, hBoxFive, hBoxSix);
        //create scene with specified layout
        scene = new Scene(layout, 240, 310);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            controller.handleKey(key);
        });
        //settings the stage
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void setTextField(String text){
        this.textField.setText(text);
    }
    public void updateTextFieldCurrent(){
        this.textField.setText(model.getCurrentString());
    }
    public void updateTextFieldSaved(){
        this.textField.setText(model.getSavedString());
    }
}
