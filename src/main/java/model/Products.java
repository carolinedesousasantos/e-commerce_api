/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caroline
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findByrealPrice", query = "SELECT p FROM Products p WHERE p.realPrice = :realPrice"),
    @NamedQuery(name = "Products.findByOffer", query = "SELECT p FROM Products p WHERE p.offer = :offer"),
    @NamedQuery(name = "Products.findByImageSrc", query = "SELECT p FROM Products p WHERE p.imageSrc = :imageSrc")})
public class Products implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "real_price")
    private float realPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "offer")
    private float offer;

    @Size(max = 500)
    @Column(name = "imgSlide1")
    private String imgSlide1;
    @Size(max = 500)
    @Column(name = "imgSlide2")
    private String imgSlide2;
    @Size(max = 500)
    @Column(name = "imgSlide3")
    private String imgSlide3;
    @Size(max = 500)
    @Column(name = "imgSlide4")
    private String imgSlide4;

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
    @Size(max = 2000)
    @Column(name = "image_src")
    private String imageSrc;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Categories categoryId;
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    @ManyToOne
    private SubCategories subCategoryId;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String description, float price) {
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public SubCategories getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(SubCategories subCategoryId) {
        this.subCategoryId = subCategoryId;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "control.Products[ id=" + id + " ]";
    }

    public String getImgSlide1() {
        return imgSlide1;
    }

    public void setImgSlide1(String imgSlide1) {
        this.imgSlide1 = imgSlide1;
    }

    public String getImgSlide2() {
        return imgSlide2;
    }

    public void setImgSlide2(String imgSlide2) {
        this.imgSlide2 = imgSlide2;
    }

    public String getImgSlide3() {
        return imgSlide3;
    }

    public void setImgSlide3(String imgSlide3) {
        this.imgSlide3 = imgSlide3;
    }

    public String getImgSlide4() {
        return imgSlide4;
    }

    public void setImgSlide4(String imgSlide4) {
        this.imgSlide4 = imgSlide4;
    }

    public float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
    }

    public float getOffer() {
        return offer;
    }

    public void setOffer(float offer) {
        this.offer = offer;
    }

    public double getPriceWithDiscount() {

        double PriceWithDiscount = this.getRealPrice() - ((this.getOffer() / 100) * this.getRealPrice());

        BigDecimal bd = new BigDecimal(PriceWithDiscount).setScale(2, RoundingMode.HALF_UP);
        double priceFormatted = bd.doubleValue();
        System.out.println("priceFormatted"+priceFormatted);
        return priceFormatted;
    }

}
