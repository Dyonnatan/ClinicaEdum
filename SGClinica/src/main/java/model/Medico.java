package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "medicos")
public class Medico extends Pessoa {

	private Integer crm;
	private List<Convenio> convenios;
	private String especialidade;

	@NotNull @Min(value=1)
	@Column(name = "crm", unique = true, nullable = false, length = 20)
	public Integer getCrm() {
		return crm;
	}

	public void setCrm(Integer crm) {
		this.crm = crm;
	}

	@ManyToMany(cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
	@JoinTable(name = "medico_convenio", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_convenio"))
	@ForeignKey(name = "fk_med_id", inverseName = "fk_conv_id") 
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
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
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
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		return true;
	}

}
