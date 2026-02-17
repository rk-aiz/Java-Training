package kakeibo.service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import kakeibo.entity.Entity;
import kakeibo.entity.Himoku;
import kakeibo.repository.HimokuRepository;

public class HomokuService {

	HimokuRepository himokuRepository;

	public DefaultTableModel createTable() {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ID","費目名","削除フラグ"};
		model.setColumnIdentifiers(column);
		model.setDataVector(conversion(himokuRepository.findAll()), column);
		return model;
	}

	public List<Entity> getList() {
		return himokuRepository.findAll();
	}

	public int getHimokuId(String name) {

		List<Entity> list = himokuRepository.findAll();
		for (Entity entity : list) {
			if (((Himoku) entity).getHimoku().equals(name)) {
				return ((Himoku) entity).getId();
			}
		}

		return -1;
	}

	private Object[][] conversion(List<Entity> list) {
		Object[][] data = new Object[list.size()][];

		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof Himoku)) {
				throw new IllegalArgumentException("entity is not Himoku");
			}

			Himoku himoku = (Himoku) list.get(i);
			data[i] = new Object[] {
				himoku.getId(),
				himoku.getHimoku(),
				himoku.isDeleteflag()
			};
		}

		return data;
	}

	public boolean insertHimoku(String name) {
		Himoku himoku = new Himoku();
		himoku.setHimoku(name);
		return himokuRepository.insertEntity(himoku) > 0;
	}
		
	public boolean updateHimoku(int id, String name, boolean deleteflag) {
		Himoku himoku = new Himoku();
		himoku.setId(id);
		himoku.setHimoku(name);
		himoku.setDeleteflag(deleteflag);
		return himokuRepository.updateEntity(himoku) > 0;
	}

	public int getCurrentMaxId() {
		return himokuRepository.getIdMaxValue();
	}

	public Object[] getData() {
		Himoku himoku = (Himoku) himokuRepository.findById(getCurrentMaxId());
		return new Object[] {
			himoku.getId(),
			himoku.getHimoku(),
			himoku.isDeleteflag()
		};
	}
}
