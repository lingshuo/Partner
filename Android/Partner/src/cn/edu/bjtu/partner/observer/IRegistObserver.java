package cn.edu.bjtu.partner.observer;

/**
 * @author Tans
 */
public interface IRegistObserver {

	void IRegistObserver_succeed(int userId, int phoneNum, int inviteCode);

	void IRegistObaserver_failed(String errorMsg);
}
