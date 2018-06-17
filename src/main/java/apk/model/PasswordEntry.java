package apk.model;

import java.util.Objects;

public class PasswordEntry {

    private Integer id;
    private String service;
    private String login;
    private String password;

    public PasswordEntry() {
    }

    public PasswordEntry(Integer id, String service, String login, String password) {
        this.id = id;
        this.service = service;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordEntry entry = (PasswordEntry) o;
        return Objects.equals(id, entry.id) &&
                Objects.equals(service, entry.service) &&
                Objects.equals(login, entry.login) &&
                Objects.equals(password, entry.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, service, login, password);
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "id=" + id +
                ", service='" + service + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}