package com.example.presentation.models;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// https://datatracker.ietf.org/doc/html/rfc7807
// Content-Type: application/problem+json
@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
	private String type;
	@XmlElement(required = true)
	private String title;
	@XmlElement()
	private String detail;
	@XmlElement(required = true)
	private int status;
	@XmlElement()
	private Map<String, String> errors;

	public ErrorMessage() {
		this(500, "INTERNAL SERVER ERROR", null, null);
	}

	public ErrorMessage(int status, String title) {
		this(status, title, null, null);
	}

	public ErrorMessage(int status, String title, String detail) {
		this(status, title, detail, null);
	}

	public ErrorMessage(int status, String title, Map<String, String> errors) {
		this(status, title, null, errors);
	}

	public ErrorMessage(int status, String title, String detail, Map<String, String> errors) {
		this(getTypeURL(status), status, title, detail, errors);
	}

	public ErrorMessage(String type, int status, String title, String detail, Map<String, String> errors) {
		super();
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.status = status;
		this.errors = errors;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}

	public int getStatus() {
		return status;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public static String getTypeURL(int status) {
		switch (status) {
		case 400:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.1";
		case 401:
			return "https://datatracker.ietf.org/doc/html/rfc7235#section-3.1";
		case 402:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.2";
		case 403:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.3";
		case 404:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4";
		case 405:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.5";
		case 406:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.6";
		case 407:
			return "https://datatracker.ietf.org/doc/html/rfc7235#section-3.2";
		case 408:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.7";
		case 409:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8";
		case 410:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.9";
		case 411:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.10";
		case 412:
			return "https://datatracker.ietf.org/doc/html/rfc7232#section-4.2";
		case 413:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.11";
		case 414:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.12";
		case 415:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.13";
		case 416:
			return "https://datatracker.ietf.org/doc/html/rfc7233#section-4.4";
		case 417:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.14";
		case 426:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.15";
		default:
			return "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5";
		}
	}
}
