package com.dw.pos;

import com.dw.Main;
import com.dw.pos.modal.TempItem;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class ItemController {
    private TempItem item;
    Anc_ItemViewController contex;
    public ItemController(TempItem i,Anc_ItemViewController context){
        this.item = i;
        this.contex = context;
    }

    public TempItem getItem() {
        return item;
    }

    public void setItem(TempItem item) {
        this.item = item;
    }

    @FXML
    private Label sqNo;

    @FXML
    private Label code;

    @FXML
    private Label description;

    @FXML
    private Label qty;

    @FXML
    private Label uprice;

    @FXML
    private Label dis;

    @FXML
    private Label amt;

    @FXML
    private JFXButton btn_del;

    @FXML
    private void initialize() {
        sqNo.setText(String.valueOf(item.getSqNo()));
    }

    @FXML
    void removeItem(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + description.getText() + " ?", ButtonType.YES, ButtonType.NO);
        alert.initOwner(Main.stg);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            contex.removeItemFromTable(item.getSqNo());

        }
    }

    public void changeSqNo(){
        sqNo.setText(String.valueOf(item.getSqNo()));
    }
}
