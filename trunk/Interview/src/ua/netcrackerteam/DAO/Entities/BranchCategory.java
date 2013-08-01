package ua.netcrackerteam.DAO.Entities;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Filipenko
 * Date: 03.03.13
 * Time: 8:27
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name="BRANCH_CATEGORY")
public class BranchCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "branch_cat_seq_gen")
    @SequenceGenerator(name = "branch_cat_seq_gen", sequenceName = "branch_cat_seq")
    @Column(name="ID_BRANCH_CAT")
    private int idBranchCat;

    @Column(name= "description")
    private String name;

    public int getIdBranchCat() {
        return idBranchCat;
    }

    public void setIdBranchCat(int idBranchCat) {
        this.idBranchCat = idBranchCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BranchCategory that = (BranchCategory) o;

        if (idBranchCat != that.idBranchCat) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBranchCat;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
