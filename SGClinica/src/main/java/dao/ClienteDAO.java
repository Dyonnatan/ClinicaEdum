package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import service.NegocioException;
import util.jpa.Transactional;
import model.TabelaBD;
import model.Cliente;

public class ClienteDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	//comentario de teste
	public Cliente buscarPeloCodigo(Long codigo) {
		//ainda não sei se isso funciona = GenericDAO.class
		return manager.find(Cliente.class, codigo);
	}
	
	public void salvar(Cliente obj) {
		((Cliente)obj).setCpf("4343");
		
		manager.merge(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		return manager.createQuery("from Cliente").getResultList();
	}
	
	@Transactional
	public void excluir(Cliente obj) throws NegocioException {
		obj = buscarPeloCodigo(((TabelaBD)obj).getId());
		try {
			manager.remove(obj);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(this.getClass().getSimpleName()+" não pode ser excluído.");
		}
	}
}
