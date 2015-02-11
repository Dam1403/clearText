import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileNotFoundException;

import static java.lang.System.out;

import functObjs.FunctObj;

/*#REFACTOR FUNCTION CLASSS!!!!!!*/

public class clearText{

	private static HashMap<String,FunctObj> functObjs;
	private static String userAlphabet = "abcdefghijklmnopqrstuvwxyz";

	private static String userQuote = "\"";
	private static ArrayList<String> history = new ArrayList<String>();

	private static String text = null;



	/**
	 * The Core of clearText this simple shell gives the user access to the 
	 * various ciphers contained in the functObj folder
	 * 
	 * functObjs - the hashtable of function objects
	 *
	 * userAlphabet - Some ciphers need an alphabet to run 
	 * 	corectly such as the simple caesar cipher 
	 * 	alphabet Example| a CaesRight over the normal english alphabet 
	 * 		performed on "cat" would return "dbu"
	 * userQuote - If your copying and pasting Text into the shell
	 * 	using syntax settext "[blog paragraph]" any double quotes in the
	 * 	text would cut the argument short to solve this I allow the user
	 * 	to set their own custom palindrome quote string 
	 * 	or character the user just has to make sure the chosen text 
	 * 	doesnt contain chosen quote 
	 *
	 * history - A history of previously entered commands
	 *
	 * text - The Text the user wants encryted or decrypted
	 *
	 * @param args currently useless
	 */
	public static void main(String[] args){


		out.println("clearText Version 0.5");


		out.println("Compiling JavaFiles: ");
		compiler.initCompile();
		out.println("Done.");
		out.println("Loading Classes: ");
		//Show Progress!!!
		functTable functObjs = refInit.init(clearText.class.getClassLoader());	
		out.println("Done.");
		out.println("Ready. use \"help\" for help");
		String line = "";
		Scanner in = new Scanner(System.in);
		while(!line.equals("EXIT")){
			System.out.print(">");
			line = in.nextLine().trim();

			if(!line.equals("history")){history.add(line);}

			String[] arguments = lineHandler(line);
			FunctObj fObj = functObjs.get(arguments[0]);
			String input = null;

			if(arguments[0].equals("text")){
				out.println("Text is currently:\n");
				out.println(text + "\n");
				continue;
			}	
			if(arguments[0].equals("alphabet")){
				out.println("Alphabet is currently:\n");
				out.println(userAlphabet + "\n");
				continue;
			}	
			if(arguments[0].equals("settext")){
				if(arguments.length == 2){
					text = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					out.println("Invalid argument Length");
					continue;
				}
				else{
					text = set(arguments[1],arguments[2].equals("-f"));
				}
				out.println("Text set to " + text);
				continue;
			}


			else if(arguments[0].equals("setalpha")){

				if(arguments.length == 2){
					userAlphabet = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					out.println("Invalid argument Length");
					continue;
				}
				else{
					userAlphabet = set(arguments[1],arguments[2].equals("-f"));
				}
				out.println("Alphabet set to " + userAlphabet);
				continue;
			}
			//FIND BETTER WAY TO HANDLE THESE SETS
			else if(arguments[0].equals("setquote")){

				if(arguments.length == 2){
					userQuote = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					out.println("Invalid argument Length");
					continue;
				}
				else{
					userQuote = set(arguments[1],arguments[2].equals("-f"));
				}
				out.println("quote set to " + userQuote);
				continue;
			}
			//WRITE STATE TO FILE
			else if(arguments[0].equals("state")){
				//VIEW
				//LOAD
				//SAVE	
			}
			else if(arguments[0].equals("history")){
				if(arguments.length == 1){
					history(0);
					continue;
				}	
				history(Integer.parseInt(arguments[1]));
				continue;

			}

			else if(arguments[0].equals("reCompile")){
				compiler.initCompile();	
				continue;

			}

			else if(arguments[0].equals("help")){

				helpMessage();
				if(arguments.length == 2){
					dispTable(functObjs,arguments[1]);
					continue;
				}

			}
			else if(fObj != null){
				if(text == null){
					out.println("text Not Set");
					continue;
				}
				out.println(fObj.getName());
				text = fObj.transmute(text,userAlphabet,0);
				out.println(text);
				for(int i = 1; i<arguments.length;i++){
					fObj = functObjs.get(arguments[i]);
					text = fObj.transmute(text,userAlphabet,0);
					out.println(fObj.getName());
					out.println(text);
				}
				continue;
			}
			history.remove(history.size() - 1);

		}
	}
	/**
	 * This function is used to set the text, and alphabet used 
	 * during the transmution process
	 * It reads the text from a file or from the commandline
	 *
	 * @param theText Either a filename or command line text 
	 * @param isFilename true or false if the user wants the text from a file
	 *
	 */
	private static String set(String theText, boolean isFilename){
		String newText = "";
		if(isFilename){
			Scanner file = null;
			try{file = new Scanner(new FileReader(theText.trim()));}
			catch(FileNotFoundException e){
				return "FileName " + theText + "Does Not Exist";
			}
			while(file.hasNext()){
				newText += file.next();
			}
			theText = newText;
		}
		return theText;

	}
	/**
	 * prints the last arguments entered into the command line
	 *
	 * @param histNum controls how many entries to print from the history 
	 *
	 */
	private static void history(int histNum){
		if(histNum == 0){
			histNum = history.size();
		}
		for(int i = 0; i < history.size() && i < histNum;i++){
			out.println(i+"."+history.get(i));
		}
	}


