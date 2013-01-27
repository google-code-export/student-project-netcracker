package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.*;

/**
 * @author
 */
@Entity
@Table(name="CONTACT")
public class TableContact implements Serializable {
    private static final long serialVersionUID = -3235406055681181451L;

    @Id
    @Column(name= "ID_CONTACT")
    private Long idContact;

    @Column(name= "INFO")
    private String info;

    @ManyToOne
    @JoinColumn(name="ID_CONTACT_CATEGORY")
    private TableContactCategory contact;

    @ManyToOne
    @JoinColumn(name = "ID_FORM", nullable = true)
    private TableForm idForm;

    public TableContactCategory getContact() {
        return contact;
    }

    public void setContact(TableContactCategory contact) {
        this.contact = contact;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableContact that = (TableContact) o;

        if (idContact != null ? !idContact.equals(that.idContact) : that.idContact != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContact != null ? idContact.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    public TableForm getIdForm() {
        return idForm;
    }

    public void setIdForm(TableForm currForm) {
        this.idForm = currForm;
    }
}
