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
    private int idInterviewRes;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_FORM")
    private Form idForm;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_USER")
    private UserList idUser;

    @Column(name= "SCORE")
    private Double scope;

    public Integer getIdInterviewRes() {
        return idInterviewRes;
    }

    public void setIdInterviewRes(Integer idInterviewRes) {
        this.idInterviewRes = idInterviewRes;
    }

    public Form getIdForm() {
        return idForm;
    }

    public void setIdForm(Form idForm) {
        this.idForm = idForm;
    }

    public UserList getIdUser() {
        return idUser;
    }

    public void setIdUser(UserList idUser) {
        this.idUser = idUser;
    }

    public Double getScope() {
        return scope;
    }

    public void setScope(Double scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterviewRes)) return false;

        InterviewRes that = (InterviewRes) o;

        if (idInterviewRes != that.idInterviewRes) return false;
        if (!idForm.equals(that.idForm)) return false;
        if (!idUser.equals(that.idUser)) return false;
        if (!scope.equals(that.scope)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInterviewRes;
        result = 31 * result + idForm.hashCode();
        result = 31 * result + idUser.hashCode();
        result = 31 * result + scope.hashCode();
        return result;
    }
}
