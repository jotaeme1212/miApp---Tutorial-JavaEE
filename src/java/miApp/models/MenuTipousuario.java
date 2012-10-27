package miApp.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanmanuelmarchese
 */
@Entity
@Table(name = "MENU_TIPOUSUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuTipousuario.findAll", query = "SELECT m FROM MenuTipousuario m"),
    @NamedQuery(name = "MenuTipousuario.findById", query = "SELECT m FROM MenuTipousuario m WHERE m.id = :id"),
    @NamedQuery(name = "MenuTipousuario.findByMenuId", query = "SELECT m FROM MenuTipousuario m WHERE m.menuId = :menuId"),
    @NamedQuery(name = "MenuTipousuario.findByTipousuarioId", query = "SELECT m FROM MenuTipousuario m WHERE m.tipousuarioId = :tipousuarioId"),
    @NamedQuery(name = "MenuTipousuario.findTipousuarioByMenu", query = "SELECT m.tipousuarioId FROM MenuTipousuario m WHERE m.menuId = :menu"),
    @NamedQuery(name = "MenuTipousuario.findByMenuAndTipousuario", query = "SELECT m FROM MenuTipousuario m WHERE m.menuId = :menu AND m.tipousuarioId = :tipo"),
    @NamedQuery(name = "MenuTipousuario.findByCreated", query = "SELECT m FROM MenuTipousuario m WHERE m.created = :created"),
    @NamedQuery(name = "MenuTipousuario.findByUpdated", query = "SELECT m FROM MenuTipousuario m WHERE m.updated = :updated")})
public class MenuTipousuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Column(name = "UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @NotNull(message = "Debe seleccionar un Tipo Usuario")
    @JoinColumn(name = "TIPOUSUARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Tipousuario tipousuarioId;

    @NotNull(message = "Debe seleccionar un Menu")
    @JoinColumn(name = "MENU_ID", referencedColumnName = "ID")
    @ManyToOne
    private Menu menuId;

    // -------------------------- Contructores de la Clase --------------------------

    public MenuTipousuario() {
    }

    public MenuTipousuario(Integer id) {
        this.id = id;
    }

    // -------------------------- Getters y Setters --------------------------

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

    public Tipousuario getTipousuarioId() { return tipousuarioId; }
    public void setTipousuarioId(Tipousuario tipousuarioId) { this.tipousuarioId = tipousuarioId; }

    public Menu getMenuId() { return menuId; }
    public void setMenuId(Menu menuId) { this.menuId = menuId; }

    // -------------------------- Métodos de la Clase --------------------------

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuTipousuario)) {
            return false;
        }
        MenuTipousuario other = (MenuTipousuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipousuarioId.getNombre() + " - " + menuId.getNombre();
    }
    
}
