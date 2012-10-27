package miApp.controllers;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import miApp.dao.TipousuarioDAO;
import miApp.models.Tipousuario;

/**
 *
 * @author juanmanuelmarchese
 */
@ManagedBean
@SessionScoped
public class TipousuarioController {

    @EJB
    private TipousuarioDAO dao;
    private Tipousuario selected;

    // ---------------------- Constructor de la Clase ----------------------

    public TipousuarioController() {
    }

    public Tipousuario getSelected() {
        if (selected == null) { selected = new Tipousuario(); }
        return selected;
    }

    // ---------------------- Métodos del Managed Bean ----------------------

    public String index() {
        return "/tipousuario/index";
    }

    public List<Tipousuario> listado() {
        return dao.findAll();
    }

    public String create() {
        selected = new Tipousuario();
        return "/tipousuario/new";
    } // Fin public String create

    public String agregar() {
        Date d = new Date();
        selected.setCreated(d);
        selected.setUpdated(d);
        dao.create(selected);
        return "/tipousuario/index";
    } // Fin public String agregar

    public String edit(int codigo) {
        selected = dao.find(codigo);
        return "/tipousuario/edit";
    } // Fin public Tipousuario edit

    public String guardar() {
        Date d = new Date();
        selected.setUpdated(d);
        dao.edit(selected);
        return "/tipousuario/index";
    } // Fin public String guardar

    public String eliminar(int codigo) {
        selected = dao.find(codigo);
        try { dao.remove(selected); }
        catch (Exception e) { SessionUtil.addErrorMessage("No se puede eliminar, posibles datos asociados"); }
        return "/tipousuario/index";
    } // Fin public String eliminar

    // --------------------- Métodos de Ayuda para acceder al Bean por otras Clases ---------------------

    public SelectItem[] getItemsAvailableSelectOne() {
        return getSelectItems(dao.findAll(), true);
    }

    // Genera una lista con los items seleccionados (uno o muchos según selectOne). Para tablas relacionadas.
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {

        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;

    }

    // ------------------ Clase para conversiones, se deben implementar todos los métodos ------------------
    @FacesConverter(forClass = Tipousuario.class)
    public static class TipousuarioControllerConverter implements Converter {

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        } // Fin java.lang.Integer getKey

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        } // Fin String getStringKey

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) { return null; }
            TipousuarioController controller = (TipousuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipousuarioController");
            return controller.dao.find(getKey(value));
        } // Fin public Object getAsObject

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) { return null; }
            if (object instanceof Tipousuario) {
                Tipousuario o = (Tipousuario) object;
                return getStringKey(o.getId());
            }
            else { throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipousuarioController.class.getName()); }
        } // Fin public String getAsString

    } // Fin public static class TipousuarioControllerConverter implements Converter

}
