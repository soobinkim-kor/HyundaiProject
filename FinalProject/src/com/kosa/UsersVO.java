package com.kosa;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersVO {
	private int idx;
	private String name;
	private int money;
	private int now;
	private int turn;

	public UsersVO() {
	}

	public UsersVO(int idx, String name, int money, int now) {
		this.idx = idx;
		this.name = name;
		this.money = money;
		this.now = now;
	}

	public int getIdx() {
		return idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

}
