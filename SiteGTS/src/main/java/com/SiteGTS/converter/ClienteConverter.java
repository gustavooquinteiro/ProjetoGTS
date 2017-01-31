package com.SiteGTS.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.SiteGTS.model.Cliente;
import com.SiteGTS.repository.Clientes;
import com.SiteGTS.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	private Clientes clientes;

	public ClienteConverter() {
		clientes = CDIServiceLocator.getBean(Clientes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Cliente retorno = null; 
		if (value != null) {
			retorno = this.clientes.porId(new Long(value));
			return retorno;
		}
		return retorno; 
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		return "";
	}

}