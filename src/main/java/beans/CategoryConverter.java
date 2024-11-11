package beans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import models.Category;
import services.CategoryService;


@FacesConverter(value = "categoryConverter") 
public class CategoryConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
		return null;
		}
		return new CategoryService().getById(Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Category) {
		    return String.valueOf(((Category) value).getId());
		} else {
		    return null;
		}
		
	}

}
