package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "comunidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comunidades.findAll", query = "SELECT c FROM Comunidades c"),
    @NamedQuery(name = "Comunidades.findById", query = "SELECT c FROM Comunidades c WHERE c.id = :id"),
    @NamedQuery(name = "Comunidades.findBySlug", query = "SELECT c FROM Comunidades c WHERE c.slug = :slug"),
    @NamedQuery(name = "Comunidades.findByComunidad", query = "SELECT c FROM Comunidades c WHERE c.comunidad = :comunidad"),
    @NamedQuery(name = "Comunidades.findByCapitalId", query = "SELECT c FROM Comunidades c WHERE c.capitalId = :capitalId")})
public class Comunidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "slug")
    private String slug;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "comunidad")
    private String comunidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capital_id")
    private int capitalId;

    public Comunidades() {
    }

    public Comunidades(Integer id) {
        this.id = id;
    }

    public Comunidades(Integer id, String slug, String comunidad, int capitalId) {
        this.id = id;
        this.slug = slug;
        this.comunidad = comunidad;
        this.capitalId = capitalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public int getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(int capitalId) {
        this.capitalId = capitalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comunidades)) {
            return false;
        }
        Comunidades other = (Comunidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "control.Comunidades[ id=" + id + " ]";
    }
    
}
