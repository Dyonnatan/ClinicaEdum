package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.cdi.CDIServiceLocator;
import model.Medico;
import model.TabelaBD;
import dao.GenericDAO;

@FacesConverter(forClass=Medico.class)
public class MedicoConverter implements Converter {

	private GenericDAO<Medico> genericDAO;

	public MedicoConverter() {
		this.genericDAO = (GenericDAO<Medico>) CDIServiceLocator.getBean(GenericDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object retorno = null;

		if (value != null) {
			retorno = this.genericDAO.buscarId(Medico.class, new Long(value));
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
