class ReachableFieldsOnChessboard
{
    public static void main(String args[])
    {
        Chessboard chessBoard = new Chessboard ();
        System.out.println (chessBoard + "\n");
        Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
        pieces[0] = chessBoard.new Pawn ('w', 'P');
        pieces[1] = chessBoard.new Rook ('b', 'R');
        pieces[2] = chessBoard.new Queen ('w', 'Q');
        pieces[3] = chessBoard.new Bishop ('w', 'B');
        pieces[4] = chessBoard.new King ('b', 'K');
        pieces[5] = chessBoard.new Knight ('w', 'N');


    }



}