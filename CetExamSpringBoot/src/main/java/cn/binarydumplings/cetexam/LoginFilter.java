package cn.binarydumplings.cetexam;

import cn.binarydumplings.cetexam.Service.UserSessionUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter/*(urlPatterns = "/HelloController/*")*/
public class LoginFilter implements Filter {

    // 未登录用户允许访问的页面
    private static final String[] APPROVE_PATH_ARR_ANONYMOUS = new String[]{
            "/index.html",
            "/login.html",
            "/static/*",
            "/HelloController/(.*)",
            "/LoginController/(.*)",
            "/StuManagement/registerStudent(.*)",
    };

    // 学生用户允许访问的页面
    private static final String[] APPROVE_PATH_ARR_STUDENT = new String[]{
            "/index.html",
            "/login.html",
            "/static/*",
            "/HelloController/(.*)",
            "/LoginController/(.*)",
            "/StuManagement/(.*)",
            "/ExamController/answerChoiceQuestion(.*)",
            "/ExamController/answerSubjectiveQuestion(.*)",
    };

    // 教师用户允许访问的页面
    private static final String[] APPROVE_PATH_ARR_TEACHER = new String[]{
            "/index.html",
            "/login.html",
            "/static/*",
            "/HelloController/(.*)",
            "/LoginController/(.*)",
            "/StuManagement/(.*)",
            "/ExamController/(.*)",
    };

    // 特权教师用户允许访问的页面（全部功能）
    private static final String[] APPROVE_PATH_ARR_TEACHER_PRIVILEGE = new String[]{
//            "/index.html",
//            "/login.html",
//            "/static/*",
//            "/HelloController/(.*)",
//            "/LoginController/(.*)",
//            "/StuManagement/(.*)",
            "(.*)"
    };


    private static final String ROOT_REQUEST_PATH_REGULAR_EXP = "(.*)";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // TODO: 部署后删去
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 获得用户访问的URL
        String url = request.getRequestURL().toString();
        System.out.println("[ DEV ] 1 - Request url: " + url);
        // 尝试获得session
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            System.out.println("[ DEV ] 1 - CurrentSessionID: " + httpSession.getId());
            System.out.println("[ DEV ] 1 - httpSession-userName: " + httpSession.getAttribute("userName"));
        }
        // 判断是否授权访问
        boolean isPathValid = false;
        if ((!UserSessionUtils.isTeacherLoggedIn(request)) && (!UserSessionUtils.isStudentLoggedIn(request))) {
            // 如果用户未登录，检查路径是否允许访问
            isPathValid = isPathValid(url, APPROVE_PATH_ARR_ANONYMOUS);
        }
        else if (UserSessionUtils.isStudentLoggedIn(request)) {
            // 如果用户为学生，检查路径是否允许访问
            isPathValid = isPathValid(url, APPROVE_PATH_ARR_STUDENT);
        }
        else if (UserSessionUtils.isTeacherLoggedIn(request) && (!UserSessionUtils.hasPrivilege(request))) {
            // 如果用户为非特权老师，检查路径是否允许访问
            isPathValid = isPathValid(url, APPROVE_PATH_ARR_TEACHER);
        }
        else if (UserSessionUtils.isTeacherLoggedIn(request) && UserSessionUtils.hasPrivilege(request)) {
            // 如果用户为特权老师，检查路径是否允许访问
            isPathValid = isPathValid(url, APPROVE_PATH_ARR_TEACHER_PRIVILEGE);
        }
        else {
            // 未知的用户类型
            // TODO: 抛出异常
        }

        // 授权访问
        if (isPathValid) {
            System.out.println("[ DEV ] 3 - Access approved");
            filterChain.doFilter(servletRequest, servletResponse);
        }
        // 未授权访问，返回状态码403
        else {
            response.setStatus(403);
            System.out.println("[ DEV ] 3 - Access denied");
        }
    }

    private boolean isPathValid(String url, String[] approvedPaths) {
        boolean isPathValid = false;
        for (String path : approvedPaths) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ROOT_REQUEST_PATH_REGULAR_EXP);
            stringBuilder.append(path);
            System.out.println("[ DEV ] 2 - Generated regular expression: " + stringBuilder.toString());
            if (url.matches(stringBuilder.toString())) {
                isPathValid = true;
                break;
            }
        }
        return isPathValid;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
