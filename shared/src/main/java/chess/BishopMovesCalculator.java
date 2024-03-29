package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalculator implements PieceMovesCalculator{

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        // Create variable of possible moves
        var validMoves = new ArrayList<ChessMove>();
        int currRow = position.getRow();  // rows and columns are 1 through 8 while board is 0 from 7
        int currCol = position.getColumn();

        boolean upRight = true;
        boolean upLeft = true;
        boolean downRight = true;
        boolean downLeft = true;

        for (int i = 1; i <= 8; i++) {
            if (currRow + i <= 8 && currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow+i, currCol+i);

                upRight = helperFunction(validMoves, board, position, nextPosition, upRight);
            }
            if (currRow + i <= 8 && currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow+i, currCol-i);
                upLeft = helperFunction(validMoves, board, position, nextPosition, upLeft);
            }
            if (currRow - i >= 1 && currCol + i <= 8){
                var nextPosition = new ChessPosition(currRow-i, currCol+i);
                downRight = helperFunction(validMoves, board, position, nextPosition, downRight);
            }
            if (currRow - i >= 1 && currCol - i >= 1){
                var nextPosition = new ChessPosition(currRow-i, currCol-i);
                downLeft = helperFunction(validMoves, board, position, nextPosition, downLeft);
            }
        }
        return validMoves;
    }

    public boolean helperFunction(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition position, ChessPosition nextPosition, boolean keepGoing) {
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
