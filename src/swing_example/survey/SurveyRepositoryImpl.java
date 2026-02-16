package swing_example.survey;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class SurveyRepositoryImpl implements SurveyRepository {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private File file;
	private String filepath;

	public SurveyRepositoryImpl(String filepath) {
		this.filepath = filepath;
		this.file = new File(filepath);
	}
	
	@Override
	public void save(SurveyForm surveyForm) {
		
		String csvLine = String.format(
				"%s,%s,%s,%s,%s",
				surveyForm.getName(),
				surveyForm.getDecade(),
				joinStrList("、", surveyForm.getHobbies()),
				surveyForm.getDevice(),
				surveyForm.getCreatedAt().format(this.formatter));
		
		//System.out.println(csvLine);
		writeToFile(csvLine);
	}
	
	private String joinStrList(String delimiter, List<String> strList) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = strList.iterator();
		
		while(it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(delimiter);
			}
		}
		
		return sb.toString();
	}

	
	public void writeToFile(String line) {
		
		boolean fileExists = this.fileExists();
		
		// try-with-resources文を使ってファイルを開く
		try(FileWriter fw = new FileWriter(this.filepath, fileExists);
			BufferedWriter bw = new BufferedWriter(fw);) {
			
			if (!fileExists) {
				bw.write("名前,年齢,趣味,利用端末,登録日時"); // ヘッダー書き込み
				bw.newLine(); // 改行
			}
			
			bw.write(line);
			bw.newLine(); // 改行
		} catch(IOException e) {
			//  IOExceptionの例外処理
			e.printStackTrace();
		}
	}
	
	public boolean fileExists() {
		return this.file.exists();
	}
	

//	public String csvReader(String line) {
//		String msg = "件読み込みを完了しました";
//		int countRead = 0;
//
//		try (FileReader fr = new FileReader(this.filepath);
//				BufferedReader br = new BufferedReader(fr);) {
//			String line;
//			// ファイルを1行読み込み、データがある間繰り返す
//			while ((line = br.readLine()) != null) {
//
//				if (line.matches("^#.*"))
//					continue;
//
//				String[] rawData = line.split(",");
//				if (rawData.length == 3) {
//					accounts.add(new Account(
//							rawData[0],
//							Integer.parseInt(rawData[1]),
//							Integer.parseInt(rawData[2])
//							));
//				} else if (rawData.length == 4) {
//					accounts.add(new TimeAccount(
//							rawData[0],
//							Integer.parseInt(rawData[1]),
//							Integer.parseInt(rawData[2]),
//							Integer.parseInt(rawData[3])
//							));
//				} else {
//					continue;
//				}
//				
//				countRead++;
//			}
//		} catch (IOException ex) {
//			// IOExceptionの例外処理
//			ex.printStackTrace();
//		}
//		
//		return countRead + msg;
//	}
}
