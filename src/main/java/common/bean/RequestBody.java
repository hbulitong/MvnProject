package common.bean;
/**
 * ������Ϣ���͵�RequestBody
 * @author Administrator
 *
 */
public class RequestBody {
	private String appId; //��Ϣ���͵�appid
	private String appey; //��Ϣ���͵�appkey
	private String masterSecret; //��Ϣ���͵�masterSecret
	private String phoneType; //�ֻ�����IOS OR android
	private String clientId; //�����û�Ψһ��ʶ
	private MessageUser[] user;
	public MessageUser[] getUser() {
		return user;
	}
	public void setUser(MessageUser[] user) {
		this.user = user;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppey() {
		return appey;
	}
	public void setAppey(String appey) {
		this.appey = appey;
	}
	public String getMasterSecret() {
		return masterSecret;
	}
	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
}
