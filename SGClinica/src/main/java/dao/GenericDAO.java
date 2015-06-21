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

public class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// comentario de teste
	public T buscarPeloCodigo(Class<T> classe, Long codigo) {
		// ainda não sei se isso funciona = GenericDAO.class
		return ((T) manager.find(classe, codigo));
	}

	public void salvar(T obj) {
		manager.merge(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(Class<T> classe) {
		return manager.createQuery("from " + classe.getSimpleName())
				.getResultList();
	}

	@Transactional
	public void excluir(Class<T> classe, T obj) throws NegocioException {
		obj = buscarPeloCodigo(classe, ((TabelaBD) obj).getId());
		try {
			manager.remove(obj);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(this.getClass().getSimpleName()
					+ " não pode ser excluído.");
		}
	}
}
