package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Convenio;
import util.jpa.Transactional;
import dao.GenericDAO;

public class CadastroConvenioService  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GenericDAO<Convenio> convDAO;
	
	@Transactional
	public void salvar(Convenio convenio) throws NegocioException{
		
		this.convDAO.salvar(convenio);
	}
	
}
