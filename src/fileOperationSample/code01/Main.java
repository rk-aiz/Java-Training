package fileOperationSample.code01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // システムプロパティ "user.dir" を取得
        //String currentDir = System.getProperty("user.dir");
        //System.out.println("現在の作業ディレクトリ: " + currentDir);

    	// 読み込むファイルを指定
    	String filepath1 = "file\\AnimalData.txt";
    	String filepath2 = "file\\AnimalData2.txt";
//    	// try-with-resources文を使ってファイルを開く
//    	try() {
//    		// 書き込みを繰り返す
//    		for(int i = 0; i < 5; i++) {
//
//    		}
//    	}catch(IOException e) {
//    		//  IOExceptionの例外処理
//    		e.printStackTrace();
//    	}
    	
    	// try-with-resources文を使ってファイルを開く
    	try(FileReader fr = new FileReader(filepath1);
    		BufferedReader br = new BufferedReader(fr);
    		FileWriter fw = new FileWriter(filepath2);
        	BufferedWriter bw = new BufferedWriter(fw);
    		) {
    		String line;
    		// ファイルを1行読み込み、データがある間繰り返す
    		while ((line = br.readLine()) != null) {
    			// １行表示
    			System.out.println(line);
    			bw.write(line); // ファイルに書き込み
    			//bw.newLine(); // 改行
    		}
    	} catch (IOException ex) {
    		// IOExceptionの例外処理
    		ex.printStackTrace();
    	}
    
    
    }
}

