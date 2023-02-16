package lk.ijse.School.entity;

public class NewUser {
    String userNIC;
    String userName;
    String userType;
    String password;


    public String getUserNIC() {
        return userNIC;
    }

    public void setUserNIC(String userNIC) {
        this.userNIC = userNIC;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public NewUser(String userNIC, String userName, String userType, String password) {
        this.userNIC = userNIC;
        this.userName = userName;
        this.userType = userType;
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "userNIC='" + userNIC + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +

                ", userPassword='" +password + '\'' +
                '}';
    }
}














