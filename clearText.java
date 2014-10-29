import java.util.Scanner;
import java.util.HashMap;

import functObjs.FunctObj;


/*#REFACTOR FUNCTION CLASSS!!!!!!*/

public class clearText{
	HashMap<String,FunctObj> functObjs;


	public static void main(String[] args){

		
		functTable functObjs = refInit.init(clearText.class.getClassLoader());	
		String line = "";
		Scanner in = new Scanner(System.in);
		while(line != "EXIT"){
			line = in.next();
			System.out.println(line);

		}
	}

}
