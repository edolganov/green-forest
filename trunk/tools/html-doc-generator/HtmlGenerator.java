import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


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


	private static String importContent(File file, String line) throws IOException {
		String urlParam = "p-url=";
		int index = line.indexOf(urlParam);
		if(index < 0){
			return "";
		}
		
		int beginIndex = urlParam.length()+index+1;
		int endIndex = line.indexOf("\"", beginIndex);
		if(endIndex < 0){
			return "";
		}
		
		String url = line.substring(beginIndex, endIndex);
		String parentPath = file.getParentFile().getPath();
		parentPath = parentPath.replace("\\", "/");
		if( ! parentPath.endsWith("/")){
			parentPath = parentPath + "/";
		}
		
		String importFilePath = parentPath+url;
		File importFile = new File(importFilePath);
		if( ! importFile.exists() || ! importFile.isFile()){
			return "";
		}
		
		String content = readFileUTF8(importFile);
		return content;
	}
	
	private static String insertContentToBlock(String line, String content) {
		String endTag = "</";
		int index = line.indexOf(endTag);
		if(index < 0){
			return content;
		}
		
		String begin = line.substring(0, index);
		String end = line.substring(index);
		return begin+"\n"+content+"\n"+end;
	}


	private static void appendLine(StringBuilder sb, String line) {
		sb.append(line).append('\n');
	}

	private static void log(String msg){
		System.out.println(msg);
	}
	
	public static String readFileUTF8(File file) throws IOException {
		return readFile(file, "UTF8");
	}

	public static String readFile(File file, String charset) throws IOException {
		InputStreamReader r = null;
		OutputStreamWriter w = null;

		try {
			r = new InputStreamReader(new FileInputStream(file), charset);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			w = new OutputStreamWriter(out, charset);
			char[] buff = new char[1024 * 4];
			int i;
			while ((i = r.read(buff)) > 0) {
				w.write(buff, 0, i);
			}
			w.flush();
			return out.toString(charset);
		} finally {
			if (r != null)
				try {
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			if (w != null)
				try {
					w.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
