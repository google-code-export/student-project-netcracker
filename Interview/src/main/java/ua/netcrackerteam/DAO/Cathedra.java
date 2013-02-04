package ua.netcrackerteam.DAO;

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
    private Long idCathedra;
    
    @Column(name="NAME")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="ID_FACULTY")
    private Faculty faculty;

    public Long getIdCathedra() {
        return idCathedra;
    }

    public void setIdCathedra(Long idCathedra) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Cathedra cathedra = (Cathedra) o;

        if (faculty != null ? !faculty.equals(cathedra.faculty) : cathedra.faculty != null) return false;
        if (idCathedra != null ? !idCathedra.equals(cathedra.idCathedra) : cathedra.idCathedra != null) return false;
        if (name != null ? !name.equals(cathedra.name) : cathedra.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCathedra != null ? idCathedra.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        return result;
    }
}
