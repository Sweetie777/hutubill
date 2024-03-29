package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @ClassName: GUIUtil
 * @Description:专门用在图形界面上的工具类
 * @author: haungyongqiang
 * @date:2018年7月5日 下午4:30:15
 */
public class GUIUtil {
	private static String imageFolder = "e:/project/hutubill/img";

	/**
	 * 
	 * @Title: setImageIcon
	 * @Description: 设置图标
	 * @param: b
	 * @param: fileName
	 * @param: tip
	 * @return: void
	 */
	public static void setImageIcon(JButton b, String fileName, String tip) {
		ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61, 81));
		b.setToolTipText(tip);
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setText(tip);
	}

	/**
	 * 
	 * @Title: setColor
	 * @Description: 设置颜色
	 * @param: color
	 * @param: cs
	 * @return: void
	 */
	public static void setColor(Color color, JComponent... cs) {
		for (JComponent c : cs) {
			c.setForeground(color);
		}
	}

	/**
	 * 
	 * @param p
	 * @param strechRate
	 *            拉伸比例1表示满屏幕
	 */
	public static void showPanel(JPanel p, double strechRate) {
		GUIUtil.useLNF();
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(strechRate);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		cp.show(p);
	}

	public static void showPanel(JPanel p) {
		showPanel(p, 0.85);
	}

	/**
	 * 
	 * @Title: checkNumber
	 * @Description: 判断填写的是数字
	 * @param: tf
	 * @param: input
	 * @return: boolean
	 */
	public static boolean checkNumber(JTextField tf, String input) {
		if (!checkEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, input + " 需要是整数");
			tf.grabFocus();
			return false;
		}
	}

	/**
	 * 
	 * @Title: checkZero
	 * @Description: 判断是否为 0
	 * @param: tf
	 * @param: input
	 * @return: boolean
	 */
	public static boolean checkZero(JTextField tf, String input) {
		if (!checkNumber(tf, input))
			return false;
		String text = tf.getText().trim();

		if (0 == Integer.parseInt(text)) {
			JOptionPane.showMessageDialog(null, input + " 不能为零");
			tf.grabFocus();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: checkEmpty
	 * @Description: 判断不能为空
	 * @param: tf
	 * @param: input
	 * @return: boolean
	 */
	public static boolean checkEmpty(JTextField tf, String input) {
		String text = tf.getText().trim();
		if (0 == text.length()) {
			JOptionPane.showMessageDialog(null, input + " 不能为空");
			tf.grabFocus();
			return false;
		}
		return true;

	}

	public static int getInt(JTextField tf) {
		return Integer.parseInt(tf.getText());
	}

	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}