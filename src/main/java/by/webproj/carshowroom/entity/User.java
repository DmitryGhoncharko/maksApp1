package by.webproj.carshowroom.entity;

import java.util.Objects;

public class User {
    private final long id;
    private final String login;
    private final String password;

    private final Role userRole;

    private User(Builder builder) {
        id = builder.userId;
        login = builder.userLogin;
        password = builder.userPassword;

        userRole = builder.userRole;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }



    public Role getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    public static class Builder {
        private long userId;
        private String userLogin;
        private String userPassword;
        private Role userRole;

        public Builder withUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUserLogin(String userLogin) {
            this.userLogin = userLogin;
            return this;
        }

        public Builder withUserPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public Builder withUserRole(Role userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
