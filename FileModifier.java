import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * Class to take an existing file and read/write/copy/delete that file through a series of functions
 */


public class FileModifier implements Readable
{

	private String extension = "";
	private String filename = "";
	static ArrayList<String> list1 = new ArrayList<String>();
	
	
	//Constructor to set variables and to get file extension
	public FileModifier(String filename)
	{
		
		//initialises filename variable
		this.filename = filename;
		
		//Finds file extension by finding the first instance of '.' then creating a substring of everything after
		int index = filename.lastIndexOf(".");
		
		if(index>0)
		{
			
			//initialises extension variable
			extension = filename.substring(index+1);
			
		}
	}
	
	public ArrayList<String> readAll()
	{
		
		ArrayList<String> output = new ArrayList<String>();
		
		//try catch to open file called filename then read all the contents of the file
		try
		{	
			File file = new File(filename);
			
			if (file.exists())
			{	
				//Creating buffered reader
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				//Creates String variable to store each line
				String line;
				
				//While loop to iterate through every line and add to output ArrayList
				while ((line = reader.readLine())!= null)
				{
					//For loop to get each word in a line
					for (int i=0;i<line.length();i++)
					{
						//Finds the first occurance of a space character from i
						int space = line.indexOf('\s',i);
						
						//if to add each subsequent word into output except last word
						if (space!=-1)
						{
							String word = line.substring(i,space);
							output.add(word);
							
							//i is set to the  last occurance of space in order to find the next
							i=space;
						}
						
						//else to add last word and to add \n to signify next line in arraylist
						else
						{
							String word = line.substring(i);
							output.add(word);
							output.add("\n");
							i=line.length();
						}
					}
				}
				
				//Closes reader after going through file 
				reader.close();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public ArrayList<String> readAll(char delimiter)
	{
		ArrayList<String> output = new ArrayList<String>();
		
		//try catch to open file called filename then read all the contents of the file
		try
		{	
			File file = new File(filename);
			
			if (file.exists())
			{	
				//Creating buffered reader
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				//Creates String variable to store each line
				String line;
				
				//While loop to iterate through every line and add to output ArrayList
				while ((line = reader.readLine())!= null)
				{
					//For loop to get each word in a line
					for (int i=0;i<line.length();i++)
					{
						
						//Finds the first occurance of the delimiter from i
						int delim = line.indexOf(delimiter,i);
						
						//if to add each subsequent word into output except last word
						if (delim!=-1)
						{
							String word = line.substring(i,delim);
							output.add(word);
							
							//i is set to the  last occurance of the delimiter in order to find the next
							i=delim;
						}
						
						//else to add last word and to add \n to signify next line in arraylist
						else
						{
							String word = line.substring(i);
							output.add(word);
							output.add("\n");
							i=line.length();
						}
					}
				}
				
				//Closes reader after going through file 
				reader.close();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}

	public ArrayList<String> readSection(int startIndex, int endIndex) 
	{
		ArrayList<String> output = new ArrayList<String>();
		int wordCount = 0;
		
		//try catch to open file called filename then read all the contents of the file
		try
		{	
			File file = new File(filename);
			
			if (file.exists())
			{	
				//Creating buffered reader
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				//Creates String variable to store each line
				String line;
				
				//While loop to iterate through every line and add to output ArrayList
				while ((line = reader.readLine())!= null && wordCount <= endIndex)
				{
					//For loop to get each word in a line
					for (int i=0;i<line.length();i++)
					{
						//Finds the first occurance of a space character from i
						int space = line.indexOf('\s',i);
						
						//if to add each subsequent word into output except last word
						if (space!=-1)
						{
							String word = line.substring(i,space);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount >= startIndex && wordCount <= endIndex)
								output.add(word);
							wordCount+=1;
							
							//i is set to the  last occurance of space in order to find the next
							i=space;
						}
						
						//else to add last word and to add \n to signify next line in arraylist
						else
						{
							String word = line.substring(i);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount >= startIndex && wordCount <= endIndex)
							{
								output.add(word);
								output.add("\n");
							}
							wordCount+=1;
							
							i=line.length();
						}
					}
				}
				
				//Closes reader after going through file 
				reader.close();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}

	public ArrayList<String> readSection(int startIndex, int endIndex, char delimiter) 
	{
		ArrayList<String> output = new ArrayList<String>();
		int wordCount = 0;
		
		//try catch to open file called filename then read all the contents of the file
		try
		{	
			File file = new File(filename);
			
			if (file.exists())
			{	
				//Creating buffered reader
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				//Creates String variable to store each line
				String line;
				
				//While loop to iterate through every line and add to output ArrayList
				while ((line = reader.readLine())!= null && wordCount <= endIndex)
				{
					//For loop to get each word in a line
					for (int i=0;i<line.length();i++)
					{
						//Finds the first occurance of a space character from i
						int delim = line.indexOf(delimiter,i);
						
						//if to add each subsequent word into output except last word
						if (delim!=-1)
						{
							String word = line.substring(i,delim);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount >= startIndex && wordCount <= endIndex)
								output.add(word);
							wordCount+=1;
							
							//i is set to the  last occurance of the delimiter in order to find the next
							i=delim;
						}
						
						//else to add last word and to add \n to signify next line in arraylist
						else
						{
							String word = line.substring(i);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount >= startIndex && wordCount <= endIndex)
							{
								output.add(word);
								output.add("\n");
							}
							wordCount+=1;
							
							i=line.length();
						}
					}
				}
				
				//Closes reader after going through file 
				reader.close();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
	
	public char readChar(int wordIndex,int letterIndex) 
	{
		
		int wordCount = 0;
		
		//try catch to open file called filename then read all the contents of the file
		try
		{	
			File file = new File(filename);
			
			if (file.exists())
			{	
				//Creating buffered reader
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				//Creates String variable to store each line
				String line;
				
				//While loop to iterate through every line and add to output ArrayList
				while ((line = reader.readLine())!= null && wordCount != wordIndex)
				{
					//For loop to get each word in a line
					for (int i=0;i<line.length();i++)
					{
						//Finds the first occurance of a space character from i
						int space = line.indexOf('\s',i);
						
						//if to add each subsequent word into output except last word
						if (space!=-1)
						{
							String word = line.substring(i,space);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount == wordIndex)
								try
								{
									return word.charAt(letterIndex);
								}
								catch (Exception e)
								{
									System.out.println("Character at index "+letterIndex+" Does not exist");
									return ' ';
								}
							wordCount+=1;
							
							//i is set to the  last occurance of space in order to find the next
							i=space;
						}
						
						//else to add last word and to add \n to signify next line in arraylist
						else
						{
							String word = line.substring(i);
							
							//if statment to ensure that only desired words are added into output
							if (wordCount == wordIndex)
								return word.charAt(letterIndex);
							wordCount+=1;
							
							i=line.length();
						}
					}
				}
				
				//Closes reader after going through file 
				reader.close();
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Character at index "+letterIndex+" Does not exist");
		return ' ';
	}
	
	public static void main(String args[])
	{
		FileModifier a = new FileModifier("test.txt");
		
		list1=a.readSection(0,5,',');
		System.out.println(a.readChar(5, 9));
		System.out.println(list1);
	}


	
}
