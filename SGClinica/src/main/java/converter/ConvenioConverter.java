package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.cdi.CDIServiceLocator;
import model.Convenio;
import model.TabelaBD;
import dao.GenericDAO;

@FacesConverter(forClass=Convenio.class)
public class ConvenioConverter implements Converter {

	private GenericDAO<Convenio> genericDAO;

	@SuppressWarnings("unchecked")
	public ConvenioConverter() {
		this.genericDAO = (GenericDAO<Convenio>) CDIServiceLocator.getBean(GenericDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object retorno = null;

		if (value != null) {
			retorno = this.genericDAO.buscarId(Convenio.class, new Long(value));
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
