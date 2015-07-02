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
	
	public int getDia() {
		return data.get(Calendar.DATE);
	}
	
	public void setDia(int dia) {
		data.set(Calendar.DATE, dia);
	}
	
	public int getMes() {
		return data.get(Calendar.MONTH);
	}
	
	public void setMes(int mes) {
		data.set(Calendar.MONTH, mes);
	}
	
	public int getAno() {
		return data.get(Calendar.YEAR);
	}
	
	public void setAno(int ano) {
		data.set(Calendar.YEAR, ano);
	}
}
