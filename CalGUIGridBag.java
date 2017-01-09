import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class MyListener implements ActionListener {
	JTextField jf;

	MyListener(JTextField jf) {
		this.jf = jf;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch (str) {
		case "C":
			jf.setText("");
			break;
		case "=":
			CalLogic cal = new CalLogic(jf.getText());
			jf.setText("" + cal.result());
			break;
		case "←":
			if(jf.getText().length() != 0 ) {
				jf.setText(jf.getText().substring(0, jf.getText().length() - 1));
			}
			break;
		default:
			jf.setText(jf.getText() + str);
		}

	}
}

class MyFrame {
	JTextField jf;
	JFrame f;
	JButton[] b = new JButton[10]; // C+S+o로 바로 import 가능
	JButton[] cal = new JButton[8];

	MyFrame() {
		Font bigfont = new Font("궁서", Font.PLAIN, 36);
		Font midfont = bigfont.deriveFont(0, 26);
		Color deepgreen = new Color(68, 137, 26);
		Color lightgreen = new Color(163, 206, 39);
		Color deepblue = new Color(47, 72, 78);
		
		f = new JFrame("계산기"); // 제목을 지정
		jf = new JTextField();
		jf.setEditable(false); // 사용자는 버튼 이외의 방법으로 textfield에 접근할 수 없다.
		jf.setBackground(lightgreen);
		jf.setForeground(deepgreen);
		jf.setFont(midfont);
		jf.setHorizontalAlignment(SwingConstants.RIGHT);
		
		MyListener listener = new MyListener(jf);

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		f.setLayout(layout);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 7;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		f.add(jf, c);
		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton("" + i); // 숫자를 문자로 바꾸는 쉬운 방법
			b[i].setBackground(deepgreen);
			b[i].setForeground(lightgreen);
			b[i].setFont(bigfont);
			}
		c.gridwidth = 1;
		for (int i = 1; i < b.length; i++) {
			b[i].addActionListener(listener);
			c.gridx = (i % 3) - 1;
			switch ((i - 1) / 3) {
			case 0:
				c.gridy = 1;
				break;
			case 1:
				c.gridy = 2;
				break;
			case 2:
				c.gridy = 3;
				break;
			}
			f.add(b[i], c);
		}
		c.gridy = 4;
		b[0].addActionListener(listener);
		c.gridx = 0;
		c.gridwidth = 2;
		f.add(b[0], c);
		String caltext[] = { ".", "+", "-", "×", "÷", "←", "C", "=" };
		int[][] calConstraint = {//gridx, gridy, gridheight
				{2, 4, 1}, // .
				{3, 1, 1}, // +
				{3, 2, 1}, // -
				{3, 3, 1}, // ×
				{3, 4, 1}, // ÷
				{5, 1, 1}, // ←
				{5, 2, 1}, // C
				{5, 3, 2} // ＝
				};
		for (int i = 0; i < cal.length; i++) {
			cal[i] = new JButton(caltext[i]);
			cal[i].addActionListener(listener);
			c.gridx = calConstraint[i][0];
			c.gridy = calConstraint[i][1];
			c.gridheight = calConstraint[i][2];
			cal[i].setBackground(deepblue);
			cal[i].setForeground(lightgreen);
			cal[i].setFont(bigfont);
			cal[0].setBackground(deepgreen);

			f.add(cal[i], c);
		}
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(500, 300);
		f.setSize(330, 300);
	}

}

public class CalGUIGridBag {

	public static void main(String[] args) {
		new MyFrame();
	}

}
