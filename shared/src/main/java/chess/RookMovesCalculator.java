package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements PieceMovesCalculator{
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        // Create variable of possible moves
        var validMoves = new ArrayList<ChessMove>();
        int currRow = position.getRow();  // rows and columns are 1 through 8 while board is 0 from 7
        int currCol = position.getColumn();

        boolean up = true;
        boolean left = true;
        boolean down = true;
        boolean right = true;

        for (int i = 1; i <= 8; i++) {
            if (currRow + i <= 8){
                var nextPosition = new ChessPosition(currRow+i, currCol);
                up = HelperFunction(validMoves, board, position, nextPosition, up);
            }
            if (currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow, currCol-i);
                left = HelperFunction(validMoves, board, position, nextPosition, left);
            }
            if (currRow - i >= 1){
                var nextPosition = new ChessPosition(currRow-i, currCol);
                down = HelperFunction(validMoves, board, position, nextPosition, down);
            }
            if (currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow, currCol+i);
                right = HelperFunction(validMoves, board, position, nextPosition, right);
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
