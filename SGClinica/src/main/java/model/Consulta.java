package model;

import java.util.Date;
import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="consultas")
public class Consulta implements TabelaBD{

	private Long id;
	private Queue<Procedimento> procedimentos;
	private Date horario;
	private Convenio convenio;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,targetEntity=Procedimento.class)   
	@JoinColumn(name="id_procedimento") 
	public Queue<Procedimento> getProcedimentos() {
		return procedimentos;
	}
	public void setProcedimentos(Queue<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	@ManyToOne
	@JoinColumn(name = "id_convenio")
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	
}
