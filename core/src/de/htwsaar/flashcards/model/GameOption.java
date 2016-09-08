package de.htwsaar.flashcards.model;

public class GameOption {
	private int id;
	private String name;
	private String description;
	private int time;
	private int boxOption;
	private boolean sorted;
	private int limit;
	private boolean dateSensitive;
	private int evalType;
	
	public GameOption(int id, String name, String description, int time, int boxOption, boolean sorted, int limit, boolean dateSensitive, int evalType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
		this.boxOption = boxOption;
		this.sorted = sorted;
		this.limit = limit;
		this.dateSensitive = dateSensitive;
		this.evalType = evalType;
	}
	
	public GameOption(int time, int boxOption, boolean sorted, int limit, boolean dateSensitive, int evalType) {
		this.time = time;
		this.boxOption = boxOption;
		this.sorted = sorted;
		this.limit = limit;
		this.dateSensitive = dateSensitive;
		this.evalType = evalType;
	}
	
	public int getEvalType() {
		return evalType;
	}

	public void setEvalType(int evalType) {
		this.evalType = evalType;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isDateSensitive() {
		return dateSensitive;
	}

	public void setDateSensitive(boolean dateSensitive) {
		this.dateSensitive = dateSensitive;
	}

	public GameOption() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getBoxOption() {
		return boxOption;
	}

	public void setBoxOption(int boxOption) {
		this.boxOption = boxOption;
	}

	public boolean isSorted() {
		return sorted;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
