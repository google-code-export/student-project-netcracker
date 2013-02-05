package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:29
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "USER_LIST")
public class UserList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_list_seq_gen")
    @SequenceGenerator(name = "user_list_seq_gen", sequenceName = "user_list_seq")
    @Column(name= "ID_USER")
    private int idUser;

    @Column(name= "USER_NAME")
    private String userName;

    @Column(name= "PASSWORD")
    private String password;

    @Column(name= "EMAIL")
    private String email;

    @Column(name= "ACTIVE")
    private String active;

    @Column(name= "ID_USER_CATEGORY")
    private int idUserCategory;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getIdUserCategory() {
        return idUserCategory;
    }

    public void setIdUserCategory(int idUserCategory) {
        this.idUserCategory = idUserCategory;
    }
}
