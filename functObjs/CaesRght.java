package functObjs;

public class CaesRght implements FunctObj{


	public String transmute(String text,String alphabet, int Nonce){
		String cText = "";
		text = text.toLowerCase();
		for (int i = 0; i < text.length(); i++ ){
			char cHar = text.charAt(i);
			if(alphabet.indexOf(cHar) == -1){
				cText += cHar;
				continue;	
			}
			int cHarIndex = alphabet.indexOf(cHar) + 1;
			cText += alphabet.charAt(cHarIndex % alphabet.length());
				
		}
		return cText;
	
	}
	public String recover(String cText,String alphabet, int Nonce){
	
		String Text = "";
		for (int i = 0; i < cText.length(); i++ ){
			char cHar = cText.charAt(i);
			if(alphabet.indexOf(cHar) == -1){
				cText += cHar;
				continue;	
			}
			int cHarIndex = alphabet.indexOf(cHar) - 1;
			cText += alphabet.charAt(cHarIndex % alphabet.length());
			
		}
		return Text;
	}
	public String getName(){
		return "CaesarRight";
	}
	
	public String help(){
		return "HAHAHA HELP";
	}



}	
