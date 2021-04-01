package com.kosa;

public class UsersVO {
	private int idx;
	private int name;
	private int money;
	private int now;
	
	public UsersVO() {}
	public UsersVO(int idx, int name, int money, int now) {
		this.idx = idx;
		this.name = name;
		this.money = money;
		this.now = now;
	}
	public int getIdx() {
		return idx;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
}
