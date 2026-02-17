package fileOperationSample.questions03;

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
		// 読み込むファイルを指定
		String inputPath = "file/AnimalData.txt";
		String outputPath = "file/AnimalData3.txt";
		
		// 1. ファイルからデータを読み込む
		List<Animal> animals = loadAnimals(inputPath);
		
		// ファイルが存在しなかった場合は処理を終了
		if (animals == null) {
			return;
		}

		// 2. データをファイルに書き込む
		saveAnimals(outputPath, animals);
	}

	/**
	 * 指定されたファイルパスからAnimalデータを読み込みます。
	 * ファイルが存在しない場合はnullを返します。
	 * @param filepath 読み込みファイルのパス
	 * @return Animalリスト、またはnull
	 */
	private static List<Animal> loadAnimals(String filepath) {
		List<Animal> animals = new ArrayList<Animal>();
		
		//Fileクラスはファイルや、フォルダの情報を扱うためのクラス
		File file = new File(filepath);
		//Fileクラスのexistsメソッドを使えばファイルが存在するかを事前にチェックできる
		if(!file.exists()) return null;

		// try-with-resources文を使ってファイルを開く
		try(FileReader fr = new FileReader(filepath);
				BufferedReader br = new BufferedReader(fr);) {
			
			String line;
			// ファイルを1行読み込み、データがある間繰り返す
			while ((line = br.readLine()) != null) {
				String[] rawData = line.split(",");
				animals.add(new Animal(
						rawData[0], 
						rawData[1], 
						Integer.parseInt(rawData[2])));
			}

		} catch (IOException ex) {
			// IOExceptionの例外処理
			ex.printStackTrace();
		}
		return animals;
	}

	/**
	 * Animalデータのリストを指定されたファイルパスに書き込みます。
	 * @param filepath 書き込みファイルのパス
	 * @param animals 書き込むデータのリスト
	 */
	private static void saveAnimals(String filepath, List<Animal> animals) {
		// try-with-resources文を使ってファイルを開く
		try(FileWriter fw = new FileWriter(filepath);
			BufferedWriter bw = new BufferedWriter(fw);) {
			// 書き込みを繰り返す
			
			for (Animal a : animals) {
				
				String line = String.format(
						"%s,%s,%d", 
						a.getName(),
						a.getKind(),
						a.getAge());
				
				System.out.println("Writing : " + line);
				
				bw.write(line);
				bw.newLine();
			}
		}catch(IOException e) {
			//  IOExceptionの例外処理
			e.printStackTrace();
		}
	}

}
