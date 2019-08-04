package gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordTableModel extends AbstractTableModel {

	String[] columnNames = new String[] { "id", "spend", "cid", "comment", "date" };

	// 使用从DAO返回的List作为TableModel的数据

	public List<Record> records = new RecordDAO().list();

	// heros.size返回一共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return records.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	// 先通过heros.get(rowIndex)获取行对应的Hero对象
	// 然后根据columnIndex返回对应的属性
	public Object getValueAt(int rowIndex, int columnIndex) {
		Record h = records.get(rowIndex);
		if (0 == columnIndex)
			return h.id;
		if (1 == columnIndex)
			return h.spend;
		if (2 == columnIndex)
			return h.cid;
		if (3 == columnIndex)
			return h.comment;
		if (4 == columnIndex)
			return h.date;
		return null;
	}

}