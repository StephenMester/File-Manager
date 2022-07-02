import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/*
 * Class to take an existing file and read/write/copy/delete that file through a series of functions
 */


public class FileModifier implements Readable, Writeable, Copyable, Deleteable
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
		
		//Error message if index is not found
		System.out.println("Character at index "+letterIndex+" Does not exist");
		return ' ';
	}
	
	public void overwrite(ArrayList<String> newLines) 
	{
		//Try catch to ensure file exists to overwrite
		try 
		{
			PrintWriter writer = new PrintWriter(filename);
			
			//For loop to go through every index in the newLines array to write into the file
			for (int i = 0; i < newLines.size(); i++)
			{
				writer.println(newLines.get(i));
			}
			
			//Closes the file to avoid memory leak
			writer.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}


	public void overwriteSection(int startIndex, int endIndex, ArrayList<String> newWords) 
	{
		try 
		{
			//Arraylist to store all words into list attribute
			ArrayList<String> list = this.readAll();
			PrintWriter writer = new PrintWriter(filename);
			
			//Counter to act as a index for the newWords array to replace the old words in the file
			int counter = 0;
			
			//Forloop to cycle through the entire set of words and write the desired words into the file
			for (int i = 0; i < list.size(); i++)
			{
				
				//Sets all items in list to have a leading space to allow for correct writing
				list.set(i, list.get(i)+'\s');
				
				//if statment to replace desired words with the items in newWords
				if(i>=startIndex && i<=endIndex)
				{
					//sets leading space for new word
					list.set(i, newWords.get(counter)+'\s');
					
					//increments counter for next occurance of newWords
					counter+=1;
				}
				
				//gets empty line and changes writer to the next line if found
				if(list.get(i).equals(" "))
				{
					writer.println(list.get(i));
				}
				else
				{
					//writes the index i in list into the file
					writer.write(list.get(i));
				}
			}
			
			//closes writer to avoid memory leak
			writer.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public void overwritelines(int startLine, int endLine, ArrayList<String> newLines)
	{
		try
		{
			//Calls file and reader objects to read through the file
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			//Arraylists to hold the lines before the lines desired to be replaced
			ArrayList<String> before = new ArrayList<String>();
			
			//Arralist to hold the lines after the lines desired to be replaced
			ArrayList<String> after = new ArrayList<String>();
			
			//Arraylist to hold all the lines to be written including the new lines
			ArrayList<String> full = new ArrayList<String>();
			
			//placeholder attribute to store each line in a file
			String line;
			
			//index to cycle through each newLine
			int newlineIndex = 0;
			
			//attribute to keep track of line number
			int lineNum = 0;
			
			//while loop to cycle through each line in the file
			while((line = reader.readLine())!=null)
			{
				//if statement to find all lines before the desired lines to be changed
				if(lineNum<startLine)
				{
					before.add(line);
				}
				
				//if statement to find all the lines after the lines desired to be changed
				else if (lineNum>endLine)
				{
					after.add(line);
				}
				
				//iterates lineNum
				lineNum += 1;
			}
			
			//Adds all lines including the new lines into the full array
			full.addAll(before);
			full.addAll(newLines);
			full.addAll(after);
			
			//calls writer object which overwrites old file
			PrintWriter writer = new PrintWriter(filename);
			
			//for loop to cycle through full array and add all items into the file
			for(int i = 0; i < full.size(); i++)
			{
				writer.println(full.get(i));
			}
			
			//closes writer
			writer.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void append(ArrayList<String> newLines) 
	{
		
		//Try to ensure file exists
		try 
		{
			//Writer objects
			FileWriter writer = new FileWriter(filename, true);
			BufferedWriter Bwriter = new BufferedWriter(writer);
			PrintWriter Pwriter = new PrintWriter(Bwriter);
			
			//For loop to cycle through all new lines and append into the file
			for (int i = 0; i < newLines.size(); i++)
			{
				Pwriter.append(newLines.get(i));
			}
			
			//closes all objects
			Pwriter.close();
			Bwriter.close();
			writer.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void copyAll(String fileDestination) 
	{
		
		try 
		{
			  //Attributes to store source and destination files
			  File file = new File(filename);
		      File newFile = new File(fileDestination);
		      
		      //if statement to display if a new file has been created and get file path
		      if (newFile.createNewFile()) 
		      {
		        System.out.println("File created: " + newFile.getName());
		        System.out.println(newFile.getAbsolutePath());
		      } 
		      //else to display if file file already exists and get its path
		      else 
		      {
		        System.out.println("File already exists.");
		        System.out.println(newFile.getAbsolutePath());
		      }
		      
		      //Attribute to hold file path of source and destination file path
		      String filePath = file.getAbsolutePath();
		      String newFilePath = newFile.getAbsolutePath();
		      
		      //File channels to hold source and destination path to allow copying
		      @SuppressWarnings("resource")
		      FileChannel src = new FileInputStream(filePath).getChannel();
		      @SuppressWarnings("resource")
		      FileChannel dest = new FileOutputStream(newFilePath).getChannel();
		      
		      //Function to copy from source file to destination file
		      dest.transferFrom(src, 0, src.size());
		      
		      //closes source and destination file
		      src.close();
		      dest.close();
		      
		      
		 } 
		
		//Catch to get any unexpected errors
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}

	}


	public void copySection(String fileDestination, int startIndex, int endIndex) {
		try 
		{
			  //Attributes to store source and destination paths and to ensure both files are created or that they already exist
			  File file = new File(filename);
		      File newFile = new File(fileDestination);
		      
		      //if statement to display if a new file has been created and get file path
		      if (newFile.createNewFile()) 
		      {
		        System.out.println("File created: " + newFile.getName());
		        System.out.println(newFile.getAbsolutePath());
		      } 
		      //else to display if file file already exists and get its path
		      else 
		      {
		        System.out.println("File already exists.");
		        System.out.println(newFile.getAbsolutePath());
		      }
		      
		      //Attribute to hold file path of source and destination file path
		      String filePath = file.getAbsolutePath();
		      String newFilePath = newFile.getAbsolutePath();
		      
		      //File channels to hold source and destination path to allow copying
		      @SuppressWarnings("resource")
		      FileChannel src = new FileInputStream(filePath).getChannel();
		      @SuppressWarnings("resource")
		      FileChannel dest = new FileOutputStream(newFilePath).getChannel();
		      
		      //Calculates the difference in the index to get the size of the message to be copied
		      int difference = endIndex - startIndex;
		      
		      //Function to copy from source file to destination file
		      dest.transferFrom(src, startIndex, difference);
		      
		      //closes source and destination file
		      src.close();
		      dest.close();
		          
		 } 
		//Catch to get any unexpected errors
		catch (IOException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
	}
	
	public void delete() 
	{
		//Creates file object to allow the file to be deleted
		File file = new File(filename);
		
		//If statement to check if file is  and prints message if file can be or cannot be deleted
		if(file.delete())
		{
			System.out.println("File "+filename+" deleted");
		}
		else
		{
			System.out.println("File "+filename+" could not be deleted");
		}
		
	}

	public static void main(String args[])
	{
		FileModifier a = new FileModifier("test.txt");
		ArrayList<String> arr = new ArrayList<>();
		ArrayList<String> newLines = new ArrayList<>();
		ArrayList<String> newLines2 = new ArrayList<>();
		newLines.add("one one2 one3 one4");
		newLines.add("two two2 two3 two4");
		newLines.add("three three2 three3 three4");
		newLines2.add("test line");
		newLines2.add("test line2");
		arr.add("test");
		arr.add("test2");
		arr.add("test3");
		
		a.overwrite(newLines);

		a.append(newLines2);
		list1=a.readAll();
		//System.out.println(a.readChar(5, 9));
		System.out.println(list1);
		
		a.copyAll("newFile3.txt");
		a.copySection("newFile4.txt", 0, 4);
		a.delete();

		
		
	}

	
}
