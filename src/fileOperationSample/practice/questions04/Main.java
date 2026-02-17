package fileOperationSample.practice.questions04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		String filepath ="file/AnimalData.txt";
		String filepath3 ="file/AnimalData3.txt";
		File file = new File(filepath);
		List <Animal> animallist = new ArrayList<>();

		if(file.exists()) {	

			try(FileReader fr = new FileReader(filepath);
					BufferedReader br = new BufferedReader(fr);) {

				String line;
				// ファイルを1 行読み込み、データがある間繰り返す
				while ((line = br.readLine()) != null) {
					// １行表示
					String [] a = line.trim().split("[ ,]");

					animallist.add(new Animal(a[0],a[1],Integer.parseInt(a[2])));

				}

			}catch (IOException ex) {
				// IOException の例外処理
				ex.printStackTrace();
			}
		}

		//		for(Animal a : animallist) {
		//
		//			System.out.println(a.getName()+" "+a.getKind()+","+a.getAge());
		//
		//		}

		try(FileWriter fw = new FileWriter(filepath3);
				BufferedWriter bw = new BufferedWriter(fw);) {

			for(Animal a:  animallist) {
				// １行表示
				bw.write(a.getName()+","+a.getKind()+","+a.getAge()); // ファイルに書き込み
				//bw.write(a.toString());// ファイルに書き込み
				bw.newLine(); // 改行

			}

		}catch (IOException ex) {
			// IOException の例外処理
			ex.printStackTrace();
		}


	}

}
