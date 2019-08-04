package service;

import java.util.Date;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordService {
	RecordDAO recordDao = new RecordDAO();

	public void add(int spend, Category c, String comment, Date date) {
		Record r = new Record();
		r.spend = spend;
		r.cid = c.id;
		r.comment = comment;
		r.date = date;
		recordDao.add(r);
	}

	public Record inquiry(int id, int spend, Category c, String comment, Date date) {
		Record r = new Record();
		r.id = id;
		r.spend = spend;
		r.cid = c.id;
		r.comment = comment;
		r.date = date;
		recordDao.inquiry(r);
		return r;
	}
}