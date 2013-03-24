package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:16
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "STATUS")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq_gen")
    @SequenceGenerator(name = "status_seq_gen", sequenceName = "status_seq")
    @Column(name= "ID_STATUS")
    private int idStatus;

    @Column(name= "NAME")
    private String name;

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
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
        if (!(o instanceof Status)) return false;

        Status status = (Status) o;

        if (idStatus != status.idStatus) return false;
        if (!name.equals(status.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatus;
        result = 31 * result + name.hashCode();
        return result;
    }
}
