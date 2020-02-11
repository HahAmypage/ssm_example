package com.davina.controller;

import com.davina.domain.PageBean;
import com.davina.domain.Product;
import com.davina.service.IOrderService;
import com.davina.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
//    private IOrderService orderService;

    /**
     * 查询全部
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> produceList = productService.findAll();
        modelAndView.addObject("produceList",produceList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    /**
     * 添加
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:/product/findByPage";
    }

    /**
     * 根据产品id查找一个产品
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",productService.findById(id));
        modelAndView.setViewName("product-update");
        return modelAndView;
    }

    /**
     * 更新
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/product/findByPage";
    }

    /**
     * 删除
     * @param pids
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String pids){
//        String[] pidsArray = pids.split(",");
//        orderService.deleteByProductId(pidsArray);
        productService.delete(pids);
        return "redirect:/product/findByPage";
    }

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean<Product> productServiceByPage = productService.findByPage(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productServiceByPage",productServiceByPage);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
