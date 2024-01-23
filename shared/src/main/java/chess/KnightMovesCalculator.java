package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalculator implements PieceMovesCalculator{
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){

        // Create variable of possible moves
        var validMoves = new ArrayList<ChessMove>();
        int currRow = position.getRow();  // rows and columns are 1 through 8 while board is 0 from 7
        int currCol = position.getColumn();

        if (currRow + 2 <= 8 && currCol + 1 <= 8){
            var nextPosition = new ChessPosition(currRow+2, currCol+1);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow + 1 <= 8 && currCol + 2 <= 8){
            var nextPosition = new ChessPosition(currRow+1, currCol+2);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow - 1 >= 1 && currCol + 2 <= 8){
            var nextPosition = new ChessPosition(currRow-1, currCol+2);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow - 2 >= 1 && currCol + 1 <= 8){
            var nextPosition = new ChessPosition(currRow-2, currCol+1);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow - 2 >= 1 && currCol - 1 >= 1){
            var nextPosition = new ChessPosition(currRow-2, currCol-1);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow - 1 >= 1 && currCol - 2 >= 1){
            var nextPosition = new ChessPosition(currRow-1, currCol-2);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow + 1 <= 8 && currCol - 2 >= 1){
            var nextPosition = new ChessPosition(currRow+1, currCol-2);
            HelperFunction(validMoves, board, position, nextPosition);
        }
        if (currRow + 2 <= 8 && currCol - 1 >= 1){
            var nextPosition = new ChessPosition(currRow+2, currCol-1);
            HelperFunction(validMoves, board, position, nextPosition);
        }

        return validMoves;
    }

    public void HelperFunction(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition position, ChessPosition nextPosition) {
        if ((board.getPiece(nextPosition) == null)) {
            validMoves.add(new ChessMove(position, nextPosition));
        } else if ((board.getPiece(nextPosition).getTeamColor() != board.getPiece(position).getTeamColor())) {
            validMoves.add(new ChessMove(position, nextPosition));
        }
    }
}
