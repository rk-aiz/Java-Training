package fileOperationSample.questions01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // システムプロパティ "user.dir" を取得
        //String currentDir = System.getProperty("user.dir");
        //System.out.println("現在の作業ディレクトリ: " + currentDir);

    	// 読み込むファイルを指定
    	String filepath1 = "file\\AnimalData.txt";
    	
    	// try-with-resources文を使ってファイルを開く
    	try(FileReader fr = new FileReader(filepath1);
    		BufferedReader br = new BufferedReader(fr);
    		) {
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

