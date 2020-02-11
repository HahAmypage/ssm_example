package com.davina.controller;

import com.davina.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",orderService.findAll());
        modelAndView.setViewName("order-list");
        return modelAndView;
    }

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",orderService.findByPage(pageNum,pageSize));
        modelAndView.setViewName("order-list");
        return modelAndView;
    }
}
