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

import kakeibo.service.KakeiboService;

public class KakeiboPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private KakeiboService kakeiboService;

	/**
	 * Create the panel.
	 */
	public KakeiboPanel(KakeiboService kakeiboService) {

		this.kakeiboService = kakeiboService;
		
		setLayout(null);
		setSize(726, 433);
		
		DefaultTableModel model = kakeiboService.createTable();
		
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
		
		JLabel himokuLabel = new JLabel("費目");
		himokuLabel.setBounds(26, 322, 48, 13);
		add(himokuLabel);
		
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
