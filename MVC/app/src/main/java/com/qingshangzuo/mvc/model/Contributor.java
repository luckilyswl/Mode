package com.qingshangzuo.mvc.model;

public class Contributor {
    public String login;
    public int contributions;

    @Override
    public String toString() {
        return login + "," + contributions;
    }
}
