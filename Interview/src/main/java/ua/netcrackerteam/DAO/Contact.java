package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.*;

/**
 * @author
 */
@Entity
@Table(name="CONTACT")
public class Contact implements Serializable {
    private static final long serialVersionUID = -3235406055681181451L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contact_seq_gen")
    @SequenceGenerator(name = "contact_seq_gen", sequenceName = "contact_seq")
    @Column(name= "ID_CONTACT")
    private int idContact;

    @Column(name= "INFO")
    private String info;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_CONTACT_CATEGORY")
    private ContactCategory contactCategory;    

    @ManyToOne
    @JoinColumn(name = "ID_FORM")
    private Form idForm;

    public ContactCategory getContactCategory() {
        return contactCategory;
    }

    public void setContactCategory(ContactCategory contactCategory) {
        this.contactCategory = contactCategory;
    }

    public Form getIdFormList() {
        return idForm;
    }

    public void setIdFormList(Form idFormList) {
        this.idForm = idFormList;
    }

    public Contact() {
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
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
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (idContact != contact.idContact) return false;
        if (!contactCategory.equals(contact.contactCategory)) return false;
        if (!idForm.equals(contact.idForm)) return false;
        if (!info.equals(contact.info)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContact;
        result = 31 * result + info.hashCode();
        result = 31 * result + contactCategory.hashCode();
        result = 31 * result + idForm.hashCode();
        return result;
    }

    public Form getIdForm() {
        return idForm;
    }

    public void setIdForm(Form currForm) {
        this.idForm = currForm;
    }
}
