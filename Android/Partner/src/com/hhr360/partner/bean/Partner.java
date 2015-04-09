package com.hhr360.partner.bean;

import java.util.ArrayList;

public class Partner {

	private String registerTime = "";
	private String lastLoginTime = "";
	private int firstPartnerNum = 0;
	private int secondPartnerNum = 0;
	private double monthIncome = 0;
	private double secStockEndow = 0;
	private double secFutEndow = 0;
	private double secStockComm = 0;
	private double secFutComm = 0;
	private double secContrIncome = 0;
	private double firStockEndow = 0;
	private double firFutEndow = 0;
	private double firStockComm = 0;
	private double firFutComm = 0;
	private double firContrIncome = 0;
	public ArrayList<User> mPartnerList = new ArrayList<User>();

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getFirstPartnerNum() {
		return firstPartnerNum;
	}

	public void setFirstPartnerNum(int firstPartnerNum) {
		this.firstPartnerNum = firstPartnerNum;
	}

	public int getSecondPartnerNum() {
		return secondPartnerNum;
	}

	public void setSecondPartnerNum(int secondPartnerNum) {
		this.secondPartnerNum = secondPartnerNum;
	}

	public double getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(double monthIncome) {
		this.monthIncome = monthIncome;
	}

	public double getSecStockEndow() {
		return secStockEndow;
	}

	public void setSecStockEndow(double secStockEndow) {
		this.secStockEndow = secStockEndow;
	}

	public double getSecFutEndow() {
		return secFutEndow;
	}

	public void setSecFutEndow(double secFutEndow) {
		this.secFutEndow = secFutEndow;
	}

	public double getSecStockComm() {
		return secStockComm;
	}

	public void setSecStockComm(double secStockComm) {
		this.secStockComm = secStockComm;
	}

	public double getSecFutComm() {
		return secFutComm;
	}

	public void setSecFutComm(double secFutComm) {
		this.secFutComm = secFutComm;
	}

	public double getSecContrIncome() {
		return secContrIncome;
	}

	public void setSecContrIncome(double secContrIncome) {
		this.secContrIncome = secContrIncome;
	}

	public double getFirStockEndow() {
		return firStockEndow;
	}

	public void setFirStockEndow(double firStockEndow) {
		this.firStockEndow = firStockEndow;
	}

	public double getFirFutEndow() {
		return firFutEndow;
	}

	public void setFirFutEndow(double firFutEndow) {
		this.firFutEndow = firFutEndow;
	}

	public double getFirStockComm() {
		return firStockComm;
	}

	public void setFirStockComm(double firStockComm) {
		this.firStockComm = firStockComm;
	}

	public double getFirFutComm() {
		return firFutComm;
	}

	public void setFirFutComm(double firFutComm) {
		this.firFutComm = firFutComm;
	}

	public double getFirContrIncome() {
		return firContrIncome;
	}

	public void setFirContrIncome(double firContrIncome) {
		this.firContrIncome = firContrIncome;
	}

}