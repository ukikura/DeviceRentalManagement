package app.form;

import lombok.Data;

@Data
public class DeviceSearchForm {
	private String search;
	private int type;
	private boolean brokenFlag;
	private int memory;
	private int capacity;
	private int graphicBoard;
	private String room;
	private int status;
	
	
}
