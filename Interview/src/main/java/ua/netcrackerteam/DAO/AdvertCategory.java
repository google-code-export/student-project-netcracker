/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.netcrackerteam.DAO;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="ADVERT_CATEGORY")
public class AdvertCategory implements Serializable {
    private static final long serialVersionUID = -3254111777751188881L;
    
    public AdvertCategory() {        
    }
    
    @Id
    @Column(name="ID_ADVERT_CATEGORY")
    private Long idAdvertCategory;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @OneToMany(mappedBy="advertCategory", fetch = FetchType.EAGER)
    private Set<Advert> adverts;

    public Long getIdAdvertCategory() {
        return idAdvertCategory;
    }

    public void setIdAdvertCategory(Long idAdvertCategory) {
        this.idAdvertCategory = idAdvertCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertCategory that = (AdvertCategory) o;

        if (adverts != null ? !adverts.equals(that.adverts) : that.adverts != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (idAdvertCategory != null ? !idAdvertCategory.equals(that.idAdvertCategory) : that.idAdvertCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdvertCategory != null ? idAdvertCategory.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (adverts != null ? adverts.hashCode() : 0);
        return result;
    }
}
