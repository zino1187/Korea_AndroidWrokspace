package com.koreait.boardclient;

import android.os.Parcel;
import android.os.Parcelable;

//다른 객체에게 전달할 수 있는 직렬화된 VO로 정의
public class Board implements Parcelable{
    private int board_id;
    private String title;
    private String writer;
    private String content;
    private String regdate;
    private int hit;

    public Board(){
    }

    //직렬화된 데이터를 받기
    protected Board(Parcel in) {
        board_id = in.readInt();
        title = in.readString();
        writer = in.readString();
        content = in.readString();
        regdate = in.readString();
        hit = in.readInt();
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }
        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    //보내기 위한 직렬화 및 저장
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(board_id);
        dest.writeString(title);
        dest.writeString(writer);
        dest.writeString(content);
        dest.writeString(regdate);
        dest.writeInt(hit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
