package ua.netcrackerteam.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:08
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Filipenko
 */
@Entity
@Table(name = "KNOWLEDGE")
public class Knowledge {
    @Id
    @Column(name= "ID_KNOWLEDGE")
    private Long idKnowledge;

    @Column(name= "SCORE")
    private Double score;

    @Column(name= "ID_FORM")
    private Long idForm;

    @Column(name= "ID_BRANCH")
    private Long idBranch;

    public Long getIdKnowledge() {
        return idKnowledge;
    }

    public void setIdKnowledge(Long idKnowledge) {
        this.idKnowledge = idKnowledge;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getIdForm() {
        return idForm;
    }

    public void setIdForm(Long idForm) {
        this.idForm = idForm;
    }

    public Long getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Long idBranch) {
        this.idBranch = idBranch;
    }
}
