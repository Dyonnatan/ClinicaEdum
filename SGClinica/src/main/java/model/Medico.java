package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "medicos")
public class Medico extends Pessoa {

	private Long crm;
	private List<Convenio> convenios;
	private String especialidade;

	@Column(name = "crm", unique = true, nullable = false)
	public Long getCrm() {
		return crm;
	}

	public void setCrm(Long crm) {
		this.crm = crm;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "medico_convenio", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_convenio"))
	@ForeignKey(name = "fk_med_id",
    inverseName = "fk_conv_id")
	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	@Column(length = 100)
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (int) (prime * result + crm);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (crm != other.crm)
			return false;
		return true;
	}

}
