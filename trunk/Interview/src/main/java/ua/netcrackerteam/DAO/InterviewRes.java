package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "INTERVIEW_RES")
public class InterviewRes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "interview_res_seq_gen")
    @SequenceGenerator(name = "interview_res_seq_gen", sequenceName = "interview_res_seq")
    @Column(name= "ID_INTERVIEW_RES")
    private Long idInterviewRes;

    @Column(name= "ID_FORM")
    private Long idForm;

    @Column(name= "ID_USER")
    private Long idUser;

    @Column(name= "SCORE")
    private Double scope;

    public Long getIdInterviewRes() {
        return idInterviewRes;
    }

    public void setIdInterviewRes(Long idInterviewRes) {
        this.idInterviewRes = idInterviewRes;
    }

    public Long getIdForm() {
        return idForm;
    }

    public void setIdForm(Long idForm) {
        this.idForm = idForm;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Double getScope() {
        return scope;
    }

    public void setScope(Double scope) {
        this.scope = scope;
    }
}
