import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ContinueFileParser {
	File f;
	private int difficulty;
	private int[][] boardArray;
	
	ContinueFileParser (File f) {
		this.f = f;
		boardArray = new int[9][9];
	}
	
	void Parse () throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("save.txt"));
		difficulty = in.nextInt();
		for (int row = 8; row >=0; row--) {
			for (int col = 0; col < 9; col++) {
				boardArray[col][row] = in.nextInt();
			}
		}
		in.close();
	}
	
	int getDifficulty () {
		return difficulty;
	}
	
	int[][] getBoardArray () {
		return boardArray;
	}
}