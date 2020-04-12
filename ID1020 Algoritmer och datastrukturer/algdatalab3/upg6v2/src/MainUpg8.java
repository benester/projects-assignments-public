/**
 * @Auther Benjamin Gafvelin
 * this code takes a word as an input and finds all the indexes with this word, from "the text"
 * The input of the code is based on previous assignments
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainUpg8
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //Take care of input from user, and input of "the text"
        Scanner scanner = new Scanner(new File("C:\\Users\\benja\\Desktop\\algdatalab3\\upg6\\src\\out6.txt"));
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the word you wish to index");
        String searchWord = userInput.next().toLowerCase();
        String input = scanner.nextLine().toLowerCase();


        //Temporary string word, to compare to the user input
        String word = "";
        for(int i = 0; i < input.length();i++){
            if(input.charAt(i)!=' '){
                word+=input.charAt(i);
            }
            if(word.equals(searchWord) && input.charAt(i+1)==' '){
                int index = (i+2) - searchWord.length();
                System.out.println("The word \"" + searchWord + "\" can be found at index: " + index);
                word = "";
                continue;
            }
            //If the next char is a space, the word is over, and if it was not detected by the previous
            //if statement, then it was not the sought after word, therefore reset the temp String word
            if(input.charAt(i) == ' '){
                word = "";
            }
        }
    }
}