package kakeibo.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Kakeibo implements Entity {

	private int id;
	private int himokuid;
	private String memo;
	private int nyuukingaku;
	private int syukkingaku;
	private LocalDateTime hiduke;
	private Himoku himokutable;
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHimokuid() {
		return himokuid;
	}
	
	public void setHimokuid(int himokuid) {
		this.himokuid = himokuid;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public int getNyuukingaku() {
		return nyuukingaku;
	}
	
	public void setNyuukingaku(int nyuukingaku) {
		this.nyuukingaku = nyuukingaku;
	}
	
	public int getSyukkingaku() {
		return syukkingaku;
	}
	
	public void setSyukkingaku(int sykkingaku) {
		this.syukkingaku = sykkingaku;
	}
	
	public LocalDateTime getHiduke() {
		return hiduke;
	}
	public void setHiduke(LocalDateTime hiduke) {
		this.hiduke = hiduke;
	}
	
	public Himoku getHimokutable() {
		return himokutable;
	}
	
	public void setHimokutable(Himoku himokutable) {
		this.himokutable = himokutable;
	}
	
	
}
