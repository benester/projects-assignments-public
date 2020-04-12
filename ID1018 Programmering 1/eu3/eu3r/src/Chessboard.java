public class Chessboard
{
    public static class Field
    {
        private char row;
        private byte column;
        private Chesspiece piece = null;
        private boolean marked = false;
        public Field (char row, byte column) {
            this.row = row;
            this.column = column;
        }
        public void put (Chesspiece piece) {   //"Lägger ut" pjäsen på spelplanen
            this.piece = piece;
        }
        public Chesspiece take () {         //Tar bort pjäsen från spelplanen
            Chesspiece save = this.piece;
            this.piece = null;
            return save;        //Returnerar pjäsen, ifall den ska användas för annat vid ett senare tillfälle.
        }
        public void mark () {      //Markerar de fält som skickats in från pjäsen.
            this.marked = true;
        }

        public void unmark () {        //Av markerar de fält som skickats in från pjäsen.
            this.marked = false;
        }
        public String toString ()       //Används för spelplanets "rutor" och skickar tillbaka tillhörande string. ex en pjäs, markerat fält eller tomt fält
        {
            String s = (marked)? "xx" : "--";
            return (piece == null)? s : piece.toString ();
        }
    }
    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int FIRST_ROW = 'a';
    public static final int FIRST_COLUMN = 1;
    private Field[][] fields;
    public Chessboard () //Skapar spelplanen och vektorn som används för att manipulera denne.
    {
        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char row = 0;
        byte column = 0;
        for (int r = 0; r < NUMBER_OF_ROWS; r++)
        {
            row = (char) (FIRST_ROW + r);
            column = FIRST_COLUMN;
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
            {
                fields[r][c] = new Field (row, column);
                column++;
            }
        }
    }

    public String toString () {
        String field = "\t";
        for(int i = 1; i<=NUMBER_OF_COLUMNS;i++)    //Skriver ut siffrorna i toppen av brädet
            field += (i + "   ");
        for (int j = 0; j < NUMBER_OF_ROWS; j++) {
            field += ("\n" + (char) ('a' + j));        //Skriver ut rad namnet

        field += "\t";      //Lägger in ett mellanrum mellan rad namnet till spelplanen
        for(int k = 0; k <NUMBER_OF_COLUMNS;k++)    //Går igenom varje kolumn i raden
        field += fields[j][k].toString() + "  ";        //Skriver antingen ut --,xx, eller en pjäs beroende på vad som finns på spelplanen
        }

        return field;
    }
    public boolean isValidField (char row, byte column) {       //Kollar så att inskickade kordinater finns på spelplanen
        boolean valid = false;
        if(row>= (char)(FIRST_ROW) && row<= ((char)((FIRST_ROW + NUMBER_OF_ROWS)-1))&& column<=NUMBER_OF_COLUMNS && column >= FIRST_COLUMN) //kollar ifall raden och kolumnen är inom intervallet
            valid = true;
        return valid;
    }
    public abstract class Chesspiece
    {
        private char color;
        // w - white, b - black
        private char name;
        // K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
        // P – Pawn
        protected char row = 0;
        protected byte column = -1;
        protected Chesspiece (char color, char name) {
            this.color = color;
            this.name = name;
        }
        public String toString ()
        {
            return "" + color + name;
        }
        public boolean isOnBoard () //Kollar ifall pjäsen är på spelplanen eller ej, mha isValidfield metoden
        {
            return Chessboard.this.isValidField (row, column);
        }
        public void moveTo (char row, byte column) throws NotValidFieldException //Flyttar en pjäs till en specifierad ruta
        {
            if (!Chessboard.this.isValidField (row, column)) {
                throw new NotValidFieldException("bad field: " + row + column); //Slänger en exception ifall en pjäs försökts flyttas till en ogiltig ruta
            }
            this.row = row;
            this.column = column;
            int r = row - FIRST_ROW;
            int c = column - FIRST_COLUMN;
            Chessboard.this.fields[r][c].put (this);    //Lägger ut pjäsen på en specifiserad ruta
            markReachableFields();
        }
         class NotValidFieldException extends Exception {       //undantagsklassen.
         private String exception;

            public NotValidFieldException (String exception)    //Tar emot en exception och sparar texten som tillhör denne
            {
                this.exception = exception;
            }
            public String getException(){
                return exception;
            }   //Skickar texten som sparades under en exception
        }
        public void moveOut () {    //Tar bort en pjäs från spelplanet
            this.unmarkReachableFields();       //Avmarkerar alla giltiga drag pjäsen kan göra
            int r = row - FIRST_ROW;
            int c = column - FIRST_COLUMN;
            Chesspiece save;
            if(Chessboard.this.isValidField(row,column))    //Kollar så att pjäsen faktiskt är på spelplanen
                save = Chessboard.this.fields[r][c].take(); //Tar bort pjäsen från spelplanet
        }
        public abstract void markReachableFields ();
        public abstract void unmarkReachableFields ();
    }
    public class Pawn extends Chesspiece
    {
        public Pawn (char color, char name)
        {
            super (color, name);
        }
        public void markReachableFields ()
        {
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col))
            {
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].mark ();
            }
        }
        public void unmarkReachableFields ()
        {
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col))
            {
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].unmark ();
            }
        }
    }
    public class Rook extends Chesspiece
    {
        public Rook (char color, char name)
        {
            super (color,name);
        }
        public void markReachableFields()
        {
                int r = row - FIRST_ROW;
                for(int i=0; i<NUMBER_OF_ROWS;i++)          //Markerar verikala möjligheter (Sidledes)
                    if(Chessboard.this.isValidField(row,(byte)(i+1)))
                        Chessboard.this.fields[r][i].mark();
                for(int i =0; i<NUMBER_OF_COLUMNS;i++)             //Markerar diagonala möjligheter
                    if(Chessboard.this.isValidField((char)(FIRST_ROW+i),column))
                    Chessboard.this.fields[i][column - 1].mark();
        }
        public void unmarkReachableFields()
        {
            int r = row - FIRST_ROW;
            for(int i=0; i<NUMBER_OF_ROWS;i++)          //avmarkerar verikala möjligheter
                if(Chessboard.this.isValidField(row,(byte)(i+1)))
                    Chessboard.this.fields[r][i].unmark();
            for(int i =0; i<NUMBER_OF_COLUMNS;i++)             //avmarkerar diagonala möjligheter
                if(Chessboard.this.isValidField((char)(FIRST_ROW+i),column))
                    Chessboard.this.fields[i][column - 1].unmark();
        }
    }
    public class Knight extends Chesspiece
    {
        public Knight (char color, char name)
        {
            super (color,name);
        }
        public void markReachableFields() {

             int r = row-FIRST_ROW;
             int c = column-FIRST_COLUMN;

            for(int i = 0; i<=1;i++) {
                if(Chessboard.this.isValidField((char)(row+2), (byte)(column-1+2*i)))   //markerar möjliga steg raktnedåt och åt sidan.
                    Chessboard.this.fields[r+2][c-1+2*i].mark();
                if(Chessboard.this.isValidField((char)(row-2),(byte)(column-1+2*i)))    //Markerar möjliga steg rakt uppåt och åt sidan.
                    Chessboard.this.fields[r-2][c-1+2*i].mark();
                if(Chessboard.this.isValidField((char)(row-1+2*i),(byte)(column-2)))    //Markerar möjliga drag i vänster riktning och åt sidan
                    Chessboard.this.fields[r-1+2*i][c-2].mark();
                if(Chessboard.this.isValidField((char)(row-1+2*i),(byte)(column+2)))  //Markerar möjliga drag i höger riktning och åt sidan.
                    Chessboard.this.fields[r-1+2*i][c+2].mark();
            }

        }
        public void unmarkReachableFields() {

            int r = row-FIRST_ROW;
            int c = column-FIRST_COLUMN;

            for(int i = 0; i<=1;i++) {
                if(Chessboard.this.isValidField((char)(row+2), (byte)(column-1+2*i)))   //avmarkerar möjliga steg raktnedåt och åt sidan.
                    Chessboard.this.fields[r+2][c-1+2*i].unmark();
                if(Chessboard.this.isValidField((char)(row-2),(byte)(column-1+2*i)))    //avmarkerar möjliga steg rakt uppåt och åt sidan.
                    Chessboard.this.fields[r-2][c-1+2*i].unmark();
                if(Chessboard.this.isValidField((char)((row-1+2*i)),(byte)(column-2)))    //avmarkerar möjliga drag i vänster riktning och åt sidan
                    Chessboard.this.fields[r-1+2*i][c-2].unmark();
                if(Chessboard.this.isValidField((char)(row-1+2*i),(byte)(column+2)))  //avmarkerar möjliga drag i höger riktning och åt sidan.
                    Chessboard.this.fields[r-1+2*i][c+2].unmark();
            }

        }
    }
    public class Bishop extends Chesspiece {
        public Bishop (char color, char name)
        {
            super (color,name);
        }
        public void markReachableFields() {
            int r = row-FIRST_ROW;
            int c = column-FIRST_COLUMN;
            for(int i = 0;i<NUMBER_OF_COLUMNS;i++)
            {
                if(Chessboard.this.isValidField((char)(row-i), (byte)(column-i)))  //markerar uppåt och åt vänster
                    Chessboard.this.fields[r-i][c-i].mark();

                if(Chessboard.this.isValidField((char)(row-i), (byte)(column+i)))  //upp höger
                    Chessboard.this.fields[r-i][c+i].mark();

                if(Chessboard.this.isValidField((char)(row+i), (byte)((column+i))))   //Ner höger
                    Chessboard.this.fields[r+i][c+i].mark();

                if(Chessboard.this.isValidField((char)(row+i), (byte)(column-i))) {    //ner vänster
                    Chessboard.this.fields[r + i][c - i].mark();
                }
            }
        }
        public void unmarkReachableFields() {
            int r = row-FIRST_ROW;
            int c = column-FIRST_COLUMN;
            for(int i = 0;i<NUMBER_OF_COLUMNS;i++)
            {
                if(Chessboard.this.isValidField((char)(row-i), (byte)(column-i)))  //markerar uppåt och åt vänster
                    Chessboard.this.fields[r-i][c-i].unmark();
                if(Chessboard.this.isValidField((char)(row-i), (byte)(column+i)))  //upp höger
                    Chessboard.this.fields[r-i][c+i].unmark();
                if(Chessboard.this.isValidField((char)(row+i), (byte)((column+i))))   //Ner höger
                    Chessboard.this.fields[r+i][c+i].unmark();
                if(Chessboard.this.isValidField((char)(row+i), (byte)(column-i)))    //ner vänster
                    Chessboard.this.fields[r+i][c-i].unmark();
            }
        }
    }
    public class Queen extends Chesspiece {
        public Queen (char color, char name)
        {
            super (color,name);
        }
        public void markReachableFields() {
            int r = row - FIRST_ROW;
            int c = column - FIRST_COLUMN;
            for(int i=0; i<NUMBER_OF_ROWS;i++)          //Markerar verikala möjligheter  (Sidledes)
                if(Chessboard.this.isValidField(row,(byte)(i+1)))
                    Chessboard.this.fields[r][i].mark();

            for(int i =0; i<NUMBER_OF_COLUMNS;i++) {             //Markerar diagonala möjligheter
                if (Chessboard.this.isValidField((char) (FIRST_ROW + i), column))
                    Chessboard.this.fields[i][column - 1].mark();
            }

            for(int i = 0;i<NUMBER_OF_COLUMNS;i++)
            {
                if(Chessboard.this.isValidField((char)((row-i)), (byte)(column-i)))  //markerar uppåt och åt vänster
                    Chessboard.this.fields[r-i][c-i].mark();
                if(Chessboard.this.isValidField((char)((row-i)), (byte)(column+i)))  //upp höger
                    Chessboard.this.fields[r-i][c+i].mark();
                if(Chessboard.this.isValidField((char)((row+i)), (byte)((column+i))))   //Ner höger
                    Chessboard.this.fields[r+i][c+i].mark();
                if(Chessboard.this.isValidField((char)((row+i)), (byte)(((column-i)))))    //ner vänster
                    Chessboard.this.fields[r+i][c-i].mark();
            }
        }
        public void unmarkReachableFields() {
            int r = row - FIRST_ROW;
            for(int i=0; i<NUMBER_OF_ROWS;i++)          //avmarkerar verikala möjligheter
                if(Chessboard.this.isValidField(row,(byte)(i+1)))
                    Chessboard.this.fields[r][i].unmark();
            for(int i =0; i<NUMBER_OF_COLUMNS;i++)             //avmarkerar diagonala möjligheter
                if(Chessboard.this.isValidField((char)(FIRST_ROW+i),column))
                    Chessboard.this.fields[i][column - 1].unmark();
            int c = column-FIRST_COLUMN;
            for(int i = 0;i<NUMBER_OF_COLUMNS;i++)
            {
                if(Chessboard.this.isValidField((char)((row-i)), (byte)(column-i)))  //markerar uppåt och åt vänster
                    Chessboard.this.fields[r-i][c-i].unmark();
                if(Chessboard.this.isValidField((char)((row-i)), (byte)(column+i)))  //upp höger
                    Chessboard.this.fields[r-i][c+i].unmark();
                if(Chessboard.this.isValidField((char)((row+i)), (byte)((column+i))))   //Ner höger
                    Chessboard.this.fields[r+i][c+i].unmark();
                if(Chessboard.this.isValidField((char)((row+i)), (byte)((column-i))))    //ner vänster
                    Chessboard.this.fields[r+i][c-i].unmark();
            }
        }
    }
    public class King extends Chesspiece {
        public King (char color, char name)
        {
            super (color,name);
        }
        public void markReachableFields() {
            int r = row - FIRST_ROW;
            int c = column-FIRST_COLUMN;
            for(int i =0; i<=1; i++)
                if(Chessboard.this.isValidField((char)(row-1+i*2), column))
                    Chessboard.this.fields[r-1+i*2][c].mark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField(row, (byte)(column-1+j*2)))
                    Chessboard.this.fields[r][c-1+j*2].mark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField((char)(row-1), (byte)(column-1+j*2)))
                    Chessboard.this.fields[r-1][c-1+j*2].mark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField((char)(row+1), (byte)(column-1+j*2)))
                    Chessboard.this.fields[r+1][c-1+j*2].mark();
        }
        public void unmarkReachableFields() {
            int r = row - FIRST_ROW;
            int c = column-FIRST_COLUMN;
            for(int i =0; i<=1; i++)
                if(Chessboard.this.isValidField((char)(row-1+i*2), column))
                    Chessboard.this.fields[r-1+i*2][c].unmark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField(row, (byte)(column-1+j*2)))
                    Chessboard.this.fields[r][c-1+j*2].unmark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField((char)(row-1), (byte)(column-1+j*2)))
                    Chessboard.this.fields[r-1][c-1+j*2].unmark();
            for(int j = 0; j <=1;j++)
                if(Chessboard.this.isValidField((char)(row+1), (byte)(column-1+j*2)))
                    Chessboard.this.fields[r+1][c-1+j*2].unmark();
        }
    }
}