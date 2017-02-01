package com.SiteGTS.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.repository.Tickets;
import com.SiteGTS.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Ticket.class)
public class TicketConverter implements Converter {

	private Tickets tickets;

	public TicketConverter() {
		tickets = CDIServiceLocator.getBean(Tickets.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Ticket retorno = null;
		if (value != null) {
			retorno = this.tickets.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Ticket ticket = (Ticket) value;
			return ticket.getId() == null ? null : ticket.getId().toString();
		}
		return "";
	}

}