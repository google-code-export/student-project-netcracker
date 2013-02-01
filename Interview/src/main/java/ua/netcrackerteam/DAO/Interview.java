package ua.netcrackerteam.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name= "ID_INTERVIEW")
    private Long idInterview;

    @Column(name= "START_DATE")
    private Date startDate;

    @Column(name= "END_DATE")
    private Date endDate;

    @Column(name= "MAX_NUMBER")
    private Integer maxNumber;

    public Long getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(Long idInterview) {
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
}
