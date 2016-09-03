package de.htwsaar.flashcards.model;

import java.util.Date;
/**
 * Daten-Container Klasse (Model) fuer die Karten-Stapel Entitaet.
 * 
 * @author mkohn & Feick Martin
 */
public class Stack {

	private int stackId;
	private String stackName;
	private int typ;
	private String subject;
	private Date creationDate;
	private Date lastEditDate;

	public Stack(int stackId, String stackName, int typ, String subject, Date creationDate, Date lastEditDate) {
		this.stackId = stackId;
		this.stackName = stackName;
		this.typ = typ;
		this.subject = subject;
		this.creationDate = creationDate;
		this.lastEditDate = lastEditDate;
	}
	
	public Stack() {}
	
	public int getStackId() {
		return stackId;
	}

	public void setStackId(int stackId) {
		this.stackId = stackId;
	}

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	@Override
	public String toString() {
		return stackName;
	}
}