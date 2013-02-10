package ua.netcrackerteam.DAO;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="INSTITUTE")
public class Institute implements Serializable {
    private static final long serialVersionUID = -3254406777751181181L;
    
    public Institute() {        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "institute_seq_gen")
    @SequenceGenerator(name = "institute_seq_gen", sequenceName = "institute_seq")
    @Column(name="ID_INSTITUTE")
    private Integer instituteId;
    
    @Column(name="NAME")
    private String name;
    
   public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
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
        if (!(o instanceof Institute)) return false;

        Institute institute = (Institute) o;

        if (instituteId != null ? !instituteId.equals(institute.instituteId) : institute.instituteId != null)
            return false;
        if (name != null ? !name.equals(institute.name) : institute.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instituteId != null ? instituteId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
