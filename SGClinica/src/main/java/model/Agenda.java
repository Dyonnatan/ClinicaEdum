package model;

import java.util.Calendar;

public class Agenda implements TabelaBD{

	private Long id;
	Calendar data;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
