package ua.netcrackerteam.DAO.Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author
 */
@Entity
@Table(name="CONTACT_CATEGORY")
public class ContactCategory implements Serializable {
    private static final long serialVersionUID = -3235404536546181451L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contact_category_seq_gen")
    @SequenceGenerator(name = "contact_category_seq_gen", sequenceName = "contact_category_seq")
    @Column(name= "ID_CONTACT_CATEGORY")
    private int idContactCategory;

    @Column(name= "CATEGORY")
    private String category;

    /*@OneToMany(mappedBy= "contactCategory", fetch = FetchType.EAGER )
    private Set<Contact> contacts;      

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }*/

    public ContactCategory() {
    }

    public Integer getIdContactCategory() {
        return idContactCategory;
    }

    public void setIdContactCategory(int idContactCategory) {
        this.idContactCategory = idContactCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactCategory)) return false;

        ContactCategory that = (ContactCategory) o;

        if (idContactCategory != that.idContactCategory) return false;
        if (!category.equals(that.category)) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = idContactCategory;
        result = 31 * result + category.hashCode();
        return result;
    }
}
