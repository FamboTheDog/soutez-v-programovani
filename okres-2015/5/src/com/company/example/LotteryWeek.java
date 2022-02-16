package com.company.example;

import java.util.Arrays;

public class LotteryWeek {
    private int year;
    private int week;
    private int[] firstMove;
    private int firstMoveAddition;
    private int[] secondMove;
    private int secondMoveAddition;

    public LotteryWeek(int year, int week, int[] firstMove, int firstMoveAddition, int[] secondMove, int secondMoveAddition) {
        this.year = year;
        this.week = week;
        this.firstMove = firstMove;
        this.firstMoveAddition = firstMoveAddition;
        this.secondMove = secondMove;
        this.secondMoveAddition = secondMoveAddition;
    }

    public int[] getSecondMove() {
        return secondMove;
    }

    public void setSecondMove(int[] secondMove) {
        this.secondMove = secondMove;
    }

    public int getSecondMoveAddition() {
        return secondMoveAddition;
    }

    public void setSecondMoveAddition(int secondMoveAddition) {
        this.secondMoveAddition = secondMoveAddition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int[] getFirstMove() {
        return firstMove;
    }

    public void setFirstMove(int[] firstMove) {
        this.firstMove = firstMove;
    }

    public int getFirstMoveAddition() {
        return firstMoveAddition;
    }

    public void setFirstMoveAddition(int firstMoveAddition) {
        this.firstMoveAddition = firstMoveAddition;
    }

}
