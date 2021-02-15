package com.dw.pos;

import com.dw.Common;
import com.dw.Main;
import com.dw.pos.modal.INVPAYDTLMAST;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class PayDtlController {
    private INVPAYDTLMAST paydtl;
    private POSView context;
    private int index;

    public INVPAYDTLMAST getPaydtl() {
        return paydtl;
    }

    public void setPaydtl(INVPAYDTLMAST paydtl) {
        this.paydtl = paydtl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @FXML
    private GridPane grid_btn;

    @FXML
    private Label lb_paytype;

    @FXML
    private Label lb_cardno;

    @FXML
    private Label lb_amt;


    public PayDtlController(INVPAYDTLMAST paydtl, POSView context,int index) {
        this.paydtl = paydtl;
        this.context = context;
        this.index = index;
    }

    @FXML
    private void initialize(){
        lb_paytype.setText(paydtl.getPAYTYPECODE());
        lb_amt.setText(Common.formatNumber(paydtl.getPAYTYPEAMT()));
        lb_cardno.setText(paydtl.getCARDNO());

        if(paydtl.getPAYTYPE().equals("Credit Card")){
            grid_btn.setStyle("-fx-background-color: #B43483; -fx-border-radius: 20; -fx-background-radius: 5");
        }else if(paydtl.getPAYTYPE().equals("Cash")){
            grid_btn.setStyle("-fx-background-color: #FBA23F; -fx-border-radius: 20; -fx-background-radius: 5");
        }

        grid_btn.addEventFilter(MouseEvent.ANY, new EventHandler<>() {

            long startTime;

            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    startTime = System.currentTimeMillis();
                } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    if (System.currentTimeMillis() - startTime > 1000) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remove Payment Type : " + paydtl.getPAYTYPECODE() + " and Amount : "+paydtl.getPAYTYPEAMT()+" ?", ButtonType.YES, ButtonType.NO);
                        alert.initOwner(Main.stg);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            context.removeItemFromPaymentTable(index);
                        }
                    }
                }
            }
        });
    }


}
