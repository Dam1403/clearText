package functObjs;

public interface FunctObj{
	
	
	/*
	* The text changing portion of the obj
	* the operation is object object specific
	*
	*@param text the string that will be altered
	*@param alphabet the string containing all available characters for the transmutation if needed
	*@param Nonce an easy way to change the output of the function in a predictable manner to change the transmutation scheme
	*/
	public String transmute(String text, String alphabet, int Nonce);

        /*
        * used to undo the transmutation
        *
        */
    
	public String recover(String cText, String alphabet, int Nonce);
        
        /*
        *returns the name of the object 
        */
	public String getName();
	
	
	/*
	*returns a message for the usage of the object
	*/
	public String help();
	
}
