package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Consulta;
import util.jpa.Transactional;
import dao.GenericDAO;

public class CadastroConsultaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GenericDAO<Consulta> convDAO;
	
	@Transactional
	public void salvar(Consulta consulta) throws NegocioException{
		
		this.convDAO.salvar(consulta);
	}
	
}
