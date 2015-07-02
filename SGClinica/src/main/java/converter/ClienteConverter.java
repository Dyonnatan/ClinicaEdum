package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.cdi.CDIServiceLocator;
import model.TabelaBD;
import model.Cliente;
import dao.GenericDAO;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	private GenericDAO<Cliente> genericDAO;

	@SuppressWarnings("unchecked")
	public ClienteConverter() {
		this.genericDAO = (GenericDAO<Cliente>) CDIServiceLocator.getBean(GenericDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object retorno = null;

		if (value != null) {
			retorno = this.genericDAO.buscarId(Cliente.class, new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Long codigo = ((TabelaBD) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
