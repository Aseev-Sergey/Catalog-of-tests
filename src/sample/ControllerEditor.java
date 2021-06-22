package sample;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControllerEditor {

    public TextArea editorArea;
    public Button buttonClose;


    public void clickClose(ActionEvent actionEvent) {
        buttonClose.getScene().getWindow().hide();
    }

    public void setText(StringBuilder stringBuilder){
        editorArea.setText(stringBuilder.toString());
    }

}
