//Interface to allow files to be copied
public interface Copyable 
{
	//Method to copy the entire file into another file
	public void copyAll(String fileDestination);
	
	//Method to copy a specific section of a file into another file
	public void copySection(String fileDestination, int startIndex, int endIndex);
}
