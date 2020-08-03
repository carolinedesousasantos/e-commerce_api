package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "resetpasswordtokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resetpasswordtokens.findAll", query = "SELECT r FROM Resetpasswordtokens r"),
    @NamedQuery(name = "Resetpasswordtokens.findById", query = "SELECT r FROM Resetpasswordtokens r WHERE r.id = :id"),
    @NamedQuery(name = "Resetpasswordtokens.findByUserId", query = "SELECT r FROM Resetpasswordtokens r WHERE r.userId = :userId"),
    @NamedQuery(name = "Resetpasswordtokens.findByToken", query = "SELECT r FROM Resetpasswordtokens r WHERE r.token = :token"),
    @NamedQuery(name = "Resetpasswordtokens.findByDate", query = "SELECT r FROM Resetpasswordtokens r WHERE r.creation_date = :creation_date")})
public class Resetpasswordtokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "token")
    private String token;

    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creation_date;

    public Resetpasswordtokens() {
    }

    public Resetpasswordtokens(Integer id) {
        this.id = id;
    }

    public Resetpasswordtokens(Integer id, int userId, String token, LocalDateTime creation_date) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.creation_date = creation_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDate() {
        return creation_date;
    }

    public void setDate(LocalDateTime creation_date) {
        this.creation_date = creation_date;
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
        if (!(object instanceof Resetpasswordtokens)) {
            return false;
        }
        Resetpasswordtokens other = (Resetpasswordtokens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "control.Resetpasswordtokens[ id=" + id + " ]";
    }
    
}
