package cts.udemy.springboot.exceptions;

import java.util.Date;

public class CustomErrorDetail {

	private Date timespan;
	private String message;
	private String errordetail;

	public CustomErrorDetail(Date timespan, String message, String errordetail) {

		this.timespan = timespan;
		this.message = message;
		this.errordetail = errordetail;
	}

	public Date getTimespan() {
		return timespan;
	}

	public void setTimespan(Date timespan) {
		this.timespan = timespan;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrordetail() {
		return errordetail;
	}

	public void setErrordetail(String errordetail) {
		this.errordetail = errordetail;
	}

}
