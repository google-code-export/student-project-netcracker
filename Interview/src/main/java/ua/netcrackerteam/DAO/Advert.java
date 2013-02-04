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
    
    public Advert() {        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "advert_seq_gen")
    @SequenceGenerator(name = "advert_seq_gen", sequenceName = "advert_seq")
    @Column (name = "ID_ADVERT")
    private Long idAdvert;
    
    @OneToOne
    @JoinColumn(name="ID_FORM", nullable = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (advertCategory != null ? !advertCategory.equals(advert.advertCategory) : advert.advertCategory != null)
            return false;
        if (form != null ? !form.equals(advert.form) : advert.form != null) return false;
        if (idAdvert != null ? !idAdvert.equals(advert.idAdvert) : advert.idAdvert != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdvert != null ? idAdvert.hashCode() : 0;
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (advertCategory != null ? advertCategory.hashCode() : 0);
        return result;
    }
}
