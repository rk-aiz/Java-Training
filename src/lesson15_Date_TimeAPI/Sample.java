package lesson15_Date_TimeAPI;

public class Sample {

	public static void main(String[] args) {
		
		String[] strArray = {
				"#名前,年齢,性別",
				"Alice,20,女性",
				"Bob,21,男性",
				"Carol,19,女性"
			};
		
		String[] Alice = strArray[1].split(",");
		String[] Bob = strArray[2].split(",");
		String[] Carol = strArray[3].split(",");
//		
//		System.out.println("Alice : "+Alice);
		
		String[][] datas = new String[strArray.length][];
		
		//                0                   1                     2              3
		//datas{{ Alice , 20 , 女性 },{ Bob , 21 , 男性 },{ Carol , 19 , 女性 },{ null }}
		//         0       1     2       0     1     2         0     1     2		
		
				
		//       0   1   2   3
		//datas{{ },{ },{ },{ }}
		

		System.out.println("datas : "+datas);
		System.out.println("datas[0] : "+datas[0]);
		System.out.println("datas[1] : "+datas[1]);
		System.out.println("datas[2] : "+datas[2]);

		
		datas[0] = Alice;//Alice配列の値が入ってるアドレスを格納
		datas[1] = Bob;//Bob配列の値が入ってるアドレスを格納
		datas[2] = Carol;//Carol配列の値が入ってるアドレスを格納
		System.out.println("datas[0] : "+datas[0]);
		System.out.println("datas[1] : "+datas[1]);
		System.out.println("datas[2] : "+datas[2]);
		System.out.println(datas[0][0]);
		
	}

}
