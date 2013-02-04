/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.netcrackerteam.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.netcrackerteam.configuration.HibernateUtil;

import java.io.Serializable;
import java.util.Locale;
import javax.persistence.*;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="SCHOOL")
public class School implements Serializable {
    private static final long serialVersionUID = -3254555057111181181L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_seq_gen")
    @SequenceGenerator(name = "school_seq_gen", sequenceName = "school_seq")
    @Column(name="ID_SCHOOL")
    private Long idSchool;
    
    @Column(name="NAME")
    private String name;

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
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

        School school = (School) o;

        if (idSchool != null ? !idSchool.equals(school.idSchool) : school.idSchool != null) return false;
        if (name != null ? !name.equals(school.name) : school.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSchool != null ? idSchool.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
