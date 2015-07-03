package model;

import java.util.Collection;
import java.util.Date;

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
	private Cliente cliente;
	private Medico medico;
	private Collection<Procedimento> procedimentos;
	private String status;
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
	
	@ManyToOne
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@ManyToOne
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,targetEntity=Procedimento.class)   
	@JoinColumn(name="id_consulta") 
	public Collection<Procedimento> getProcedimentos() {
		return procedimentos;
	}
	public void setProcedimentos(Collection<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_convenio")
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
