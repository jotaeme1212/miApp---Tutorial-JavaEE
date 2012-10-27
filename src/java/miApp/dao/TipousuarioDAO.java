package miApp.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miApp.models.Tipousuario;

/**
 *
 * @author juanmanuelmarchese
 */
@Stateless
public class TipousuarioDAO extends AbstractDAO<Tipousuario> {

    @PersistenceContext(unitName = "miAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipousuarioDAO() {
        super(Tipousuario.class);
    }
    
}
