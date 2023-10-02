package Model;

/** This class supplies the user object. */
public class User {
    private String userName;
    private String passWord;


/** Constructor for an instance of the appointment schedule object.
 *
 * @param userName the username
 * @param passWord the user password
 *
 */
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User() {
    }

    /** The username getter.
     *
     * @return username
     *
     */
    public String getUserName() {
        return userName;
    }

    /** The username setter. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** The password getter.
     *
     * @return password
     * */
    public String getPassWord() {
        return passWord;
    }

    /** The password setter. */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
