import java.util.Random;

public class ReachableFieldsOnChessboard {

    public static void main(String[] args) throws Chessboard.Chesspiece.NotValidFieldException {
        Chessboard chessBoard = new Chessboard ();
        System.out.println (chessBoard + "\n");
        Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
        pieces[0] = chessBoard.new Pawn ('w', 'P');  //klar
        pieces[1] = chessBoard.new Rook ('b', 'R');  //klar
        pieces[2] = chessBoard.new Queen ('w', 'Q');  //klar
        pieces[3] = chessBoard.new Bishop ('w', 'B'); //klar
        pieces[4] = chessBoard.new King ('b', 'K');   //klar
        pieces[5] = chessBoard.new Knight ('w', 'N'); //klar
        Random random = new Random();
        System.out.println(pieces[0].isOnBoard());

        for(int i = 0; i<pieces.length;i++ ) {
            try {
                pieces[i].moveTo((char)('a'+random.nextInt(7)+1),(byte)(random.nextInt(8)+1));
            } catch (Chessboard.Chesspiece.NotValidFieldException e) {
                System.out.println(e.getException());
            }
            System.out.println(chessBoard);

            System.out.println(pieces[i].isOnBoard());
            pieces[i].moveOut();
        }
    }
}
