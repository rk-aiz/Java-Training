package kakeibo.view;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KakeiboPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public KakeiboPanel() {
		
		setLayout(null);
		setSize(726, 433);
		
		Object[][] data = {
				{ 1, "2024-02-10", "給料","1月の給料",280000,0 },
				{ 2, "2024-02-11","教養娯楽費","書籍を購入",0,2800 },
				{ 3, "2024-02-18","水道光熱費","1月の電気代",0,7560 },
				{ 4, "2024-02-14","交際費","同期会の会費",0,6000 },
				{ 5, "2026-01-29","食費","マクドナルド",0,850 }
		};
		
		JLabel himokuLabel = new JLabel("費目");
		himokuLabel.setBounds(26, 322, 48, 13);
		add(himokuLabel);
		
		String[] hearder = {"ID", "費目名", "メモ", "入金額", "出金額", "日付"};
//		JLabel[] hearder = {idLabel, idLabel, idLabel, idLabel, idLabel, idLabel};
		
		DefaultTableModel model = new DefaultTableModel(data, hearder);
		
		JTable table = new JTable(model) {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, -1, toggle, extend);
			}
		};
		table.setSize(690, 280);
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(12, 10, 690, 280);
		
		
//		「費目」ラベル														setBounds(12, 303, 50, 15)
//		費目コンボボックス														setBounds(60, 300, 263, 20)
//		「メモ」ラベル														setBounds(12, 337, 50, 15)
//		メモ入力JTextArea（JScrollPane使用）									setBounds(12, 337, 50, 15)
//		「入出金」ラベル														setBounds(381, 304, 50, 13)
//		入金ラジオボタン														setBounds(439, 300, 65, 21)
//		出金ラジオボタン														setBounds(525, 300, 65, 21)
//		「金額」ラベル														setBounds(381, 338, 50, 13)
//		金額入力テキストフィールド														setBounds(438, 335, 169, 19)
//		登録/変更ボタン														setBounds(450, 364, 100, 20)
//		データ削除ボタン														setBounds(560, 364, 100, 20)

		
		add(tableScrollPane);

		
		
		JComboBox<String> himokuComboBox = new JComboBox<>();
		himokuComboBox.setBounds(75, 320, 134, 20);
		add(himokuComboBox);
		
		himokuComboBox.addItem("寿司");
		himokuComboBox.addItem("豚キムチ");
		himokuComboBox.addItem("豚骨ラーメン");
		himokuComboBox.addItem("カレー");
		himokuComboBox.addItem("焼きそば");
		
		JLabel memoLabel = new JLabel("メモ");
		memoLabel.setBounds(26, 355, 48, 13);
		add(memoLabel);
		
		JTextArea memoTextArea = new JTextArea("メモテキストエリア");
		memoTextArea.setSize(50, 15);
		JScrollPane memoScrollPane = new JScrollPane(memoTextArea);
		memoScrollPane.setBounds(75, 353, 260, 39);
		add(memoScrollPane);
		
		JLabel himokuLabel_1 = new JLabel("入出金");
		himokuLabel_1.setBounds(381, 324, 48, 13);
		add(himokuLabel_1);
		
		JLabel himokuLabel_2 = new JLabel("金額");
		himokuLabel_2.setBounds(381, 355, 48, 13);
		add(himokuLabel_2);
		
		textField = new JTextField();
		textField.setBounds(441, 352, 233, 19);
		add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNyukin = new JRadioButton("入金");
		rdbtnNyukin.setBounds(437, 320, 62, 21);
		add(rdbtnNyukin);
		
		JRadioButton rdbtnShukkin = new JRadioButton("出金");
		rdbtnShukkin.setBounds(503, 320, 62, 21);
		add(rdbtnShukkin);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnNyukin);
		radioGroup.add(rdbtnShukkin);
		
		
		//model.addRow(new Object[] {"ビビ", 18, "アラバスタの女王", "なし"});

	}
}
