package ua.netcrackerteam.DAO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="ADVERT")
public class Advert implements Serializable {
    
    //private static final long serialVersionUID = ????
    
    public Advert() {        
    }
    
    @Id
    @Column(name="ID_ADVERT")
    private Long idAdvert;
    
    @OneToOne
    @JoinColumn(name="ID_FORM")                //how about nullable?
    private Form form;
    
    @ManyToOne
    @JoinColumn(name="ID_ADVERT_CATEGORY")       
    private AdvertCategory advertCategory;

    public Long getIdAdvert() {
        return idAdvert;
    }

    public void setIdAdvert(Long idAdvert) {
        this.idAdvert = idAdvert;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public AdvertCategory getAdvertCategory() {
        return advertCategory;
    }

    public void setAdvertCategory(AdvertCategory advertCategory) {
        this.advertCategory = advertCategory;
    }

}
