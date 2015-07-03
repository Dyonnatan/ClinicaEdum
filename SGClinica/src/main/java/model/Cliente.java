package model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

	private BigDecimal limite;
	private List<Convenio> convenios;
	private List<String> telefonesCliente;

	@NotNull(message = "é obrigatório")
	@Column(name = "limite", nullable = false, precision = 10, scale = 2)
	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cliente_convenio", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_convenio"))
	@ForeignKey(name = "fk_cli_id", inverseName = "fk_conv_id")
	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "telefones_cliente", joinColumns = @JoinColumn(name = "id_pessoa"))
	@Column(name = "telefone", length = 15)
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	public List<String> getTelefonesCliente() {
		return telefonesCliente;
	}

	public void setTelefonesCliente(List<String> telefonesCliente) {
		this.telefonesCliente = telefonesCliente;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Cliente [limite=" + limite + ", getNome()=" + getNome()
				+ ", getSexo()=" + getSexo() + ", getCpf()=" + getCpf()
				+ ", getRg()=" + getRg() + ", getOrgao()=" + getOrgao()
				+ ", getDataNascimentoFormat()=" + getDataNascimentoFormat()
				+ ", getMunicipio()=" + getMunicipio() + ", getEndereco()="
				+ getEndereco() + "]";
	}

}
