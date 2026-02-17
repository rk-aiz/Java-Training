package fileOperationSample.fileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// 読み込むファイルを指定
		String filepath = "C:\\pleiades\\2024-12\\workspace\\sukkiri\\file\\momotaro.txt";
		// try-with-resources文を使ってファイルを開く
		try(FileReader fr = new FileReader(filepath);
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			// ファイルを1行読み込み、データがある間繰り返す
			while ((line = br.readLine()) != null) {
				// １行表示
				System.out.println(line);
			}
		} catch (IOException ex) {
			// IOExceptionの例外処理
			ex.printStackTrace();
		}
	}

}
