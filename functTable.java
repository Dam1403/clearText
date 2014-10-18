import java.util.HashMap


public static class functTable{

	
	
	private HashMap<String,FunctObj> functObjs;



	public functTable{
		functObjs = new HashMap<String,FunctObj>();
		
	}	
	public void put(String key,FunctObj functObj){
		functObjs.put(key,functObj);

	}

	public FunctObj get(String key){
		functObjs.get(key);

	}




}
