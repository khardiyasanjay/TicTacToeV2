package org.example.tictactoe;

import org.example.tictactoe.model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    TicTacToeGame(){
        initializeGame();
    }

    public void initializeGame(){
        // create 2 players
        players = new LinkedList<>();
        PlayingPieceX playingPieceX = new PlayingPieceX();
        Player player1 = new Player("player1", playingPieceX);

        PlayingPieceO playingPieceO = new PlayingPieceO();
        Player player2 = new Player("player2", playingPieceO);

        players.add(player1);
        players.add(player2);

        // initialize board
        gameBoard = new Board(3);
    }

    public String startGame(){
        boolean noWinner = true;

        while(noWinner){
            //take out the player whose turn it is and also put back at the end
            Player playerTurn = players.removeFirst();

            gameBoard.printBoard();

            //get the free space from the board
            List<Board.Pair> freeCells = gameBoard.getFreeCells();
            if(freeCells.isEmpty()){
                noWinner = false;
                continue;
            }

            //read the user input
            System.out.println("Player " + playerTurn.name + " enter row,column: ");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String[] inputValues = s.split(",");
            int row = Integer.valueOf(inputValues[0]);
            int column = Integer.valueOf(inputValues[1]);

            //place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(row, column, playerTurn.playingPiece);
            if(!pieceAddedSuccessfully){
                System.out.println("Incorrect position choosen, try again");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner = isThereWinner(row, column, playerTurn.playingPiece);

            if(winner){
                return playerTurn.name;
            }
        }

        return "tie";
    }

    public boolean isThereWinner(int row, int column, PlayingPiece piece){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for (int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i]!=piece){
                rowMatch = false;
            }
        }

        //need to check in column
        for (int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[i][column]==null || gameBoard.board[i][column]!=piece){
                columnMatch = false;
            }
        }

        for (int i=0,j=0;i<gameBoard.size;i++,j++){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j]!=piece){
                diagonalMatch=false;
            }
        }

        for (int i=0,j=gameBoard.size-1;i<gameBoard.size;i++,j--){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j]!=piece){
                antiDiagonalMatch=false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
