import java.util.ArrayList;

//Interface to ensure that classes allow the file to be read in multiple ways
public interface Readable 
{
	
	//Method to read the file in its entirety
	public ArrayList<String> readAll();
	//readAll for files with delimiters
	public ArrayList<String> readAll(char delimiter);
	
	//Method to read a file from one word to another
	public ArrayList<String> readSection(int startIndex, int endIndex);
	//readSection for files with delimiters
	public ArrayList<String> readSection(int startIndex, int endIndex, char delimiter);
	
	public char readChar(int wordIndex,int letterIndex);
}
