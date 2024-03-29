package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerCatalog implements Initializable {

    public AnchorPane anchorPane;
    public TextField logField;
    public TreeView<String> catalog;
    public Button buttonEditor;
    public ArrayList<String> array;
    private Desktop desktop;
    final FileChooser fileChooser = new FileChooser();
    public ArrayList<Integer> arrayIndex;
    private MultipleSelectionModel<TreeItem<String>> selectionModel;
    private String pathMarker;
    private String pathFile;
    private LinkedList<String> list;
    HashMap<String, StringBuilder> hashMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = new LinkedList<>();
        hashMap = new HashMap<>();
        array = new ArrayList<>();
        arrayIndex = new ArrayList<>();
        desktop = Desktop.getDesktop();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

    }

    public void clickLoadCatalog(ActionEvent actionEvent) {

        File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if (file != null && FillCatalog.readFromFile(file, list, logField)) {
            pathFile = file.getPath();
            FillCatalog.setCatalog(catalog, list, hashMap, logField);
            buttonEditor.setDisable(false);
            selectionModel = catalog.getSelectionModel();
            selectionModel.selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>(){
                public void changed(ObservableValue<? extends TreeItem<String>> changed,
                                    TreeItem<String> oldValue, TreeItem<String> newValue){
                    if(newValue != null){
                        pathMarker = newValue.getValue();
                        TreeItem<String> parent = newValue.getParent();
                        while(parent != null){
                            pathMarker = parent.getValue() + "/" + pathMarker;
                            parent = parent.getParent();
                        }
                    }
                }
            });
        } else {
            catalog.setRoot(new TreeItem<>());
            buttonEditor.setDisable(true);
        }

    }

    public void clickEditFile(ActionEvent actionEvent) throws IOException {

        if(hashMap.containsKey(pathMarker)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editor.fxml"));
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(loader.load()));
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(buttonEditor.getScene().getWindow());
            secondaryStage.setTitle("Редактор");
            ControllerEditor controller = loader.getController();
            controller.setText(hashMap.get(pathMarker));
            secondaryStage.show();
        } else{
            logField.setText("Каталог пуст");
        }
        
        
        
    }


}
