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
        String text = event.getText();

        if(isNumeric(text)){
            number(event.getText().toString());
            view.updateTextFieldCurrent();
        } else if(text.equals(".")) {
            decimal();
            view.updateTextFieldCurrent();
        } else if(isOperator(text)){
            handleOperator(text);
        } else if(event.getCode() == KeyCode.ENTER){
            compute(model.getOperator());
            model.setOperator("");
            view.updateTextFieldCurrent();
        } else if(event.getCode() == KeyCode.CONTROL){
            if(model.isFirst() && !model.getOperator().isEmpty()){
                model.setSaved(model.getSaved() * -1);
                updateSavedString();
                view.updateTextFieldSaved();
            } else {
                model.setCurrent(model.getCurrent() * -1);
                updateCurrentString();
                view.updateTextFieldCurrent();
            }
        } else if(event.getCode() == KeyCode.BACK_SPACE){
            try{
                if(model.getCurrentString().length() <= 1){
                    model.setCurrentString("0");
                    model.setFirst(true);
                } else{
                    model.setCurrentString(model.getCurrentString().substring(0, model.getCurrentString().length()-1));
                }
                updateCurrentFloat();
                view.updateTextFieldCurrent();
            } catch(NumberFormatException e){

            }
        } else if(event.getCode() == KeyCode.ESCAPE){
            model.setSaved(0);
            model.setCurrent(0);
            model.setCurrentString("0");
            model.setSavedString("0.0");
            model.setOperator("");
            model.setFirst(true);
            view.updateTextFieldCurrent();
        } else if(event.getCode() == KeyCode.P){
            if(model.isFirst() && !model.getOperator().isEmpty()){
                model.setSaved(model.getSaved() * 0.01f);
                updateSavedString();
                view.updateTextFieldSaved();
            } else {
                model.setCurrent(model.getCurrent() * 0.01f);
                updateCurrentString();
                view.updateTextFieldCurrent();
            }
        }
    }

    public void number(String num){
        if(model.isFirst()){
            model.setCurrentString(num);
            model.setFirst(false);
        } else {
            model.setCurrentString(model.getCurrentString() + num);
        }
        updateCurrentFloat();
    }


    public void decimal(){
        if(model.isFirst()){
            model.setCurrentString("0.");
            updateCurrentFloat();
            model.setFirst(false);
        } else if(!model.getCurrentString().contains(".")){
            model.setCurrentString(model.getCurrentString() + ".");
            updateCurrentFloat();
        }
    }

    public void handleOperator(String op){
        if (model.getOperator().equals(op) && model.isFirst()){
            System.out.println("do nothing, same operator pressed twice");
            //do nothing
            return;
        } else if(model.isFirst() && !model.getOperator().equals(op)){
            System.out.println("switching operator");
            model.setOperator(op);
        } else if(!model.isFirst() && !model.getOperator().equals(op)){
            compute(model.getOperator());
            model.setOperator(op);
            view.updateTextFieldCurrent();
        } else{
            if(model.getSaved() == 0){
                System.out.println("first number in operation saved");
                model.setSaved(model.getCurrent());
                model.setOperator(op);
                updateSavedString();
                model.setFirst(true);
            } else{
                System.out.println("computing, have both numbers");
                compute(op);
            }
            view.updateTextFieldCurrent();
        }
    }

    public void compute(String op){
        switch(op){
            case "+":
                model.setCurrent(model.getSaved() + model.getCurrent());
                break;
            case "-":
                model.setCurrent(model.getSaved() - model.getCurrent());
                break;
            case "/":
                model.setCurrent(model.getSaved() / model.getCurrent());
                break;
            case "*":
                model.setCurrent(model.getSaved() * model.getCurrent());
                break;
        }
        updateCurrentString();
        model.setFirst(true);
        model.setOperator("");
        model.setSaved(model.getCurrent());
        updateSavedString();
        view.updateTextFieldCurrent();
    }

    public boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public void updateCurrentFloat() {
        model.setCurrent(Float.valueOf(model.getCurrentString()));
    }
    public void updateCurrentString() {
        model.setCurrentString(String.valueOf(model.getCurrent()));
    }
    public void updateSavedFloat() {
        model.setSaved(Float.valueOf(model.getSavedString()));
    }
    public void updateSavedString(){
        model.setSavedString(String.valueOf(model.getSaved()));
    }
}
