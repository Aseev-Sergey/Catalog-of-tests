package sample;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FillCatalog {

    public static void readFromFile(File file, ArrayList<String> array,
                                    ArrayList<Integer> arrayIndex, TextField logField)
    {
        Integer titleIndex = 1;
        try (Scanner sc = new Scanner(file, "Windows-1251"))
        {
            int index = 0;
            while (sc.hasNext())
            {
                array.add(sc.nextLine());
                try (Scanner label = new Scanner(array.get(index)))
                {
                    if(label.hasNext("[$]Category:")){
                        int beginIndex = array.get(index).indexOf(' ');
                        array.set(index, array.get(index).substring(beginIndex+1));
                        arrayIndex.add(titleIndex);
                        index++;
                    }else{ if(array.isEmpty()){
                        continue;
                    } else{
                        array.remove(index);
                    }}
                }
                titleIndex++;
            }
            if (index == 0){
                logField.setText("Неправильный формат");
                return;
            }
        } catch (FileNotFoundException exc)
        {
            logField.setText("Файл не найден");
            return;
        }
    }

    public static void setCatalog(TreeView<String> catalog, ArrayList<String> array, TextField logField){

        int index = 0;
        TreeItem<String> rootTreeNode = new TreeItem<>(array.get(index));
        TreeItem<String> levelOne = new TreeItem<>("Ошибка уровня 1");
        TreeItem<String> levelTwo = new TreeItem<>("Ошибка уровня 2");;
        TreeItem<String> levelThree = new TreeItem<>("Ошибка уровня 3");;
        index++;
        String[] prevStr = array.get(index).split("/");
        switch (prevStr.length){
            case 2:
                levelOne = new TreeItem<>(prevStr[1]);
                rootTreeNode.getChildren().add(levelOne);
                break;
            case 3:
                levelOne = new TreeItem<>(prevStr[1]);
                levelTwo = new TreeItem<>(prevStr[2]);
                levelOne.getChildren().add(levelTwo);
                rootTreeNode.getChildren().add(levelOne);
                break;
            case 4:
                levelOne = new TreeItem<>(prevStr[1]);
                levelTwo = new TreeItem<>(prevStr[2]);
                levelOne.getChildren().add(levelTwo);
                levelThree = new TreeItem<>(prevStr[3]);
                levelTwo.getChildren().add(levelThree);
                rootTreeNode.getChildren().add(levelOne);
            default: break;
        }
        index++;
        while(index < array.size()){
            String[] nextStr = array.get(index).split("/");
            if(prevStr.length != 2){
                if(prevStr[1].equals(nextStr[1])){
                    if(prevStr[2].equals(nextStr[2])){
                        levelThree = new TreeItem<>(nextStr[3]);
                        levelTwo.getChildren().add(levelThree);
                    }
                    else {
                        levelTwo = new TreeItem<>(nextStr[2]);
                        levelOne.getChildren().add(levelTwo);
                    }
                } else {
                    levelOne = new TreeItem<>(nextStr[1]);
                    rootTreeNode.getChildren().add(levelOne);
                }
            } else {
                switch (nextStr.length){
                    case 2:
                        levelOne = new TreeItem<>(nextStr[1]);
                        rootTreeNode.getChildren().add(levelOne);
                        break;
                    case 3:
                        levelTwo = new TreeItem<>(nextStr[2]);
                        levelOne.getChildren().add(levelTwo);
                        break;
                    case 4:
                        levelTwo = new TreeItem<>(nextStr[2]);
                        levelOne.getChildren().add(levelTwo);
                        levelThree = new TreeItem<>(nextStr[3]);
                        levelTwo.getChildren().add(levelThree);
                        break;
                    default: break;
                }
            }
            prevStr = nextStr.clone();
            index++;
        }
        catalog.setRoot(rootTreeNode);
        logField.setText("Каталог загружен");
    }
}
