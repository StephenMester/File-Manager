import java.util.ArrayList;

//interface to allow for writing to a file
public interface Writeable 
{
	//method to allow the user to completely write over a file
	public void overwrite(ArrayList<String> newLines);
	
	//method to allow user to overwrite a specific section of text
	public void overwriteSection(int startIndex, int endIndex, ArrayList<String> newWords);
	
	//method to overwrite lines in a file
	public void overwritelines(int startLine, int endLine, ArrayList<String> newLines);
	

	//method to add text to the end of a file
	public void append(ArrayList<String> newLines);
}