	/**
	 * Returns the help function for a specific Object
	 * 
	 * @param table The table containing the functions
	 * @param objName The name of the object 
	 *
	 */
	private static void dispTable(functTable table, String objName){
		if(objName != null){

			if(objName.equals("help")){
				out.println("PARADOX DETECTED");
				return;
			}

			FunctObj help = table.get(objName);
			if(help == null){
				out.println("No Such Function");
				return;
			}
			String sHelp = help.help();
			out.println("Function " + objName+":");
			out.println(sHelp);
			return;
		}
		ArrayList<String> keys = new ArrayList<String>(table.keys());
		ArrayList<FunctObj> vals = new ArrayList<FunctObj>(table.values());
		for(int i = 0; i < keys.size(); i++){
			out.println("Function "+keys.get(i));
			out.println(vals.get(i).help());
		}
	}

	/** Handles the line recieved from the user
	 * It splits the line by space and groups strings together based on
	 * the user specified quote pattern userQuote
	 *
	 * @param line The line from standard input
	 */
	private static String[] lineHandler(String line){
		ArrayList<String> arguments = new ArrayList<String>();
		String currString = "";
		char c = 0;

		boolean quote = false;

		for(int i = 0; i < line.length();i++){
			c = line.charAt(i);

			if(c == ' ' || i == line.length()){
				if(quote){

					currString += c;
					continue;
				}
				arguments.add(currString);
				currString = "";		
			}
			//MAKE USER SET!!!
			//May be able to be done with subString
			else if (c == userQuote.charAt(0)){
				int potInd = i + userQuote.length();
				
				if(userQuote.equals(line.substring(i,potInd))){
					quote = !quote;
					i += userQuote.length() - 1;
				}
			}

			else{
				currString += c;

			}
		}
		arguments.add(currString);
		String[] array = new String[arguments.size()];
		array = arguments.toArray(array);
		return array;
	}
	private static void helpMessage(){

		out.println("Command: text");
		out.println("\tusage: text");

		out.println("Command: alphabet");
		out.println("\tusage: alphabet");

		out.println("Command: settxt");
		out.println("\tusage: settxt [your text] ");

		out.println("\tusage: settxt [your text filename] -f \n");
		out.println("Command: setalpha");
		out.println("\tusage: setalpha [String your alphabet] ");
		out.println("\tusage: setalpha [alphabet filename]  -fn\n");
		out.println("Command: setquote");
		out.println("\tusage: setquote [String your quote] ");
		out.println("\tusage: setquote [quote filename]  -fn\n");
		out.println("\tDefaults to \". set to a palindrome\n");

		out.println("Command: history");
		out.println("\tusage: history");
		out.println("\tusage: history [Number to display]\n");
		
		out.println("Command: reCompile");
		out.println("\tusage: reCompile\n");

		out.println("Command: help");
		out.println("\tusage: help");
		out.println("\tusage: help [FunctObjName]\n");


	}

}
