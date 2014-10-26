import parent.FunctObj;


public class loadCaesRght implements FunctObj{

	private String Name;


	
	public FunctObj(){
		String Name = "CaesarRight";
	}

	public String transmute(String text,String alphabet, int Nonce){
		String cText = "";
		for (int i = 0; i < text.length(); i++ ){
			char cHar = text.charAt(i);
			int cHarIndex = alphabet.indexOf(cHar) + 1;
			cText += alphabet.charAt(cHarIndex % alphabet.length())
			
		}
		return cText;
	
	}
	public String recover(String cText,String alphabet, int Nonce){
	
		String Text = "";
		for (int i = 0; i < cText.length(); i++ ){
			char cHar = cText.charAt(i);
			int cHarIndex = alphabet.indexOf(cHar) - 1;
			cText += alphabet.charAt(cHarIndex % alphabet.length())
			
		}
		return Text;
	}
	public String getName(){
		return this.Name;
	}
	
	public String help(){
		return "HAHAHA HELP";
	}



}	
