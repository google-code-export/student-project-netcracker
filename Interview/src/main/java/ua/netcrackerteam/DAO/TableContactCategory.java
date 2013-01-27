package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author
 */
@Entity
@Table(name="CONTACT_CATEGORY")
public class TableContactCategory implements Serializable {
    private static final long serialVersionUID = -3235404536546181451L;

    @Id
    @Column(name= "ID_CONTACT_CATEGORY")
    private Long idContactCategory;

    @Column(name= "CATEGORY")
    private String category;

    @OneToMany(mappedBy="contact", fetch = FetchType.EAGER )
    private Set<TableContact> contactCategory;

    public Set<TableContact> getContactCategory() {
        return contactCategory;
    }

    public void setContactCategory(Set<TableContact> contactCategory) {
        this.contactCategory = contactCategory;
    }

    public TableContactCategory() {
    }

    public Long getIdContactCategory() {
        return idContactCategory;
    }

    public void setIdContactCategory(Long idContactCategory) {
        this.idContactCategory = idContactCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
