package ua.netcrackerteam.DAO.Entities;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 22.08.13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */

public class EnrollmentScores implements Serializable {
    private static final long serialVersionUID = -3235404536546181452L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "enrollment_scores_seq_gen")
    @SequenceGenerator(name = "enrollment_scores_seq_gen", sequenceName = "enrollment_scores_seq")
    @Column(name= "SCORE_ID")
    private int idScore;

    @Column(name= "NAME")
    private String NAME;

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
