package com.dw.pos.modal;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;

public class TempItem {
    int sqNo;
    String code;
    String description;
    double qty;
    double uprice;
    double dis;
    double amt;
    JFXButton del;
    Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public TempItem(int sqNo) {
        this.sqNo = sqNo;
    }

    public int getSqNo() {
        return sqNo;
    }

    public void setSqNo(int sqNo) {
        this.sqNo = sqNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public JFXButton getDel() {
        return del;
    }

    public void setDel(JFXButton del) {
        this.del = del;
    }
}
