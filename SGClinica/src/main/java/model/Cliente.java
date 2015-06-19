package model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity

public class Cliente extends Pessoa {

	private BigDecimal limite;

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
