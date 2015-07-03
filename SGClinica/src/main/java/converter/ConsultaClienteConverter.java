package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Cliente;
import dao.GenericDAO;

@FacesConverter(value = "consCliConverter")  
public class ConsultaClienteConverter implements Converter {
		  
	    GenericDAO<Cliente> dao = new GenericDAO<Cliente>();
 
	    @Override  
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {  
	        Cliente c = dao.buscarId(Cliente.class, Long.parseLong(value) );  
	        return c;  
	    }  
	  
	    @Override  
	    public String getAsString(FacesContext context, UIComponent component, Object value) {  
	        Cliente c = (Cliente) value;  
	        return String.valueOf( c.getId() );  
	    }  
	  
	}  
