package ua.netcrackerteam.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Contact")
public class DAOContacts implements Serializable {
    @Id
    @Column(name= "ID_FORM")
    private Long ID_CONTACT;
    @Column(name= "ID_FORM")
    private String INFO;
    @Id
    @Column(name= "ID_FORM")
    private Long ID_CONTACT_CATEGORY;
    @Id
    @Column(name= "ID_FORM")
    private long ID_FORM;

    public DAOContacts() {
    }

    public DAOContacts(Long ID_CONTACT, String INFO, Long ID_CONTACT_CATEGORY, long ID_FORM) {
        this.ID_CONTACT = ID_CONTACT;
        this.INFO = INFO;
        this.ID_CONTACT_CATEGORY = ID_CONTACT_CATEGORY;
        this.ID_FORM = ID_FORM;
    }

    public Long getID_CONTACT() {
        return ID_CONTACT;
    }

    public void setID_CONTACT(Long ID_CONTACT) {
        this.ID_CONTACT = ID_CONTACT;
    }

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

    public Long getID_CONTACT_CATEGORY() {
        return ID_CONTACT_CATEGORY;
    }

    public void setID_CONTACT_CATEGORY(Long ID_CONTACT_CATEGORY) {
        this.ID_CONTACT_CATEGORY = ID_CONTACT_CATEGORY;
    }

    public long getID_FORM() {
        return ID_FORM;
    }

    public void setID_FORM(long ID_FORM) {
        this.ID_FORM = ID_FORM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DAOContacts)) return false;

        DAOContacts that = (DAOContacts) o;

        if (ID_FORM != that.ID_FORM) return false;
        if (ID_CONTACT != null ? !ID_CONTACT.equals(that.ID_CONTACT) : that.ID_CONTACT != null) return false;
        if (ID_CONTACT_CATEGORY != null ? !ID_CONTACT_CATEGORY.equals(that.ID_CONTACT_CATEGORY) : that.ID_CONTACT_CATEGORY != null)
            return false;
        if (INFO != null ? !INFO.equals(that.INFO) : that.INFO != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID_CONTACT != null ? ID_CONTACT.hashCode() : 0;
        result = 31 * result + (INFO != null ? INFO.hashCode() : 0);
        result = 31 * result + (ID_CONTACT_CATEGORY != null ? ID_CONTACT_CATEGORY.hashCode() : 0);
        result = 31 * result + (int) (ID_FORM ^ (ID_FORM >>> 32));
        return result;
    }
}
