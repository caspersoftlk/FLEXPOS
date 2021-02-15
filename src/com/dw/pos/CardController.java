package com.dw.pos;

import com.dw.pos.modal.Card;
import com.dw.pos.modal.TempItem;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CardController {
    private Card item;
    POSView contex;
    public CardController(Card i,POSView context){
        this.item = i;
        this.contex = context;
    }

    public Card getItem() {
        return item;
    }

    public void setItem(Card item) {
        this.item = item;
    }

    public POSView getContex() {
        return contex;
    }

    public void setContex(POSView contex) {
        this.contex = contex;
    }

    public JFXButton getLb_cardname() {
        return btn_card;
    }

    public void setLb_cardname(JFXButton lb_cardname) {
        this.btn_card = lb_cardname;
    }

    @FXML
    private JFXButton btn_card;
    @FXML
    private void initialize() {
        btn_card.setText(String.valueOf(item.getCODE()));

        btn_card.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contex.addPayDtlCard(event);
                System.out.println("SS");
            }
        });

    }
}
