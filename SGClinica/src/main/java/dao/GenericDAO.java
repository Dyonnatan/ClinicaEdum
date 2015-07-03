package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import service.NegocioException;
import util.jpa.Transactional;
import model.TabelaBD;

public class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public T buscarId(Class<T> classe, Long codigo) {
		return ((T) manager.find(classe, codigo));
	}

	public void salvar(T obj) {
		manager.merge(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(Class<?> classe) {
		return manager.createQuery("from " + classe.getSimpleName())
				.getResultList();
	}

	@Transactional
	public void excluir(Class<T> classe, T obj) throws NegocioException {
		obj = buscarId(classe, ((TabelaBD) obj).getId());
		try {
			manager.remove(obj);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(this.getClass().getSimpleName()
					+ " não pode ser excluído.");
		}
	}
}
