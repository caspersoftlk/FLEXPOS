package com.dw.pos.modal;

import javafx.scene.Node;

public class Card{
    public Card(String CODE, String CARDFORMAT, String COMM) {
        this.CODE = CODE;
        this.CARDFORMAT = CARDFORMAT;
        this.COMM = COMM;
    }
    String CODE;
    String CARDFORMAT;
    String COMM;
    Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getCARDFORMAT() {
        return CARDFORMAT;
    }

    public void setCARDFORMAT(String CARDFORMAT) {
        this.CARDFORMAT = CARDFORMAT;
    }

    public String getCOMM() {
        return COMM;
    }

    public void setCOMM(String COMM) {
        this.COMM = COMM;
    }

    @Override
    public String toString() {
        return CODE;
    }
}

