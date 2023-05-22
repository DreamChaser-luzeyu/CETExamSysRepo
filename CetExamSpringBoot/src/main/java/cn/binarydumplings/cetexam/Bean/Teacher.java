package cn.binarydumplings.cetexam.Bean;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("teacher")
public class Teacher {

    public static final Integer PRIVILEGED = 1;
    public static final Integer NOT_PRIVILEGED = 0;

    private String userName;
    private String realName;
    private String password;
    private Integer hasPrivilege;

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getHasPrivilege() {
        return hasPrivilege;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setHasPrivilege(Integer hasPrivilege) {
        this.hasPrivilege = hasPrivilege;
    }

    public Teacher(String userName, String realName, String password, Integer hasPrivilege) {
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.hasPrivilege = hasPrivilege;
    }
}
