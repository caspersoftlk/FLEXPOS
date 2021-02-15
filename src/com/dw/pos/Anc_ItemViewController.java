package com.dw.pos;

import com.dw.pos.modal.TempItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Anc_ItemViewController {
    @FXML
    private VBox vBox_itemsTable,vBox_itemList;

    ArrayList<ItemController> itemList_views = new ArrayList<>();

    @FXML
    private void initialize() {
        initTalbe();
    }

    private void initTalbe(){
        try {
            Node[] nodes = new Node[10];

            for(int i = 0; i<nodes.length;i++){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item.fxml"));
                TempItem item = new TempItem(i);
                ItemController controller = new ItemController(item,this);
                fxmlLoader.setController(controller);

                item.setNode(fxmlLoader.load());
                controller.setItem(item);
                itemList_views.add(controller);
                vBox_itemList.getChildren().add(itemList_views.get(i).getItem().getNode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeItemFromTable(int index){
        itemList_views.remove(index);
        vBox_itemList.getChildren().clear();
        int i =0;
        for (ItemController e:itemList_views) {
            e.getItem().setSqNo(i++);
            e.changeSqNo();
            vBox_itemList.getChildren().add(e.getItem().getNode());
        }
    }
}
