public interface FunctObj{
	

	private String Name;

	public FunctObj();

	public String transmute(String text, String alphabet, int Nonce);

	public String recover(String cText, String alphabet, int Nonce);

	public String getName();
	
	public String help();
	
}
