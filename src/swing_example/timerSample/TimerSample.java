package swing_example.timerSample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


//Timerを使ったら指定した時間ごとにactionPerformedが呼ばれる
public class TimerSample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Timer Sample");
        JLabel label1 = new JLabel("0");
        JLabel label2 = new JLabel("0");
        
        frame.setContentPane(new JPanel());

        frame.getContentPane().add(label1);
        frame.getContentPane().add(label2);
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                count++;
                label1.setText(String.valueOf(count));
                
                if(count == 10) {
                	((Timer)e.getSource()).stop();
                }
            }
        });

        Timer timer2 = new Timer(100, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                count++;
                label2.setText(String.valueOf(count));
                
                try {
					Thread.sleep(900);
				} catch (InterruptedException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
                
                if(count == 10) {
                	((Timer)e.getSource()).stop();
                }
            }
        });
        
        timer.start();   // ← ここ重要
        timer2.start();
        
 
    }
}
