package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Cliente;
import dao.GenericDAO;

public class CadastroClienteService implements Serializable{

	@Inject
	private GenericDAO clienteDAO;
	
	public void salvar(Cliente cliente) throws NegocioException{
		
		this.clienteDAO.salvar(cliente);
	}
	
}
