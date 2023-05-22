package cn.binarydumplings.cetexam.Service;

import cn.binarydumplings.cetexam.Bean.Teacher;
import cn.binarydumplings.cetexam.Controller.LoginController;
import jakarta.servlet.http.HttpServletRequest;

public class UserSessionUtils {
    public static String getUserName(HttpServletRequest request) {
        return (String) request.getSession(true).getAttribute("userName");
    }

    public static Boolean isStudentLoggedIn(HttpServletRequest request) {
        String userName = (String) request.getSession(true).getAttribute("userName");
        if(userName == null) {return false;}
        String userType = (String) request.getSession(true).getAttribute("userType");
        return LoginController.USER_TYPE_STUDENT.equals(userType);
    }

    public static Boolean isTeacherLoggedIn(HttpServletRequest request) {
        String userName = (String) request.getSession(true).getAttribute("userName");
        if(userName == null) {return false;}
        String userType = (String) request.getSession(true).getAttribute("userType");
        return LoginController.USER_TYPE_TEACHER.equals(userType);
    }

    public static Boolean hasPrivilege(HttpServletRequest request) {
        Integer privilege = (Integer) request.getSession(true).getAttribute("isPrivileged");
        if(privilege == null) { return false; }
        return Teacher.PRIVILEGED.equals(privilege);
    }
}
