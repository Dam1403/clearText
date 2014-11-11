import java.io.File;

import java.lang.reflect.Constructor;

import functObjs.FunctObj;

public class refInit{

	public static functTable init(ClassLoader classLoader){


		Class aClass = null;
		Constructor constructor = null;
		String className = null;
		String[] fileName = null;


		functTable theTable = new functTable();
		File directory = new File("functObjs");
		File[] files = directory.listFiles();		
		//PROGRESS System.out.print("Progress:  ");
		for(int i = 0; i < files.length; i++){
			fileName = files[i].getName().split("\\.");
			className = fileName[0];

			//PROGRESS
			//int prog =(int)((i / (double)files.length) * 100);
			//String theBs = null;
			//if(prog < 10 ){theBs = "\b";}
			//else if (prog < 100){theBs = "\b\b";}
			//System.out.print("" + prog + theBs);
			if( 	fileName.length == 1||
				       	!fileName[1].equals("class") ||
				       	fileName[0].equals("FunctObj")){
				continue;
			}

			System.out.println("Loading " + className);
			try {
				aClass = classLoader.loadClass("functObjs."+className);
				constructor = aClass.getConstructor(new Class[0]);
				FunctObj obj = (FunctObj)constructor.newInstance();
				theTable.put(obj.getName(), obj);

			} catch (Exception e) {
				System.out.println("Proper exception catching will come after Testing");
				e.printStackTrace();
				return null;	
			}


		}
		//PROGRESS System.out.print("100\n");
		return theTable;
	}
}



















