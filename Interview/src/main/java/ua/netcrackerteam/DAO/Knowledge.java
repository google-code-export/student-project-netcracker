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

    @ManyToOne//(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_FORM")
    private Form form;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_BRANCH")
    private Branch branch;
    
    
    
    

    public Knowledge() {        
    }
    
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
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    
    
    
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knowledge)) return false;

        Knowledge knowledge = (Knowledge) o;

        if (idKnowledge != knowledge.idKnowledge) return false;
        if (!branch.equals(knowledge.branch)) return false;
        if (!form.equals(knowledge.form)) return false;
        if (!score.equals(knowledge.score)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKnowledge;
        result = 31 * result + score.hashCode();
        result = 31 * result + branch.hashCode();
        result = 31 * result + form.hashCode();
        return result;
    }    
}
