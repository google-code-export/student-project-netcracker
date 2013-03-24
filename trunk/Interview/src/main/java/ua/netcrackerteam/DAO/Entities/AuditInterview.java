package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author krygin
 */
@Entity
@Table(name="AUDIT_INTERVIEW")
public class AuditInterview implements Serializable {
    private static final long serialVersionUID = -3235406055681555555L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "audit_seq_gen")
    @SequenceGenerator(name = "audit_seq_gen", sequenceName = "audit_seq")
    @Column(name= "ID_AUDIT")
    private int idAudit;

    @Column(name= "USER_NAME")
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_USER_CATEGORY")
    private UserCategory idUserCategory;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name= "ID_ACTION")
    private ActionCategories actionCategories;

    @Column(name= "ACTION_DESC")
    private String actionDescription;

    @Column(name= "ACTION_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date actionDate;

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserCategory getUserCategory() {
        return idUserCategory;
    }

    public void setUserCategory(UserCategory idUserCategory) {
        this.idUserCategory = idUserCategory;
    }

    public ActionCategories getAction() {
        return actionCategories;
    }

    public void setAction(ActionCategories action) {
        this.actionCategories = action;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditInterview that = (AuditInterview) o;

        if (idAudit != that.idAudit) return false;
        if (actionCategories != null ? !actionCategories.equals(that.actionCategories) : that.actionCategories != null)
            return false;
        if (actionDate != null ? !actionDate.equals(that.actionDate) : that.actionDate != null) return false;
        if (actionDescription != null ? !actionDescription.equals(that.actionDescription) : that.actionDescription != null)
            return false;
        if (idUserCategory != null ? !idUserCategory.equals(that.idUserCategory) : that.idUserCategory != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAudit;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (idUserCategory != null ? idUserCategory.hashCode() : 0);
        result = 31 * result + (actionCategories != null ? actionCategories.hashCode() : 0);
        result = 31 * result + (actionDescription != null ? actionDescription.hashCode() : 0);
        result = 31 * result + (actionDate != null ? actionDate.hashCode() : 0);
        return result;
    }
}
