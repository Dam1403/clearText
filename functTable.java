import java.util.HashMap;
import functObjs.FunctObj;


public class functTable{



	private HashMap<String,FunctObj> functObjs;


	public functTable(){
		this.functObjs = new HashMap<String,FunctObj>();


	}	
	public void put(String key,FunctObj functObj){
		functObjs.put(key,functObj);


	}

	public FunctObj get(String key){
		return functObjs.get(key);

	}




}
