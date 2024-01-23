package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovesCalculator implements PieceMovesCalculator{
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        // Create variable of possible moves
        var validMoves = new ArrayList<ChessMove>();
        int currRow = position.getRow();  // rows and columns are 1 through 8 while board is 0 from 7
        int currCol = position.getColumn();

        boolean up = true;
        boolean upLeft = true;
        boolean left = true;
        boolean downLeft = true;
        boolean down = true;
        boolean downRight = true;
        boolean right = true;
        boolean upRight = true;

        for (int i = 1; i <= 8; i++) {
            if (currRow + i <= 8){
                var nextPosition = new ChessPosition(currRow+i, currCol);
                up = HelperFunction(validMoves, board, position, nextPosition, up);
            }
            if (currRow + i <= 8 && currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow+i, currCol-i);
                upLeft = HelperFunction(validMoves, board, position, nextPosition, upLeft);
            }
            if (currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow, currCol-i);
                left = HelperFunction(validMoves, board, position, nextPosition, left);
            }
            if (currRow - i >= 1 && currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow-i, currCol-i);
                downLeft = HelperFunction(validMoves, board, position, nextPosition, downLeft);
            }
            if (currRow - i >= 1){
                var nextPosition = new ChessPosition(currRow-i, currCol);
                down = HelperFunction(validMoves, board, position, nextPosition, down);
            }
            if (currRow - i >= 1 && currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow-i, currCol+i);
                downRight = HelperFunction(validMoves, board, position, nextPosition, downRight);
            }
            if (currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow, currCol+i);
                right = HelperFunction(validMoves, board, position, nextPosition, right);
            }
            if (currRow + i <= 8 && currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow+i, currCol+i);
                upRight = HelperFunction(validMoves, board, position, nextPosition, upRight);
            }
        }
        return validMoves;
    }

    public boolean HelperFunction(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition position, ChessPosition nextPosition, boolean keepGoing) {
        if (keepGoing) {
            if ((board.getPiece(nextPosition) == null)) {
                validMoves.add(new ChessMove(position, nextPosition));
                return true;
            } else if ((board.getPiece(nextPosition).getTeamColor() != board.getPiece(position).getTeamColor())) {
                validMoves.add(new ChessMove(position, nextPosition));
                return false;
            }
            else return false;
        }
        return false;
    }
}
