package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface TabelaBD {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId();
	public void setId(long id);
}
