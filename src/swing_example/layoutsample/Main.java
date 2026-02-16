package swing_example.layoutsample;


import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// インスタンスを生成
//		JFrame frame = new BorderLayoutSample();
//		JFrame frame = new BoxLayoutXSample();
//		JFrame frame = new BoxLayoutYSample();
//		JFrame frame = new FlowLayoutSample();
		JFrame frame = new GridBagLayoutSample();
//		JFrame frame = new GridLayoutSample();
//		JFrame frame = new GroupLayoutSample();
//		JFrame frame = new OverlayLayoutSample();
//		JFrame frame = new SpringLayoutSample();
		// 表示
		frame.setVisible(true);
	}

}
