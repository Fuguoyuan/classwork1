package Entity;

/**
 * 定义User类 用于保存下项目信息：
 * 属性有id、username、password分别代表用户编号、用户账号、用户密码
 * 并且定义了每个属性的get和set方法、重写了toString方法。
 */
public class User {
    int id;
    String username;
    String password;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
