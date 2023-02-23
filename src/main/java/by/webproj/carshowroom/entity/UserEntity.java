package by.webproj.carshowroom.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class UserEntity {
    private final long id;
    private final String login;
    private final String password;


    private UserEntity(Builder builder) {
        id = builder.userId;
        login = builder.userLogin;
        password = builder.userPassword;

    }
    public static class Builder {
        private long userId;
        private String userLogin;
        private String userPassword;

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

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
