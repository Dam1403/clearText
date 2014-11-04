import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileNotFoundException;

import functObjs.FunctObj;


/*#REFACTOR FUNCTION CLASSS!!!!!!*/

public class clearText{

	private static HashMap<String,FunctObj> functObjs;
	private static String defAlphabet = "abcdefghijklmnopqrstuvwxyz";
	private static String userAlphabet = null;
	private static ArrayList<String> history = new ArrayList<String>();

	private static String text = null;


	public static void main(String[] args){


		functTable functObjs = refInit.init(clearText.class.getClassLoader());	
		String line = "";
		Scanner in = new Scanner(System.in);
		while(line.equals("EXIT")){
			line = in.nextLine().trim();
			System.out.println("LINE " + line);
			history.add(line);
			String[] arguments = line.split("\\s+");
			FunctObj fObj = functObjs.get(arguments[0]);
			String input = null;


			if(arguments[0].equals("settxt")){
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
				System.out.println("Text set to :" + text);
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
				System.out.println("Alphabet set to :" + userAlphabet);
			}
			else if(arguments[0].equals("history")){
				if(arguments.length == 1){
					history(0);
					continue;
				}	
				history(Integer.parseInt(arguments[1]));

			}

			else if(fObj != null){
				if(text == null){
					System.out.println("text Not Set");
					continue;
				}
				System.out.println(fObj.getName());
				text = fObj.transmute(text,defAlphabet,0);
				System.out.println(text);
				for(int i = 1; i<arguments.length;i++){
					fObj = functObjs.get(arguments[i]);
					text = fObj.transmute(text,defAlphabet,0);
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

}
