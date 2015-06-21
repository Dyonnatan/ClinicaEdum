package service;

import java.io.Serializable;

import javax.inject.Inject;

import util.jpa.Transactional;
import model.Cliente;
import dao.GenericDAO;

public class CadastroClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject()
	private GenericDAO<Cliente> clienteDAO;
	
	@Transactional
	public void salvar(Cliente cliente) throws NegocioException{
		
		this.clienteDAO.salvar(cliente);
	}
	
}
