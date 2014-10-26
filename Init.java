public static class Init{
	
	
	
	public static void main(String[] args){
		BufferedReader br = new BufferedReader
			(new FileReader("functObjs/classLoader.java"));
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
		
	        File Path = new File("functObjs");
	        List<String> files = list(Path);
	
	        for (String file : files) {
	            System.out.println(file);
        	}
    
		
		buffW.write("[");
		for(int i = 0; i < fileList.length(); i++ ){
			filename = fileList.get(i);
			if (filename.substring(0,3).equals("load")){
				String objName = .strip("."[0]).substring(iD.length() - 1)
				file.write("new " + objName + ".()")	
			}
			
		}
		buffW.write("]")
	}



}
