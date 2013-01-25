package ua.netcrackerteam.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author
 */
@Entity
@Table(name="CONTACT")
public class TableContact {
    @Id
    @Column(name= "idForm")
    private Long idContact;

    @Column(name= "INFO")
    private String info;

    @Id
    @Column(name= "ID_CONTACT_CATEGORY")
    private Long idContactCategory;

    @Id
    @Column(name= "ID_FORM")
    private long idForm;

    public TableContact() {
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getIdContactCategory() {
        return idContactCategory;
    }

    public void setIdContactCategory(Long idContactCategory) {
        this.idContactCategory = idContactCategory;
    }

    public long getIdForm() {
        return idForm;
    }

    public void setIdForm(long idForm) {
        this.idForm = idForm;
    }
}
