/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.netcrackerteam.DAO;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="FACULTY")
public class Faculty implements Serializable {
    private static final long serialVersionUID = -3254400077751181181L;
    
    public Faculty() {        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "faculty_seq_gen")
    @SequenceGenerator(name = "faculty_seq_gen", sequenceName = "faculty_seq")
    @Column(name = "ID_FACULTY")
    private int idFaculty;
    
    @Column(name = "NAME")
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_INSTITUTE")
    private Institute institute;

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        if (idFaculty != faculty.idFaculty) return false;
        if (!institute.equals(faculty.institute)) return false;
        if (!name.equals(faculty.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFaculty;
        result = 31 * result + name.hashCode();
        result = 31 * result + institute.hashCode();
        return result;
    }
}
