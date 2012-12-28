import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class HtmlGenerator {
	

	public static void main(String[] args) throws Exception {
		String templatePath = "./reference-guide/single-page-template.html";
		String targetName = "single-page.html";
		if(args.length > 0){
			templatePath = args[0];
			if(args.length > 1){
				targetName = args[1];
			}
		}
		
		File templateFile = new File(templatePath);
		if( ! templateFile.isFile()){
			return;
		}
		
		log("begin. \nprocess file: "+templateFile.getAbsolutePath());
		String content = generateHtml(templateFile);
		if(content != null){
			File targetFile = new File(templateFile.getParent(), targetName);
			targetFile.delete();
			
			log("create: "+targetFile.getName());
			
			FileWriter fileWriter = new FileWriter(targetFile);

			fileWriter.write(content);
			fileWriter.close();
		}
		log("end");

		
		
	}
	
	
	private static String generateHtml(File templateFile) {
		try {
			return tryGenerateHtml(templateFile);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private static String tryGenerateHtml(File file) throws Exception {
		
		StringBuilder content = new StringBuilder();
		
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line = null;
		while((line = br.readLine())!= null){
			
			String normalStr = line.toLowerCase();
			
			if(normalStr.contains("<body>")){
				line += "\n"+"<span class='disablePreprocessingFlag'></span>";
				appendLine(content, line);
				continue;
			}
			
			if(normalStr.contains("importblock") && normalStr.contains("p-url")){
				String importedContent = importContent(file, line);
				line = insertContentToBlock(line, importedContent);
				appendLine(content, line);
				continue;
			}
			
			appendLine(content, line);
			
		}
		fileReader.close();
		
		String out = content.toString();
		return out;
	}


	private static String importContent(File file, String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String insertContentToBlock(String line, String importedContent) {
		// TODO Auto-generated method stub
		return null;
	}


	private static void appendLine(StringBuilder sb, String line) {
		sb.append(line).append('\n');
	}

	private static void log(String msg){
		System.out.println(msg);
	}

}
