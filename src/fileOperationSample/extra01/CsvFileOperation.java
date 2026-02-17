package fileOperationSample.extra01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileOperation {
	private final String filepath;
	private File f;

	public CsvFileOperation(String filepath) {
		this.filepath = filepath;
		this.f = new File(filepath);
	}

	public String csvReader(List<Account> accounts) {
		String msg = "件読み込みを完了しました";
		int countRead = 0;

		try (FileReader fr = new FileReader(this.filepath);
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			// ファイルを1行読み込み、データがある間繰り返す
			while ((line = br.readLine()) != null) {

				if (line.matches("^#.*"))
					continue;

				String[] rawData = line.split(",");
				if (rawData.length == 3) {
					accounts.add(new Account(
							rawData[0],
							Integer.parseInt(rawData[1]),
							Integer.parseInt(rawData[2])
							));
				} else if (rawData.length == 4) {
					accounts.add(new TimeAccount(
							rawData[0],
							Integer.parseInt(rawData[1]),
							Integer.parseInt(rawData[2]),
							Integer.parseInt(rawData[3])
							));
				} else {
					continue;
				}
				
				countRead++;
			}
		} catch (IOException ex) {
			// IOExceptionの例外処理
			ex.printStackTrace();
		}
		
		return countRead + msg;
	}
	
	public String CsvWriter(List<Account> accounts) {
		int msgWriteCount = 0;
		String msg = "件書き込みを完了しました";
		
		// try-with-resources文を使ってファイルを開く
		try(FileWriter fw = new FileWriter(this.filepath);
			BufferedWriter bw = new BufferedWriter(fw);) {
			
			bw.write("#口座番号,口座名義,普通預金残高,定期預金残高"); // ファイルに書き込み
			bw.newLine(); // 改行
			
			for (Account a : accounts) {
				bw.write(a.toCsvData());
				bw.newLine();
				msgWriteCount++;
			}
		}catch(IOException e) {
			//  IOExceptionの例外処理
			return "ファイル書き込みエラー";
		}
		
		return msgWriteCount + msg;
	}
	
	public boolean fileExists() {
		return this.f.exists();
	}
}
