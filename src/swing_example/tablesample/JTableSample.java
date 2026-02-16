package swing_example.tablesample;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableSample extends JFrame {
	
	public static void main(String[] args) {
		JTableSample frame = new JTableSample();
		frame.setVisible(true);
	}

	public JTableSample() {
		
		setTitle("麦わらの一味");
		
		setSize(600, 200);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Object[][] data = {
				{"ルフィ", 19, "船長", "30億ベリー" },
	            { "ゾロ", 21, "剣士", "11億1100万ベリー" },
	            { "ナミ", 20, "航海士", "3億6600万ベリー" },
	            { "ウソップ", 19, "狙撃手", "5億ベリー" },
	            { "サンジ", 21, "料理長", "10億3200万ベリー" },
	            { "チョッパー", 17, "医者", "1000ベリー" },
	            { "ロビン", 30, "考古学者", "9億3000万ベリー" },
	            { "フランキー", 36, "船大工", "3億9400万ベリー" },
	            { "ブルック", 90, "音楽家", "3億8300万ベリー" },
	            { "ジンベエ", 46, "操舵手", "11億ベリー" },
		};
		
		String[] columnNames = {"名前", "年齢", "役職", "懸賞金"};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		JTable table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
		
		model.addRow(new Object[] {"ビビ", 18, "アラバスタの女王", "なし"});
	}
}
