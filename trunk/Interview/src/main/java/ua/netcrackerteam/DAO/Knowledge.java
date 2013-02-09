package ua.netcrackerteam.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:08
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;

/**
 * @author Filipenko
 */
@Entity
@Table(name = "KNOWLEDGE")
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "knowledge_seq_gen")
    @SequenceGenerator(name = "knowledge_seq_gen", sequenceName = "knowledge_seq")
    @Column(name= "ID_KNOWLEDGE")
    private int idKnowledge;

    @Column(name= "SCORE")
    private Double score;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_FORM")
    private Form idForm;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_BRANCH")
    private Branch idBranch;

    public int getIdKnowledge() {
        return idKnowledge;
    }

    public void setIdKnowledge(int idKnowledge) {
        this.idKnowledge = idKnowledge;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Form getIdForm() {
        return idForm;
    }

    public void setIdForm(Form idForm) {
        this.idForm = idForm;
    }

    public Branch getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Branch idBranch) {
        this.idBranch = idBranch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knowledge)) return false;

        Knowledge knowledge = (Knowledge) o;

        if (idKnowledge != knowledge.idKnowledge) return false;
        if (!idBranch.equals(knowledge.idBranch)) return false;
        if (!idForm.equals(knowledge.idForm)) return false;
        if (!score.equals(knowledge.score)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKnowledge;
        result = 31 * result + score.hashCode();
        result = 31 * result + idForm.hashCode();
        result = 31 * result + idBranch.hashCode();
        return result;
    }
}
