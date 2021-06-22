package sample;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FillCatalog {

    public static boolean readFromFile(File file, LinkedList<String> list, TextField logField) {

        try(Scanner sc = new Scanner(file, "Windows-1251"))
        {
            while(sc.hasNext()){
                list.add(sc.nextLine());
            }
        } catch (FileNotFoundException exc)
        {
            logField.setText("Файл не найден");
            return false;
        }
        return true;

    }

    private static int fillValue(int index, LinkedList<String> list, StringBuilder stringBuilder){
        do{
            stringBuilder.append(list.get(index).trim()).append('\n');
            index++;
        } while(index < list.size() && !(list.get(index).startsWith("$Category:")));
        return index;
    }

    public static void setCatalog(TreeView<String> catalog, LinkedList<String> list,
                                  HashMap<String, StringBuilder> hashMap, TextField logField){

        int index = 0;
        TreeItem<String> rootTreeNode = new TreeItem<>(list.get(index));
        String prevStr = list.get(index++);
        while(index < list.size()){
            String nextStr = list.get(index);
            if(prevStr.startsWith("$Category:") && !(nextStr.startsWith("$Category:"))){
                StringBuilder stringBuilder = new StringBuilder();
                index = fillValue(index, list, stringBuilder);
                hashMap.put(prevStr, stringBuilder);
                String[] packages = prevStr.split("/");
                for(String packageName : packages) {
                    if(rootTreeNode.getChildren().contains(rootTreeNode.getChildren().indexOf(packageName))) {
                        TreeItem<String> packageNode = rootTreeNode.getChildren().
                                get(rootTreeNode.getChildren().indexOf(packageName));
                        packageNode.getChildren().add(new TreeItem<String>(packageName));
                    } else {
                        rootTreeNode.getChildren().add(new TreeItem<String>(packageName));
                    }
                }
                if(index < list.size()) {
                    prevStr = list.get(index++);
                } else{ break;}

            } else{
                prevStr = nextStr;
                index++;
            }
        }
        catalog.setRoot(rootTreeNode);
        logField.setText("Каталог загружен");
    }
}
