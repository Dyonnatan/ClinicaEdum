package model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="procedimentos")
public class Procedimento implements TabelaBD {

	private Long id;
	private String informacoes;
	private Date horario;
	private BigDecimal valorCusto;
	private BigDecimal valorMedico;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 255)
	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	@Column(name = "valor_custo", length = 5, precision = 3)
	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	@Column(name = "valor_medico", length = 5, precision = 3)
	public BigDecimal getValorMedico() {
		return valorMedico;
	}

	public void setValorMedico(BigDecimal valorMedico) {
		this.valorMedico = valorMedico;
	}

}
