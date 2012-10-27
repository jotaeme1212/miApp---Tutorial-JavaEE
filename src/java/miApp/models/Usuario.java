package miApp.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanmanuelmarchese
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByCreated", query = "SELECT u FROM Usuario u WHERE u.created = :created"),
    @NamedQuery(name = "Usuario.findByUpdated", query = "SELECT u FROM Usuario u WHERE u.updated = :updated"),
    @NamedQuery(name = "Usuario.findByTipousuarioId", query = "SELECT u FROM Usuario u WHERE u.tipousuarioId = :tipousuarioId"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")})
public class Usuario implements Serializable {

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

    @Size(min = 1, max = 100, message="Debe ingresar el Login")
    @Column(name = "LOGIN")
    private String login;
    
    @Size(min = 1, max = 100, message="Debe ingresar la Clave")
    @Column(name = "CLAVE")
    private String clave;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(min = 1, max = 100, message="Debe ingresar el eMail")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "Debe seleccionar un Tipo Usuario")
    @JoinColumn(name = "TIPOUSUARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Tipousuario tipousuarioId;

    // -------------------------- Contructores de la Clase --------------------------

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    // -------------------------- Getters y Setters --------------------------

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Tipousuario getTipousuarioId() { return tipousuarioId; }
    public void setTipousuarioId(Tipousuario tipousuarioId) { this.tipousuarioId = tipousuarioId; }

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
        if (!(object instanceof Usuario)) { return false; }
        Usuario other = (Usuario) object;
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
