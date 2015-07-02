package service;

import java.io.Serializable;

import javax.inject.Inject;

import util.jpa.Transactional;
import model.Medico;
import dao.GenericDAO;

public class CadastroMedicoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GenericDAO<Medico> medicoDAO;
	
	@Transactional
	public void salvar(Medico medico) throws NegocioException{
		
		this.medicoDAO.salvar(medico);
	}
	
}
