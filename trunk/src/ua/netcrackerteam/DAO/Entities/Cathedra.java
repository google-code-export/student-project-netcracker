package ua.netcrackerteam.DAO.Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="CATHEDRA")
public class Cathedra implements Serializable {
    private static final long serialVersionUID = -3254658733351181181L;
    
    public Cathedra() {        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cathedra_seq_gen")
    @SequenceGenerator(name = "cathedra_seq_gen", sequenceName = "cathedra_seq")
    @Column(name="ID_CATHEDRA")
    private int idCathedra;
    
    @Column(name="NAME")
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_FACULTY")
    private Faculty faculty;
    
    
    
    

    public int getIdCathedra() {
        return idCathedra;
    }

    public void setIdCathedra(int idCathedra) {
        this.idCathedra = idCathedra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cathedra)) return false;

        Cathedra cathedra = (Cathedra) o;

        if (idCathedra != cathedra.idCathedra) return false;
        if (!faculty.equals(cathedra.faculty)) return false;
        if (!name.equals(cathedra.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCathedra;
        result = 31 * result + name.hashCode();
        result = 31 * result + faculty.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
