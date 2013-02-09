package ua.netcrackerteam.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Filipenko
 * Date: 01.02.13
 * Time: 0:35
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author Filipenko
 */
@Entity
@Table(name="BRANCH")
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "branch_seq_gen")
    @SequenceGenerator(name = "branch_seq_gen", sequenceName = "branch_seq")
    @Column(name="ID_BRANCH")
    private int idBranch;

    @Column(name= "NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;

        Branch branch = (Branch) o;

        if (idBranch != branch.idBranch) return false;
        if (!description.equals(branch.description)) return false;
        if (!name.equals(branch.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBranch;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}



