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
    
    //private static final long serialVersionUID = ????
    
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
}
