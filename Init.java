public static class Init{
	
	
	
	public static void main(String[] args){
		BufferedReader br = new BufferedReader
			(new FileReader("classLoader.java"));
		File file = new File("Loaded.java");
 
		if (!file.exists()) {
				file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
			
		String line;
		while ((line = br.readLine()) != null) {
			if(line.contains('\"')){
				String[] parts = line.split("\"");

			}
			else{
				bw.write(line);
			}			
		}
		br.close();
	}
	private void writeObjects(
			BufferedWriter buffW,
			{
		String iD = "load";
		File cwd = new File(System.getProperty("user.dir"));
		String[] fileList = cwd.listFiles();
		
		buffW.write("[");
		for(int i = 0; i < fileList.length(); i++ ){
			String objName = fileList.strip("."[0]).substring(iD.length() - 1)
			file.write("new " + objName + ".()")
		}
		buffW.write("]")
	}



}
