
package entity;


public class User {
    private long id;
    private String login;
    private String password;
    private Reader reader;

    public User() {
    }

    public User(String login, String password, Reader reader) {
        this.login = login;
        this.password = password;
        this.reader = reader;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" 
                + "login=" + login 
                + ", password=" + password 
                + ", reader=" + reader + reader.getName() 
                + " " + reader.getLastname()
                + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
      
}
