package de.htwsaar.flashcards.model;

//Feick Martin
import java.sql.Date;

public class Stack {

	private int stackId;
	private String stackName;
	private int typ;
	private String subject;
	private Date creationDate;
	private Date lastEditDate;
	private Date lastAccessDate;
	private Date NextAccessDate;

	public Stack(int stackId, String stackName, int typ, String subject, Date creationDate, Date lastEditDate,
			Date lastAccessDate, Date nextAccessDate) {
		this.stackId = stackId;
		this.stackName = stackName;
		this.typ = typ;
		this.subject = subject;
		this.creationDate = creationDate;
		this.lastEditDate = lastEditDate;
		this.lastAccessDate = lastAccessDate;
		this.NextAccessDate = nextAccessDate;
	}

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

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Date getNextAccessDate() {
		return NextAccessDate;
	}

	public void setNextAccessDate(Date nextAccessDate) {
		NextAccessDate = nextAccessDate;
	}

	@Override
	public String toString() {
		return "Stack [stackId=" + stackId + ", stackName=" + stackName + ", typ=" + typ + ", subject=" + subject
				+ ", creationDate=" + creationDate + ", lastEditDate=" + lastEditDate + ", lastAccessDate="
				+ lastAccessDate + ", NextAccessDate=" + NextAccessDate + "]";
	}

}