package miApp.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanmanuelmarchese
 */
@Entity
@Table(name = "TIPOUSUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findById", query = "SELECT t FROM Tipousuario t WHERE t.id = :id"),
    @NamedQuery(name = "Tipousuario.findByNombre", query = "SELECT t FROM Tipousuario t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipousuario.findByCreated", query = "SELECT t FROM Tipousuario t WHERE t.created = :created"),
    @NamedQuery(name = "Tipousuario.findByUpdated", query = "SELECT t FROM Tipousuario t WHERE t.updated = :updated")})
public class Tipousuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Size(min = 1, max = 100, message="Debe ingresar el Nombre")
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    // -------------------------- Contructores de la Clase --------------------------

    public Tipousuario() {
    }

    public Tipousuario(Integer id) {
        this.id = id;
    }

    // -------------------------- Getters y Setters --------------------------

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

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
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }

}
