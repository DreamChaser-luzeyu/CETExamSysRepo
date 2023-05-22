package cn.binarydumplings.cetexam.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HelloController")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
