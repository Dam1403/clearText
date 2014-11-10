import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileNotFoundException;

import functObjs.FunctObj;
//YOU WERE TESTING QUOTES

/*#REFACTOR FUNCTION CLASSS!!!!!!*/

public class clearText{

	private static HashMap<String,FunctObj> functObjs;
	private static String userAlphabet = null;

	private static String userQuote = "\"";
	private static ArrayList<String> history = new ArrayList<String>();

	private static String text = null;


	public static void main(String[] args){


		functTable functObjs = refInit.init(clearText.class.getClassLoader());	
		String line = "";
		Scanner in = new Scanner(System.in);
		while(!line.equals("EXIT")){
			System.out.print(">");
			line = in.nextLine().trim();
			if(!line.equals("history")){history.add(line);}
			String[] arguments = lineHandler(line);
			FunctObj fObj = functObjs.get(arguments[0]);
			String input = null;
	

			if(arguments[0].equals("settext")){
				if(arguments.length == 2){
					text = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					System.out.println("Invalid argument Length");
					continue;
				}
				else{
					text = set(arguments[1],arguments[2].equals("-f"));
				}
				System.out.println("Text set to " + text);
			}


			else if(arguments[0].equals("setalpha")){
				
				if(arguments.length == 2){
					userAlphabet = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					System.out.println("Invalid argument Length");
					continue;
				}
				else{
					userAlphabet = set(arguments[1],arguments[2].equals("-f"));
				}
				System.out.println("Alphabet set to " + userAlphabet);
			}
			//FIND BETTER WAY TO HANDLE THESE SETS
			else if(arguments[0].equals("setquote")){
				
				if(arguments.length == 2){
					userQuote = set(arguments[1],false);
				}
				else if(arguments.length == 1 || arguments.length > 3 ){
					System.out.println("Invalid argument Length");
					continue;
				}
				else{
					userQuote = set(arguments[1],arguments[2].equals("-f"));
				}
				System.out.println("quote set to " + userQuote);
			}
			else if(arguments[0].equals("history")){
				if(arguments.length == 1){
					history(0);
					continue;
				}	
				history(Integer.parseInt(arguments[1]));

			}
			
			else if(arguments[0].equals("help")){
				
				if(arguments.length == 2){
					dispTable(functObjs,arguments[1]);
				}

				System.out.println("Command: settxt");
				System.out.println("\tusage: settxt [your text] ");

				System.out.println("\tusage: settxt [your text filename] -f \n");
				System.out.println("Command: setalpha");
				System.out.println("\tusage: setalpha [String your alphabet] ");
				System.out.println("\tusage: setalpha [alphabet filename]  -fn\n");
				System.out.println("Command: setquote");
				System.out.println("\tusage: setquote [String your quote] ");
				System.out.println("\tusage: setquote [quote filename]  -fn\n");
				System.out.println("\tDefaults to \" set to a palindrome\n");

				System.out.println("Command: history");
				System.out.println("\tusage: history");
				System.out.println("\tusage: history [Number to display]\n");
				System.out.println("Command: help");
				System.out.println("\tusage: help");
				System.out.println("\tusage: help [FunctObjName]\n");
				dispTable(functObjs, null);





			}
			else if(fObj != null){
				if(text == null){
					System.out.println("text Not Set");
					continue;
				}
				System.out.println(fObj.getName());
				text = fObj.transmute(text,text,0);
				System.out.println(text);
				for(int i = 1; i<arguments.length;i++){
					fObj = functObjs.get(arguments[i]);
					text = fObj.transmute(text,userAlphabet,0);
					System.out.println(fObj.getName());
					System.out.println(text);
				}
			}
			

		}
	}

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

	private static void history(int histNum){
		if(histNum == 0){
			histNum = history.size();
		}
		for(int i = 0; i < history.size() && i < histNum;i++){
			System.out.println(i+"."+history.get(i));
		}
	}



	private static void dispTable(functTable table, String objName){
		if(objName != null){

			if(objName.equals("help")){
				System.out.println("PARADOX DETECTED");
				return;
			}

			FunctObj help = table.get(objName);
			if(help == null){
				System.out.println("No Such Function");
				return;
			}
			String sHelp = help.help();
			System.out.println("Function " + objName+":");
			System.out.println(sHelp);
			return;
		}
		ArrayList<String> keys = new ArrayList<String>(table.keys());
		ArrayList<FunctObj> vals = new ArrayList<FunctObj>(table.values());
		for(int i = 0; i < keys.size(); i++){
			System.out.println("Function "+keys.get(i));
			System.out.println(vals.get(i).help());
		}
	}

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
					i += userQuote.length();
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

}
