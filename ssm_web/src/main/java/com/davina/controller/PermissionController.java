package com.davina.controller;

import com.davina.domain.Permission;
import com.davina.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/permission")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",permissionService.findByPage(pageNum,pageSize));
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findByPage";
    }
}
