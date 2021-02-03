package com.koreait.websocketclient;

public class BoardException extends RuntimeException{
    public BoardException(String msg){
        super(msg);
    }
    public BoardException(String msg, Throwable e){
        super(msg, e);
    }
    public BoardException(Throwable e){
        super(e);
    }
}
