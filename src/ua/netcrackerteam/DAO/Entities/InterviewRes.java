package ua.netcrackerteam.DAO.Entities;

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
    private Form form;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_USER")
    private UserList user;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_ENROLLMENT_SCORE")
    private EnrollmentScores enrollmentScore;

    @Column(name= "SCORE")
    private String score;

    @Column(name= "WORK_IN_TEAM")
    private int workInTeam;

    @Column(name= "SQL_KNOWLEDGE")
    private String sqlKnowledge;

    @Column(name= "JAVA_KNOWLEDGE")
    private String javaKnowledge;

    public EnrollmentScores getEnrollmentScore() {
        return enrollmentScore;
    }

    public void setEnrollmentScore(EnrollmentScores enrollmentScore) {
        this.enrollmentScore = enrollmentScore;
    }

    public int getWorkInTeam() {
        return workInTeam;
    }

    public void setWorkInTeam(int workInTeam) {
        this.workInTeam = workInTeam;
    }

    public String getSqlKnowledge() {
        return sqlKnowledge;
    }

    public void setSqlKnowledge(String sqlKnowledge) {
        this.sqlKnowledge = sqlKnowledge;
    }

    public String getJavaKnowledge() {
        return javaKnowledge;
    }

    public void setJavaKnowledge(String javaKnowledge) {
        this.javaKnowledge = javaKnowledge;
    }

    public Integer getIdInterviewRes() {
        return idInterviewRes;
    }

    public void setIdInterviewRes(Integer idInterviewRes) {
        this.idInterviewRes = idInterviewRes;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public UserList getUser() {
        return user;
    }

    public void setIdUser(UserList user) {
        this.user = user;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterviewRes)) return false;

        InterviewRes that = (InterviewRes) o;

        if (idInterviewRes != that.idInterviewRes) return false;
        if (!form.equals(that.form)) return false;
        if (!user.equals(that.user)) return false;
        if (!score.equals(that.score)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInterviewRes;
        result = 31 * result + form.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + score.hashCode();
        return result;
    }
}
