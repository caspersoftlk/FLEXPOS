package com.dw.pos;

import com.dw.Common;
import com.dw.pos.modal.Card;
import com.dw.pos.modal.INVPAYDTLMAST;
import com.dw.pos.modal.TempItem;
import com.dw.pos.utill.posDataController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;


public class POSView {

    @FXML
    private TextField tf_qty,tf_barcode,tf_time,tf_cusAmount,tf_cusAmount1,tf_totalPayable,tf_cashBal,tf_cardno;
    @FXML
    private VBox vBox_sidebar,vBox_MainContainer;
    @FXML
    private JFXHamburger ico_hamb;

    @FXML
    private AnchorPane ancPane_Container;

    @FXML
    private HBox hbox_card,hbox_fincard;
    @FXML
    private GridPane grid_cash,grid_card;

    @FXML
    private JFXButton btn_bks;
    @FXML
    private FlowPane Flow_PaymCash;


    //Items
    ArrayList<ItemController> itemList_views = new ArrayList<>();
    ArrayList<CardController> cardList_views = new ArrayList<>();

    //Utill Poss
    private posDataController dataController;

    @FXML
    private void initialize() {
        dataController = new posDataController();


        showSidebar(null);

        //Time Date
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            tf_time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        //TODO
        hideOrShowVboxItemTable();
        //initCard();
//        hideOrShowVboxItemTable(true);
//        hideOrShowGridCash(false);
//        hideOrShowGridCard(false);

//        tf_cusAmount.textProperty().addListener((observable, oldValue, newValue) -> {
//            tf_cashBal.setText(Common.formatNumber(getCusBalance(newValue)));
//        });


        //Card Number Mask
//        tf_cardno.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println(newValue.length());
//            if(newValue.length()==4||newValue.length()==9||newValue.length()==14){
//                tf_cardno.setText(newValue.concat("-"));
//            }
//            if (tf_cardno.getText().length() > 19) {
//                String s = tf_cardno.getText().substring(0, 19);
//                tf_cardno.setText(s);
//            }
//            hbox_card.setDisable(!CheckCC(tf_cardno.getText().replace("-","")));
//        });

        //Numpad Backspace button
        btn_bks.addEventFilter(MouseEvent.ANY, new EventHandler<>() {

            long startTime;

            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    startTime = System.currentTimeMillis();
                } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    if (System.currentTimeMillis() - startTime > 1000) {
                        TextField textField = getFocusTextBox();
                        textField.clear();
                    }
                }
            }
        });
    }

    @FXML
    void showSidebar(InputEvent event) {
        if(vBox_sidebar.getScaleX()==0) {

            new Thread(() -> {
                int i=0;
                while (i<150){
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i=i+3;
                    vBox_sidebar.setPrefWidth(i);
                }
            }).start();
            vBox_sidebar.setScaleX(1);
            ico_hamb.setRotate(90);
        }else{
            new Thread(() -> {
                int i=150;
                while (i>0){
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i=i-3;
                    vBox_sidebar.setPrefWidth(i);
                }
            }).start();
            vBox_sidebar.setScaleX(0);
            ico_hamb.setRotate(0);
        }

    }

    /* NumPad */
    public void eventNumPadAction(ActionEvent actionEvent) {

        JFXButton x = (JFXButton) actionEvent.getSource();
        String val = x.getText();


        TextField textField = getFocusTextBox();

        String tempVal = textField.getText();
        if(tempVal.contains(".")&&val.equals(".")){
            return;
        }

        if(val.equals("bk")){
            tempVal = bk(tempVal);
            textField.setText(tempVal);
            textField.positionCaret(textField.getText().length());
            return;
        }

        if(val.equals("ENTER")){
            textField.fireEvent(actionEvent);
            return;
        }

        textField.setText(tempVal.concat(val));
        textField.positionCaret(textField.getText().length());

    }
    public String bk(String str) {
        if (str != null && str.length() > 0 ) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    public TextField getFocusTextBox(){
        if(tf_qty.isFocused())
            return tf_qty;
        else if(tf_barcode.isFocused())
            return tf_barcode;
        else if(tf_cusAmount.isFocused())
            return tf_cusAmount;
        else if(tf_cardno.isFocused())
            return tf_cardno;
        else
            return tf_barcode;
    }
    /* End >> NumPad */





    /* Show And Hide Containers */
    private String focusStage = "I";
    private void hideOrShowVboxItemTable(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Anc_ItemView.fxml"));
            Anc_ItemViewController controller = new Anc_ItemViewController();
            fxmlLoader.setController(controller);

            ancPane_Container.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private void hideOrShowGridCash(boolean show){
//        if(show){
//            grid_cash.setMaxHeight(-1);
//            grid_cash.setScaleX(1);
//            tf_cusAmount.requestFocus();
//        }else{
//            grid_cash.setMaxHeight(0);
//            grid_cash.setScaleX(0);
//        }
//    }
    private void hideOrShowGridCard(boolean show){
        if(show){
            grid_card.setMaxHeight(-1);
            grid_card.setScaleX(1);
            tf_cardno.requestFocus();
        }else{
            grid_card.setMaxHeight(0);
            grid_card.setScaleX(0);
        }
    }

    @FXML
    void showGridChash(ActionEvent event) {
//        hideOrShowVboxItemTable(false);
//        hideOrShowGridCash(true);
//        hideOrShowGridCard(false);
//        Flow_PaymCash.getChildren().addAll(hbox_fincard.getChildren());
//        focusStage ="CS";
    }
    @FXML
    void showGridCard(ActionEvent event) {
//        hideOrShowVboxItemTable(false);
//        hideOrShowGridCash(false);
//        hideOrShowGridCard(true);
//        hbox_fincard.getChildren().addAll(Flow_PaymCash.getChildren());
//        focusStage = "CC";
    }
    @FXML
    void getBackTovBoxItemTable(ActionEvent event) {
//        hideOrShowVboxItemTable(true);
//        hideOrShowGridCash(false);
//        hideOrShowGridCard(false);
//        focusStage = "I";
    }
    /* End >> Show And Hide Containers */




    @FXML
    public void eventCashPadAction(ActionEvent actionEvent) {

        JFXButton x = (JFXButton) actionEvent.getSource();
        String val = x.getText();
        System.out.println(val);

        double curVal = Double.parseDouble(tf_cusAmount.getText().isEmpty()?"0":tf_cusAmount.getText());
        curVal = curVal + Double.parseDouble(val);
        tf_cusAmount.setText(String.valueOf(curVal));
        tf_cusAmount.positionCaret(tf_cusAmount.getText().length());
    }

    public double getCusBalance(String val){
        double payable = Common.deFormatNumber(tf_totalPayable.getText());
        double newVal = Common.deFormatNumber(val);
        return newVal-payable;
    }


    /* Payment - Card */
    int noPaymentCount=0;
    private void initCard(){
        try {
            Node[] nodes = new Node[4];

            for(int i = 0; i<nodes.length;i++){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card.fxml"));
                Card item = new Card("AMEX","","");
                CardController controller = new CardController(item,this);
                fxmlLoader.setController(controller);

                item.setNode(fxmlLoader.load());
                controller.setItem(item);
                cardList_views.add(controller);
                hbox_card.getChildren().add(cardList_views.get(i).getItem().getNode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<PayDtlController> payDtlControllerArrayList = new ArrayList<>();

    @FXML
    public void addPayDtlCard(ActionEvent event){

        try {
            JFXButton x = (JFXButton) event.getSource();
            String cardType = x.getText();
            double cusAmount = Common.deFormatNumber(tf_cusAmount1.getText());
            String cardNo = tf_cardno.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PayDtl.fxml"));
            INVPAYDTLMAST dtl = new INVPAYDTLMAST();
            dtl.setCARDNO(cardNo);
            dtl.setPAYTYPECODE(cardType);
            dtl.setPAYTYPEAMT(cusAmount);
            dtl.setPAYTYPE("Credit Card");
            PayDtlController controller = new PayDtlController(dtl,this,noPaymentCount);
            noPaymentCount++;
            fxmlLoader.setController(controller);
            dtl.setNode(fxmlLoader.load());
            payDtlControllerArrayList.add(controller);
            hbox_fincard.getChildren().add(dtl.getNode());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void addPayDtlCash(ActionEvent event){

        try {
            JFXButton x = (JFXButton) event.getSource();
            String cardType = x.getText();
            double cusAmount = Common.deFormatNumber(tf_cusAmount1.getText());
            String cardNo = tf_cardno.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PayDtl.fxml"));
            INVPAYDTLMAST dtl = new INVPAYDTLMAST();
            dtl.setCARDNO(cardNo);
            dtl.setPAYTYPECODE(cardType);
            dtl.setPAYTYPEAMT(cusAmount);
            dtl.setPAYTYPE("Credit Card");
            PayDtlController controller = new PayDtlController(dtl,this,noPaymentCount);
            noPaymentCount++;
            fxmlLoader.setController(controller);
            dtl.setNode(fxmlLoader.load());
            payDtlControllerArrayList.add(controller);
            hbox_fincard.getChildren().add(dtl.getNode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromPaymentTable(int index){
        payDtlControllerArrayList.remove(index);
        if(focusStage.equals("CC")){
            hbox_fincard.getChildren().clear();
        }else if(focusStage.equals("CS")){
            Flow_PaymCash.getChildren().clear();
        }
        int i =0;
        for (PayDtlController e:payDtlControllerArrayList) {
            e.setIndex(i++);
            if(focusStage.equals("CC")){
                hbox_fincard.getChildren().add(e.getPaydtl().getNode());
            }else if(focusStage.equals("CS")){
                Flow_PaymCash.getChildren().add(e.getPaydtl().getNode());
            }
        }
    }

    @FXML
    void eventValidateCC(ActionEvent event) {
        System.out.println(CheckCC(tf_cardno.getText()));
    }
    public boolean CheckCC(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    /* End >> Payment Card */
}
