package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import service.NegocioException;
import util.jpa.Transactional;
import model.BancoDeDados;;

public class GenericDAO<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public T buscarPeloCodigo(Long codigo) {
		//ainda não sei se isso funciona = GenericDAO.class
		return (T) manager.find(GenericDAO.class, codigo);
	}
	
	public void salvar(T obj) {
		manager.merge(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		return manager.createQuery("from "+this.getClass().getSimpleName()).getResultList();
	}
	
	@Transactional
	public void excluir(T obj) throws NegocioException {
		obj = buscarPeloCodigo(((BancoDeDados)obj).getId());
		try {
			manager.remove(obj);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(this.getClass().getSimpleName()+" não pode ser excluído.");
		}
	}
}
