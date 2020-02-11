package com.davina.controller;

import com.davina.domain.Role;
import com.davina.domain.SysUser;
import com.davina.service.IRoleService;
import com.davina.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findByPage")
    public ModelAndView findAllUsers(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        System.out.println("user name:"+user.getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",userService.findAllUsers(pageNum,pageSize));
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @RequestMapping("/save")
    public String save(SysUser sysUser){
        userService.save(sysUser);
        return "redirect:/user/findByPage";
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userService.findById(id));
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 根据用户id，进入角色列表
     * @param id
     * @return
     */
    @RequestMapping("/toUserRole")
    public ModelAndView toUserRole(Integer id){
        ModelAndView modelAndView = new ModelAndView();

        // 查询用户
        SysUser sysUser = userService.findById(id);
        // 查询用户目前有的角色
        List<Role> sysUserRoles = sysUser.getRoles();
        String roleStr = "";
        if (sysUserRoles != null && sysUserRoles.size() > 0){
            for (Role role : sysUserRoles){
                roleStr+=role.getRoleName()+",";
            }
        }
        // 查询所有角色
        List<Role> allRoles = roleService.findAll();
        // 封装数据到modelAndView
        modelAndView.addObject("roleStr",roleStr);
        modelAndView.addObject("allRoles",allRoles);
        modelAndView.addObject("user",sysUser);
        modelAndView.setViewName("user-role-add");

        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer userId,@RequestParam(value = "ids",required = false) Integer[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findByPage";
    }
}
