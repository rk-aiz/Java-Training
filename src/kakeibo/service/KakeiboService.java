package kakeibo.service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import kakeibo.entity.Entity;
import kakeibo.entity.Himoku;
import kakeibo.entity.Kakeibo;
import kakeibo.repository.HimokuRepository;
import kakeibo.repository.KakeiboRepository;

public class KakeiboService {

	private KakeiboRepository kakeiboRepository;
	private HimokuRepository himokuRepository;
	List<Entity> list;
	
	public KakeiboService(
			KakeiboRepository kakeiboRepository,
			HimokuRepository himokuRepository) {
		this.kakeiboRepository = kakeiboRepository;
		this.himokuRepository = himokuRepository;
	}
	
	public DefaultTableModel createTable() {
		DefaultTableModel model = new DefaultTableModel();
		String[] column = {"ID","費目名","メモ","入金額","出金額","日付"};
		model.setColumnIdentifiers(column);
		model.setDataVector(conversion(kakeiboRepository.findAll()), column);
		return model;
	}

	private Object[][] conversion(List<Entity> list) {
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof Kakeibo)) {
				throw new IllegalArgumentException("entity is not Kakeibo");
			}

			Kakeibo kakeibo = (Kakeibo) list.get(i);
			Entity himokuEntity = himokuRepository.findById(kakeibo.getHimokuid());

			if (!(himokuEntity instanceof Himoku)) {
				throw new IllegalArgumentException("entity is not Himoku");
			}

			Himoku himoku = (Himoku) himokuEntity;
			
			data[i] = new Object[] {
				kakeibo.getId(),
				himoku.getHimoku(),
				kakeibo.getMemo(),
				kakeibo.getNyuukingaku(),
				kakeibo.getSyukkingaku(),
				kakeibo.getHiduke()
			};
		}
		return data;
	}

	public int entryData(int id,int himokuid,String memo,int selectRadioButton,String money,int selectRow) {

		if (himokuid == -1 || himokuid == 0) return -1;

		if (selectRadioButton == -1) return -1;

		int parsedMoney = 0;
		try {
			parsedMoney = Integer.parseInt(money.trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}

		if (parsedMoney < 0) return -1;

		Kakeibo kakeibo = new Kakeibo();
		kakeibo.setId(id);
		kakeibo.setHimokuid(himokuid);
		kakeibo.setMemo(memo);

		switch (selectRadioButton) {
			case 1 -> {
				kakeibo.setNyuukingaku(parsedMoney);
				kakeibo.setSyukkingaku(0);
			}
			case 2 -> {
				kakeibo.setNyuukingaku(0);
				kakeibo.setSyukkingaku(parsedMoney);
			}
		}

		if (selectRow == -1) {
			return kakeiboRepository.insertEntity(kakeibo);
		} else {
			return kakeiboRepository.updateEntity(kakeibo);
		}
	}

	public int deleteData(int id) {
		return kakeiboRepository.deleteEntity(kakeiboRepository.findById(id));
	}

	public int getCurrentMaxId() {
		return kakeiboRepository.getIdMaxValue();
	}

	public Object[] getData() {
		return createObject(getCurrentMaxId());
	}

	public Object[] getData(int id) {
		return createObject(id);
	}

	private Object[] createObject(int id) {
		Kakeibo kakeibo = (Kakeibo) kakeiboRepository.findById(id);
		Himoku himoku = (Himoku) himokuRepository.findById(kakeibo.getHimokuid());
		return new Object[] {
			kakeibo.getId(),
			himoku.getHimoku(),
			kakeibo.getMemo(),
			kakeibo.getNyuukingaku(),
			kakeibo.getSyukkingaku(),
			kakeibo.getHiduke()
		};
	}
 }
