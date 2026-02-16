package kakeibo.entity;

public class Himoku implements Entity {
	
	private int id;
	private String himoku;
	private boolean deleteflag;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getHimoku() {
		return himoku;
	}
	
	public void setHimoku(String himoku) {
		this.himoku = himoku;
	}
	
	public boolean isDeleteflag() {
		return deleteflag;
	}
	
	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
}
