package com.hhr360.partner.bean;

public class Partner {

	private int id;
	private String accountName = "";
	private String invitationCode = "";
	private String Phone = "";
	private String hhrLevel = "";
	private String firstlyPartnerNum = "";
	private String secondlyPartnerNum = "";
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

	public String getFirstlyPartnerNum() {
		return firstlyPartnerNum;
	}

	public void setFirstlyPartnerNum(String firstlyPartnerNum) {
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

	public String getSecondlyPartnerNum() {
		return secondlyPartnerNum;
	}

	public void setSecondlyPartnerNum(String secondlyPartnerNum) {
		this.secondlyPartnerNum = secondlyPartnerNum;
	}

}
