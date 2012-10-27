package miApp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import miApp.models.Menu;
import miApp.models.MenuTipousuario;
import miApp.models.Tipousuario;

/**
 *
 * @author juanmanuelmarchese
 */
@Stateless
public class MenuTipousuarioDAO extends AbstractDAO<MenuTipousuario> {
    @PersistenceContext(unitName = "miAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuTipousuarioDAO() {
        super(MenuTipousuario.class);
    }

    public Boolean findByMenuAndTipousuario(Menu menu, Tipousuario tipo) {
        try {
            Query query = em.createNamedQuery("MenuTipousuario.findByMenuAndTipousuario");
            query.setParameter("menu", menu);
            query.setParameter("tipo", tipo);
            query.getSingleResult();
            return true;
        }
        catch (Exception e) { return false; }
    } // Fin public Boolean findByMenuAndTipousuario


}
