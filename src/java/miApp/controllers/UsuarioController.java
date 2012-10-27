package miApp.controllers;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import miApp.dao.UsuarioDAO;
import miApp.models.Usuario;

/**
 *
 * @author juanmanuelmarchese
 */
@ManagedBean
@SessionScoped
public class UsuarioController {

    @EJB
    private UsuarioDAO dao;
    private Usuario selected;

    // ---------------------- Constructor de la Clase ----------------------

    public UsuarioController() {
    }

    public Usuario getSelected() {
        if (selected == null) { selected = new Usuario(); }
        return selected;
    } // Fin public Usuario getSelected

    // ---------------------- Métodos del Managed Bean ----------------------

    public String index() {
        return "/usuario/index";
    } // Fin public String index

    public List<Usuario> listado() {        
        return dao.findAll();
    } // Fin public List<Usuario> listado

    public String create() {
        selected = new Usuario();
        return "/usuario/new";
    } // Fin public String create

    public String agregar() {
        Date d = new Date();
        selected.setCreated(d);
        selected.setUpdated(d);
        dao.create(selected);
        return "/usuario/index";
    } // Fin public String agregar

    public String edit(int codigo) {
        selected = dao.find(codigo);
        return "/usuario/edit";
    } // Fin public Tipousuario edit

    public String guardar() {
        Date d = new Date();
        selected.setUpdated(d);
        dao.edit(selected);
        return "/usuario/index";
    } // Fin public String guardar

    public String eliminar(int codigo) {
        selected = dao.find(codigo);
        dao.remove(selected);
        return "/usuario/index";
    } // Fin public String eliminar

}
