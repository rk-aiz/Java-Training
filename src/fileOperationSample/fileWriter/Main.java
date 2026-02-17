package fileOperationSample.fileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		// 書き込むファイルを指定
		String filepath = "file/data2.txt";
		// try-with-resources文を使ってファイルを開く
		try(FileWriter fw = new FileWriter(filepath);
				BufferedWriter bw = new BufferedWriter(fw);) {
			// 書き込みを繰り返す
			for(int i = 0; i < 5; i++) {
				bw.write("[" + (i+1) + "]\n"); // ファイルに書き込み
				bw.newLine(); // 改行
			}
		}catch(IOException e) {
			// IOExceptionの例外処理
			e.printStackTrace();
		}
	}

}
