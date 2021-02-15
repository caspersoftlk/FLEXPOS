package com.dw.pos.modal;

import javafx.scene.Node;

public class INVPAYDTLMAST {
    int INVPAYDTLKEY;
    String INVNO,PAYTYPE,PAYTYPECODE,CARDNO,LOCATION;
    double FCURAMT,PAYTYPEAMT,EXCHANGERATE;
    Node node;

    public int getINVPAYDTLKEY() {
        return INVPAYDTLKEY;
    }

    public void setINVPAYDTLKEY(int INVPAYDTLKEY) {
        this.INVPAYDTLKEY = INVPAYDTLKEY;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getPAYTYPE() {
        return PAYTYPE;
    }

    public void setPAYTYPE(String PAYTYPE) {
        this.PAYTYPE = PAYTYPE;
    }

    public String getPAYTYPECODE() {
        return PAYTYPECODE;
    }

    public void setPAYTYPECODE(String PAYTYPECODE) {
        this.PAYTYPECODE = PAYTYPECODE;
    }

    public String getCARDNO() {
        return CARDNO;
    }

    public void setCARDNO(String CARDNO) {
        this.CARDNO = CARDNO;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public double getFCURAMT() {
        return FCURAMT;
    }

    public void setFCURAMT(double FCURAMT) {
        this.FCURAMT = FCURAMT;
    }

    public double getPAYTYPEAMT() {
        return PAYTYPEAMT;
    }

    public void setPAYTYPEAMT(double PAYTYPEAMT) {
        this.PAYTYPEAMT = PAYTYPEAMT;
    }

    public double getEXCHANGERATE() {
        return EXCHANGERATE;
    }

    public void setEXCHANGERATE(double EXCHANGERATE) {
        this.EXCHANGERATE = EXCHANGERATE;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
