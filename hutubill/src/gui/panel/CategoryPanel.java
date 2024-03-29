package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel {
	static {
		GUIUtil.useLNF();
	}

	public static CategoryPanel instance = new CategoryPanel();

	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	public JButton bInquiry = new JButton("查询");
	String columNames[] = new String[] { "分类名称", "消费次数" };

	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable t = new JTable(ctm);

	public CategoryPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete, bInquiry);
		JScrollPane sp = new JScrollPane(t);
		JPanel pSubmit = new JPanel();
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);
		pSubmit.add(bInquiry);

		this.setLayout(new BorderLayout());
		this.add(sp, BorderLayout.CENTER);
		this.add(pSubmit, BorderLayout.SOUTH);

		addListener();
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(CategoryPanel.instance);
	}

	public Category getSelectedCategory() {
		int index = t.getSelectedRow();
		return ctm.cs.get(index);
	}

	public void updateData() {
		ctm.cs = new CategoryService().list();
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0, 0);

		if (0 == ctm.cs.size()) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
			bInquiry.setEnabled(false);
		} else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
			bInquiry.setEnabled(true);
		}
	}

	public void addListener() {
		CategoryListener listener = new CategoryListener();
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		bInquiry.addActionListener(listener);
	}

}