public class Model {
    float current;
    float saved;
    String currentString;
    String savedString;
    String operator;
    boolean first;

    Model(){
        current = 0;
        saved = 0;
        currentString = "0";
        savedString = "0";
        operator = "";
        first = true;
    }



    public float getCurrent() {
        return current;
    }
    public void setCurrent(float current) {
        this.current = current;
    }
    public float getSaved() {
        return saved;
    }
    public void setSaved(float saved) {
        this.saved = saved;
    }
    public String getCurrentString() {
        return currentString;
    }
    public void setCurrentString(String currentString) {
        this.currentString = currentString;
    }
    public String getSavedString() {
        return savedString;
    }
    public void setSavedString(String savedString) {
        this.savedString = savedString;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public boolean isFirst() {
        return first;
    }
    public void setFirst(boolean first) {
        this.first = first;
    }
}
