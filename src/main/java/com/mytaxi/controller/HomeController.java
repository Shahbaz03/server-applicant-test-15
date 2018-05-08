package com.mytaxi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.mytaxi.constants.SwaggerConstants.HOME_CONTROLLER;

import io.swagger.annotations.Api;

@Controller
@Api(tags = {HOME_CONTROLLER})
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
