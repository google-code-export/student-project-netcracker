package ua.netcrackerteam.DAO;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="ADVERT")
public class Advert implements Serializable {
    
    private static final long serialVersionUID = -3299906777751181181L;    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "advert_seq_gen")
    @SequenceGenerator(name = "advert_seq_gen", sequenceName = "advert_seq")
    @Column (name = "ID_ADVERT")
    private int idAdvert;
    
    @ManyToOne
    @JoinColumn(name="ID_FORM", nullable = true)
    private Form form;
    
    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_ADVERT_CATEGORY")
    private AdvertCategory advertCategory;
    
    
    
    
    
    public Advert() {        
    }

    public int getIdAdvert() {
        return idAdvert;
    }

    public void setIdAdvert(int idAdvert) {
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

    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advert)) return false;

        Advert advert = (Advert) o;

        if (idAdvert != advert.idAdvert) return false;
        if (!advertCategory.equals(advert.advertCategory)) return false;
        if (!form.equals(advert.form)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdvert;
        result = 31 * result + form.hashCode();
        result = 31 * result + advertCategory.hashCode();
        return result;
    }
}
