package com.tenjak.controller.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.tenjak.controller.userController;
import com.tenjak.pojo.User;
import com.tenjak.service.userService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
@ResponseBody
public class userControllerImpl implements userController {
    @Autowired
    private userService userService;

    /*新增用户*/
    @RequestMapping("/add")
    public void add(User user) {
        userService.add(user);
    }

    /*删除用户信息*/
    @RequestMapping("/delete")
    public void delete(String userId) {
        userService.delete(userId);
    }

    /*更新用户信息*/
    @RequestMapping("/update")
    public void update(User user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*查询单个用户*/
    @RequestMapping("/findOne")
    public User findOne(String uid) {
        User one = userService.findOne(uid);
        return one;
    }

    /*导出功能*/
    @RequestMapping("/exportexcel")
    public void exportexcel(HttpServletResponse response) {
        List<User> all = userService.findAll();
        response.setHeader("content-type", "application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=User.xls");
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, all);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*对象赋值*/
    @RequestMapping("/Object")
    public void Object(){
        User u1=new User();
        u1.setRealname("A");
        User u2=new User();
        BeanUtils.copyProperties(u1,u2);
        System.out.println(u2.getRealname());
    }

}
