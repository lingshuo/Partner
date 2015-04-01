package com.hhr360.partner.observer;

public interface IFindPasswordObserver {

	void IFindPasswordObserver_onSuccess(String phone_code);

	void IFindPasswordObserver_onFailed(String msg);
}
