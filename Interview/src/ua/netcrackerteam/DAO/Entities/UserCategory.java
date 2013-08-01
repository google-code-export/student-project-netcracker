package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "USER_CATEGORY")
public class UserCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_category_seq_gen")
    @SequenceGenerator(name = "user_category_seq_gen", sequenceName = "user_category_seq")
    @Column(name= "ID_USER_CATEGORY")
    private Integer idUserCategory;

    @Column(name= "CATEGORY")
    private String name;

    public Integer getIdUserCategory() {
        return idUserCategory;
    }

    public void setIdUserCategory(Integer idUserCategory) {
        this.idUserCategory = idUserCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public static UserCategory getUserCategoeyByID(int currUserCategoryID) throws SQLException {
        Session session = null;
        org.hibernate.Query re = null;
        List listOfCategories = null;
        UserCategory currUserCategory = new UserCategory();
        try {
            Locale.setDefault(Locale.ENGLISH);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            re = session.createQuery("from UserCategory where idUSerCategory = '" + currUserCategoryID + "'");
                    listOfCategories = re.list();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        currUserCategory = (UserCategory) listOfCategories.get(0);
        return currUserCategory;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCategory)) return false;

        UserCategory that = (UserCategory) o;

        if (idUserCategory != that.idUserCategory) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserCategory;
        result = 31 * result + name.hashCode();
        return result;
    }
}
