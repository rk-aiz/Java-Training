package fileOperationSample.practice.questions02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// 読み込むファイルを指定
		String filepath = "file/AnimalData.txt";
		String filepath2 = "file/AnimalData2.txt";
		
		//String filepath2 = System.getProperty("user.dir")+"file/AnimalData2.txt";
		//System.out.println(System.getProperty("user.dir"));
		
		//Fileクラスはファイルや、フォルダの情報を扱うためのクラス
		File file = new File(filepath);
		//Fileクラスのexistsメソッドを使えばファイルが存在するかを事前にチェックできる
		if(file.exists()) {

			// try-with-resources文を使ってファイルを開く
			try(FileReader fr = new FileReader(filepath);
					BufferedReader br = new BufferedReader(fr);
					FileWriter fw = new FileWriter(filepath2);
					BufferedWriter bw = new BufferedWriter(fw);) {
				
				String line;
				// ファイルを1行読み込み、データがある間繰り返す
				while ((line = br.readLine()) != null) {
					// １行表示file
					bw.write(line); // ファイルに書き込み
					bw.newLine();// 改行
				}

			} catch (IOException ex) {
				// IOExceptionの例外処理
				ex.printStackTrace();
			}
		}
	}

}
