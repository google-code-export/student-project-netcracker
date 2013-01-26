package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.*;

/**
 * @author
 */
@Entity
@Table(name="CONTACT")
public class TableContact implements Serializable {

    @Id
    @Column(name= "ID_CONTACT")
    private Long idContact;

    @Column(name= "INFO")
    private String info;

    @Column(name= "ID_CONTACT_CATEGORY")
    private Long idContactCategory;

    @ManyToOne
    @JoinColumn(name = "ID_FORM")
    private TableForm idForm;

    public TableForm getIdFormList() {
        return idForm;
    }

    public void setIdFormList(TableForm idFormList) {
        this.idForm = idFormList;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableContact that = (TableContact) o;

        if (idContact != null ? !idContact.equals(that.idContact) : that.idContact != null) return false;
        if (idContactCategory != null ? !idContactCategory.equals(that.idContactCategory) : that.idContactCategory != null)
            return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContact != null ? idContact.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (idContactCategory != null ? idContactCategory.hashCode() : 0);
        return result;
    }

    public TableForm getIdForm() {
        return idForm;
    }

    public void setIdForm(TableForm currForm) {
        this.idForm = currForm;
    }
}
