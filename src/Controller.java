import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    Model model;
    View view;

    Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void handleKey(KeyEvent event){
        if(event.getCode().isDigitKey()){
            number(event.getText().toString());
        } else if(event.getCode() == KeyCode.PLUS){

        }
    }

    public void number(String num){
        if(model.isFirst()){
            model.setCurrentString(num);
            model.setFirst(false);
        } else {
            model.setCurrentString(model.getCurrentString() + num);
        }
        currentNumFromString();
        view.updateTextFieldCurrent();
    }

    public void currentNumFromString(){
        model.setCurrent(Float.valueOf(model.currentString));
    }
    public void currentStringFromNum(){
        model.setCurrentString(String.valueOf(model.current));
    }
}
