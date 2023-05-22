package cn.binarydumplings.cetexam.Bean;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("student")
public class Student {
    public static final Integer APPLIED = 1;
    public static final Integer NOT_APPLIED = 0;

    private String userName;
    private String realName;
    private String password;
    private Integer hasApplied;

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getHasApplied() {
        return hasApplied;
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

    public void setHasApplied(Integer hasApplied) {
        this.hasApplied = hasApplied;
    }

    public Student(String userName, String realName, String password, Integer hasApplied) {
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.hasApplied = hasApplied;
    }
}
