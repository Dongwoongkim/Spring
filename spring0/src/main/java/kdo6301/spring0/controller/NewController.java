package kdo6301.spring0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewController {

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello " + name;
    }
}
