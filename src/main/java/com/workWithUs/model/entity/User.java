package com.workWithUs.model.entity;

public class User {

    private int id;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Role role;
    private Boolean isBlocked;
    private String avatar;

    public static class Builder {

        private final User person;

        public Builder() {
            person = new User();
        }

        public Builder withEmail(String email) {
            person.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            person.password = password;
            return this;
        }

        public Builder withId(int id) {
            person.id = id;
            return this;
        }

        public Builder withFullName(String fullName) {
            person.fullName = fullName;
            return this;
        }


        public Builder withPhoneNumber(String phoneNumber) {
            person.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withStatus(boolean isBlocked) {
            person.isBlocked = isBlocked;
            return this;
        }

        public Builder withRole(String roleName) {
            Role role = null;
            for (Role value : Role.values()) {
                if (value.equalsTo(roleName)) role = value;
            }
            person.role = role;
            return this;
        }

        public Builder withAvatar(String avatar){
            person.avatar = avatar;
            return this;
        }

        public User build() {
            return person;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
