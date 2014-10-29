import java.io.File;

import java.lang.reflect.Constructor;

import functDefs.FunctObj;

public class refInit{

	public static functTable init(ClassLoader classLoader){


		Class aClass = null;
		Constructor constructor = null;
		String className = null;
		String[] fileName = null;


		functTable theTable = new functTable();
		File directory = new File("functObjs");
		File[] files = directory.listFiles();		
		
		for(int i = 0; i < files.length; i++){
			fileName = files[i].getName().split("\\.");
			className = fileName[0];
			if( 	fileName.length == 1||
				       	!fileName[1].equals("class") ||
				       	fileName[0].equals("FunctObj")){
				continue;
			}
				
			System.out.println(className);

			try {
				aClass = classLoader.loadClass("functObjs."+className);
				System.out.println("aClass.getName()="+aClass.getName());
				System.out.println("aClass.getConstructors()="+aClass.getConstructors());
				constructor = aClass.getConstructor(new Class[0]);
				FunctObj obj = (FunctObj)constructor.newInstance();
				theTable.put(obj.getName(), obj);

			} catch (Exception e) {
				System.out.println("Proper exception catching will come after Testing");
				e.printStackTrace();
				return null;	
			}


		}
		return theTable;
	}
}



















