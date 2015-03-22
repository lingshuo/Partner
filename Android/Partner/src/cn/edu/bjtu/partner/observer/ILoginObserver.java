package cn.edu.bjtu.partner.observer;

/**
 * @author Tans
 */
public interface ILoginObserver {

	void ILoginObserver_succeed(int userId, int phoneNum, int inviteCode);

	void ILoginObaserver_failed(String errorMsg);

}
