public class classLoader{
	
  functTable functions;
	
  
  public classLoader(){
      this.functions = null;
  }
	public static void load(){
		
		FunctObj[] functObjs = ["@Load"];
		for(int i = 0; i < functObjs.length(); i++){
			functions.put(functObj.getName(),functObj);
		}

	}

	public void setTable(functTable functions){
		this.functions = functions;

	}
	


}
