package com.hhr360.partner.bean;

public class User {

	private int id;
	private String accountName = "";
	private String invitationCode = "";
	private String Phone = "";
	private String hhrLevel = "";
	private int firstlyPartnerNum = 0;
	private int secondlyPartnerNum = 0;
	private String extendPartnerNum = "";
	private String interestReturnCoefficient = "";
	private String chargeReturnCoefficient = "";
	private String dailyIncome = "";
	private String monthlyIncome = "";

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getHhrLevel() {
		return hhrLevel;
	}

	public void setHhrLevel(String hhrLevel) {
		this.hhrLevel = hhrLevel;
	}

	public int getFirstlyPartnerNum() {
		return firstlyPartnerNum;
	}

	public void setFirstlyPartnerNum(int firstlyPartnerNum) {
		this.firstlyPartnerNum = firstlyPartnerNum;
	}

	public String getExtendPartnerNum() {
		return extendPartnerNum;
	}

	public void setExtendPartnerNum(String extendPartnerNum) {
		this.extendPartnerNum = extendPartnerNum;
	}

	public String getInterestReturnCoefficient() {
		return interestReturnCoefficient;
	}

	public void setInterestReturnCoefficient(String interestReturnCoefficient) {
		this.interestReturnCoefficient = interestReturnCoefficient;
	}

	public String getChargeReturnCoefficient() {
		return chargeReturnCoefficient;
	}

	public void setChargeReturnCoefficient(String chargeReturnCoefficient) {
		this.chargeReturnCoefficient = chargeReturnCoefficient;
	}

	public String getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(String dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getSecondlyPartnerNum() {
		return secondlyPartnerNum;
	}

	public void setSecondlyPartnerNum(int secondlyPartnerNum) {
		this.secondlyPartnerNum = secondlyPartnerNum;
	}

}
