package beans;

import java.util.LinkedList;

public class AlertListBean {

	private LinkedList<AlertBean> alerts;
	
	public AlertListBean() {
		alerts = new LinkedList<AlertBean>();
	}
	
	public void addAlert(AlertBean a) {
		alerts.add(a);
	}
	
	public LinkedList<AlertBean> getAlerts() {
		return alerts;
	}
	
	public boolean isEmpty() {
		return alerts.isEmpty();
	}
	
	public int getNumAlerts() {
		return alerts.size();
	}
	
	//TODO stylize
	public String display() {
		String strConcat = "";
		if (alerts.isEmpty()) {
			strConcat = "No new alerts.";
		} else {
			for (AlertBean ab : alerts)
				strConcat += ab.display();
		}
		return strConcat;
	}
}
