package swing_example.survey;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Survey extends JFrame {

	private final SurveyRepository surveyRepository;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField textOther;
	
	private List<JComponent> components = new ArrayList<>();
	private static final List<String> DECADE_LIST = List.of(
			"10歳未満",
			"10代",
			"20代",
			"30代",
			"40代",
			"50代",
			"60代",
			"70代",
			"80代",
			"90歳以上");
	
	public Survey(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
		this.initializeComponent();
	}
		
	private void sendAction() {
		
		//確認ダイアログ表示
		int confirm = JOptionPane.showConfirmDialog(
				this, 
				"画面の内容で登録します。\nよろしいですか？",
				"登録確認",
				JOptionPane.YES_NO_OPTION);
		
		if (confirm == 0) { // はいの場合
			this.surveyRepository.save(
					SurveyForm.fromJComponents(this.components));
		}
		
		// アンケートの初期化
		clearAction();
	}
	
	/**
	 * アンケートを初期化します。
	 */
	private void clearAction() {
		for (JComponent c : components) {
			switch (c) {
				case JTextField tf -> { tf.setText(""); }
				case JComboBox<?> cb -> { cb.setSelectedIndex(0); }
				case JCheckBox cb -> { cb.setSelected(false); }
				case JRadioButton rb -> { 
					if ("パソコン".equals(rb.getText())) rb.setSelected(true); }
				default -> {}
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Survey frame = new Survey(new SurveyRepositoryImpl("file/survey.txt"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public void initializeComponent() {
		setTitle("アンケート");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(12, 10, 510, 69);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel nameLabel = new JLabel("名前");
		nameLabel.setBounds(12, 10, 50, 20);
		panel1.add(nameLabel);
		
		JLabel ageLabel = new JLabel("年齢");
		ageLabel.setBounds(12, 40, 50, 19);
		panel1.add(ageLabel);
		
		nameField = new JTextField();
		nameField.setName("名前");
		nameField.setBounds(74, 11, 157, 19);
		panel1.add(nameField);
		nameField.setColumns(10);
		components.add(nameField);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setName("年代");
		comboBox.setBounds(74, 39, 157, 21);
		//コンボボックスに年代項目リストをセット
		for (String d : DECADE_LIST) {
			comboBox.addItem(d);
		}
		panel1.add(comboBox);
		components.add(comboBox);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(12, 89, 510, 103);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel hobbyLabel = new JLabel("趣味");
		hobbyLabel.setBounds(12, 10, 50, 13);
		panel2.add(hobbyLabel);
		
		JCheckBox chkMusic = new JCheckBox("音楽");
		chkMusic.setBounds(22, 35, 75, 21);
		panel2.add(chkMusic);
		components.add(chkMusic);
		
		JCheckBox chkFishing = new JCheckBox("釣り");
		chkFishing.setBounds(112, 35, 59, 21);
		panel2.add(chkFishing);
		components.add(chkFishing);
		
		JCheckBox chkSport = new JCheckBox("スポーツ観戦");
		chkSport.setBounds(187, 35, 117, 21);
		panel2.add(chkSport);
		components.add(chkSport);
		
		JCheckBox chkTravel = new JCheckBox("旅行");
		chkTravel.setBounds(308, 35, 68, 21);
		panel2.add(chkTravel);
		components.add(chkTravel);
		
		JCheckBox chkCinema = new JCheckBox("映画鑑賞");
		chkCinema.setBounds(398, 35, 82, 21);
		panel2.add(chkCinema);
		components.add(chkCinema);
		
		JCheckBox chkAlchol = new JCheckBox("お酒");
		chkAlchol.setBounds(23, 60, 74, 21);
		panel2.add(chkAlchol);
		components.add(chkAlchol);
		
		JCheckBox chkCooking = new JCheckBox("料理");
		chkCooking.setBounds(112, 60, 59, 21);
		panel2.add(chkCooking);
		components.add(chkCooking);
		
		JCheckBox chkBook = new JCheckBox("読書");
		chkBook.setBounds(187, 60, 117, 21);
		panel2.add(chkBook);
		components.add(chkBook);
		
		JCheckBox chkOther = new JCheckBox("その他");
		chkOther.setBounds(308, 60, 68, 21);
		panel2.add(chkOther);
		components.add(chkOther);
		
		textOther = new JTextField();
		textOther.setName("その他趣味");
		textOther.setBounds(376, 62, 104, 19);
		panel2.add(textOther);
		textOther.setColumns(10);
		components.add(textOther);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(12, 202, 510, 80);
		contentPane.add(panel3);
		panel3.setLayout(null);
		
		JLabel deviceLabel = new JLabel("使用端末");
		deviceLabel.setBounds(12, 10, 50, 13);
		panel3.add(deviceLabel);
		
		JRadioButton radioPC = new JRadioButton("パソコン", true);
		radioPC.setBounds(25, 39, 78, 21);
		panel3.add(radioPC);
		components.add(radioPC);
		
		JRadioButton radioSmartPhone = new JRadioButton("スマホ");
		radioSmartPhone.setBounds(116, 39, 78, 21);
		panel3.add(radioSmartPhone);
		components.add(radioSmartPhone);
		
		JRadioButton radioTablet = new JRadioButton("タブレット");
		radioTablet.setBounds(202, 39, 92, 21);
		panel3.add(radioTablet);
		components.add(radioTablet);
		
		JRadioButton radioOther = new JRadioButton("その他");
		radioOther.setBounds(298, 39, 78, 21);
		panel3.add(radioOther);
		components.add(radioOther);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioPC);
		group.add(radioSmartPhone);
		group.add(radioTablet);
		group.add(radioOther);
		
		JPanel panel4 = new JPanel();
		panel4.setBounds(12, 292, 510, 59);
		contentPane.add(panel4);
		panel4.setLayout(null);
		
		JButton clearButton = new JButton("クリア");
		clearButton.setBounds(17, 19, 91, 21);
		clearButton.addActionListener(e -> this.clearAction());
		panel4.add(clearButton);
		
		JButton submitButton = new JButton("送信");
		submitButton.setBounds(407, 19, 91, 21);
		submitButton.addActionListener(e -> this.sendAction());
		
		panel4.add(submitButton);
	}
}
