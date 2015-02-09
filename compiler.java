import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import static java.nio.file.StandardCopyOption.*;

public class compiler{

	public static void initCompile(){

		String[] fileName = null;
		File directory = new File("functObjs");
		File[] files = directory.listFiles();
		Process proc;
		for(int i  = 0; i < files.length;i++){
			//REFACTOR fileName
			fileName = files[i].getName().split("\\.");
			String fullFname = files[i].getName();
			if(fileName.length == 1||
					fileName[0].equals("FunctObj")||
					fileName[0].equals("README")){
				continue;
			}
			//SERVER COULD DOWNLOAD ONLY JAVA FILES
			if(fileName[1].equals("java")){
				System.out.println("Compiling: "+fullFname);
				compile(fullFname);
			}

		}
		proc = null;
		files = null;

	}
	public static final void moveToJFol(String fileName){
		Path startLoc = Paths.get("functObjs/" + fileName);
		Path endLoc = Paths.get("javaFiles/" + fileName);
		try{
			Files.move(startLoc,endLoc,REPLACE_EXISTING);
			}
		catch(IOException e){
			System.out.println("IOERROR " + e.getMessage());
			return;
		}
		//MAKE ATOMIC?
		return;
		
	}

	public static final void compile(String filename){
		Process proc;

		try{proc = Runtime.getRuntime().exec("javac functObjs/" + filename);}

		catch(IOException e){
			System.out.println(e.getMessage());
			System.out.println("I fucked up");
			return;
		}
		try{proc.waitFor();}
		catch(InterruptedException e){
			System.out.println("Interrupted");
			return;
		}		
		moveToJFol(filename);	
		
		
		proc = null;
		filename = null;
	}

}
