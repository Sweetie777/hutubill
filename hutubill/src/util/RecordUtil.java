package util;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.model.RecordTableModel;
import service.RecordService;

public class RecordUtil extends JDialog {
	static {
		GUIUtil.useLNF();
	}
	static RecordTableModel rtm = new RecordTableModel();
	static JTable t = new JTable(rtm);
	// static JButton bFirst = new JButton("首页");
	// static JButton bPre = new JButton("上一页");
	// static JButton bNext = new JButton("下一页");
	// static JButton bLast = new JButton("末页");
	//
	// static int number = 10;// 每页显示10个
	// static int start = 0;// 开始的页码
	static JButton bDelete = new JButton("删除");
	static JButton bUpdate = new JButton("修改");
	static JButton bSubmit = new JButton("提交");

	public RecordUtil() {
		JFrame f = new JFrame();
		this.setSize(400, 360);
		this.setLocation(200, 200);
		// this.setModal(true);
		this.setLayout(new BorderLayout());
		t.getSelectionModel().setSelectionInterval(0, 0);
		GUIUtil.setColor(ColorUtil.blueColor, bDelete, bUpdate);

		JPanel pDelete = new JPanel();
		pDelete.add(bDelete);
		pDelete.add(bUpdate);

		this.setVisible(true);
		// addPageListener();
		bDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 判断是否选中
				int index = t.getSelectedRow();
				if (-1 == index) {
					JOptionPane.showMessageDialog(null, "删除前需要先选中一行");
					return;
				}

				// 进行确认是否要删除
				if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, "确认要删除？"))
					return;

				// 获取id
				Record h = rtm.records.get(index);
				int id = h.id;

				// 删除
				new RecordDAO().delete(id);
				updateTable();
			}
		});
		bUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断是否选中
				int index = t.getSelectedRow();
				if (-1 == index) {
					JOptionPane.showMessageDialog(pDelete, "编辑前需要先选中一行");
					return;
				}

				// 获取选中的对象
				Record h = rtm.records.get(index);

				// 显示编辑Dialog

				addDialog ed = new addDialog(f);
				ed.tfspend.setText(String.valueOf((int) h.spend));
				ed.tfcid.setText(String.valueOf((int) h.cid));
				ed.tfcomment.setText(h.comment);

				ed.setVisible(true);

			}
		});
		JScrollPane sp = new JScrollPane(t);

		this.add(sp, BorderLayout.CENTER);
		this.add(pDelete, BorderLayout.SOUTH);
	}

	public static void updateTable() {
		rtm.records = new RecordDAO().list();
		t.updateUI();
		if (!rtm.records.isEmpty())
			t.getSelectionModel().setSelectionInterval(0, 0);
	}

	static class addDialog extends JDialog {
		JLabel lspend = new JLabel("消费：");
		JLabel lcid = new JLabel("外键：");
		JLabel lcomment = new JLabel("备注：");
		JTextField tfspend = new JTextField("");
		JTextField tfcid = new JTextField("");
		JTextField tfcomment = new JTextField("");

		addDialog(JFrame f) {
			// TODO Auto-generated constructor stub
			super(f);
			this.setModal(true);
			int gap = 30;
			this.setLayout(new BorderLayout());

			JPanel pInput = new JPanel();
			JPanel pSubmit = new JPanel();
			pInput.setLayout(new GridLayout(3, 3, gap, gap));
			pInput.add(lspend);
			pInput.add(tfspend);
			pInput.add(lcid);
			pInput.add(tfcid);
			pInput.add(lcomment);
			pInput.add(tfcomment);
			pSubmit.add(bSubmit);
			GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

			this.add(pInput, BorderLayout.NORTH);
			this.add(pSubmit, BorderLayout.CENTER);

			this.setSize(360, 320);
			this.setLocationRelativeTo(f);
			bSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (GUIUtil.checkNumber(tfspend, "spend")) {
						if (GUIUtil.checkNumber(tfcid, "cid")) {
							if (GUIUtil.checkEmpty(tfcomment, "comment")) {
								int index = t.getSelectedRow();
								int id = rtm.records.get(index).id;
								int spend = Integer.parseInt(tfspend.getText());
								int cid = Integer.parseInt(tfcid.getText());
								String comment = tfcomment.getText();
								Date date = DateUtil.today();
								Record r = new Record();
								r.spend = spend;
								r.cid = cid;
								r.comment = comment;
								r.id = id;
								r.date = date;
								new RecordDAO().update(r);
								JOptionPane.showMessageDialog(f, "提交成功");
								addDialog.this.setVisible(false);
								updateTable();
							}
						}
					}
				}
			});
		}
	}
}
