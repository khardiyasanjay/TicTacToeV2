package org.example.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece){
        if(board[row][column]!=null){
            return false;
        }

        board[row][column] = playingPiece;
        return true;
    }

    public static class Pair{
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public List<Pair> getFreeCells(){
        List<Pair> freeCells = new ArrayList<>();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null){
                    freeCells.add(new Pair(i, j));
                }
            }
        }

        return freeCells;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null)
                    System.out.print(board[i][j] + " | ");
                else
                    System.out.print(board[i][j].pieceType + " | ");
            }
            System.out.println();
        }
    }
}
