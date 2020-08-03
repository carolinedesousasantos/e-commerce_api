/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caroline
 */
@Entity
@Table(name = "sub_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCategories.findAll", query = "SELECT s FROM SubCategories s"),
    @NamedQuery(name = "SubCategories.findById", query = "SELECT s FROM SubCategories s WHERE s.id = :id"),
    @NamedQuery(name = "SubCategories.findByDescription", query = "SELECT s FROM SubCategories s WHERE s.description = :description")})
public class SubCategories implements Serializable {

    @OneToMany(mappedBy = "subCategoryId")
//    private Collection<Products> productsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categories categoryId;

    public SubCategories() {
    }

    public SubCategories(Integer id) {
        this.id = id;
    }

    public SubCategories(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof SubCategories)) {
            return false;
        }
        SubCategories other = (SubCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "control.SubCategories[ id=" + id + " ]";
    }
//
//    @XmlTransient
//    public Collection<Products> getProductsCollection() {
//        return productsCollection;
//    }
//
//    public void setProductsCollection(Collection<Products> productsCollection) {
//        this.productsCollection = productsCollection;
//    }
    
}
