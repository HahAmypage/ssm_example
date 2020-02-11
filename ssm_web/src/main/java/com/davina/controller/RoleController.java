package com.davina.controller;

import com.davina.domain.Role;
import com.davina.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询角色列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",roleService.findAllRoles(pageNum,pageSize));
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findByPage";
    }
}
