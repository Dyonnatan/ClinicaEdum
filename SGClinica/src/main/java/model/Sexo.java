package model;

public enum Sexo {
	NI("Não informado"), M("Masculino"), F("Feminino");

	String sexo;

	private Sexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return sexo;
	}
}
