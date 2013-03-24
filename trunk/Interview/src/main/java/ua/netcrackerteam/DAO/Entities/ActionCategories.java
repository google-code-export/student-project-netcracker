package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author krygin
 */
@Entity
@Table(name="ACTION_CATEGORIES")
public class ActionCategories implements Serializable {
    private static final long serialVersionUID = -3235406111167555555L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "action_seq_gen")
    @SequenceGenerator(name = "action_seq_gen", sequenceName = "action_seq")
    @Column(name= "ID_ACTION")
    private int idAction;

    @Column(name= "ACTION_NAME")
    private String actionName;

    public ActionCategories() {
    }

    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionCategories that = (ActionCategories) o;

        if (idAction != that.idAction) return false;
        if (actionName != null ? !actionName.equals(that.actionName) : that.actionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAction;
        result = 31 * result + (actionName != null ? actionName.hashCode() : 0);
        return result;
    }
}
