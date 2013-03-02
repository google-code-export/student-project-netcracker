package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 0:53
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "INTERVIEW")
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "interview_seq_gen")
    @SequenceGenerator(name = "interview_seq_gen", sequenceName = "interview_seq")
    @Column(name= "ID_INTERVIEW")
    private int idInterview;

    @Column(name= "START_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;

    @Column(name= "END_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    @Column(name= "MAX_NUMBER")
    private Integer maxNumber;

    public int getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(int idInterview) {
        this.idInterview = idInterview;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (idInterview != interview.idInterview) return false;
        if (!endDate.equals(interview.endDate)) return false;
        if (!maxNumber.equals(interview.maxNumber)) return false;
        if (!startDate.equals(interview.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInterview;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + maxNumber.hashCode();
        return result;
    }
}
