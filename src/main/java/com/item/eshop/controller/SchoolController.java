package com.item.eshop.controller;

import com.item.eshop.mapper.SchoolMapper;
import com.item.eshop.model.School;
import com.item.eshop.service.SchoolService;
import com.item.eshop.util.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolMapper schoolMapper;


    @PostMapping("/add")
    public int add(@RequestParam(value = "name")String name,@RequestParam(value = "pid")int pid) {
        School school = new School();
        int sort_no = schoolMapper.selectCountNum();
        school.setId(sort_no+1);
        school.setSort_no(sort_no+1);
        school.setProvinceId(pid);
        school.setName(name);
        return schoolService.insert(school);
    }

    @PostMapping("/delete")
    public int delete(@RequestParam(value = "id")Integer id) {
        return schoolService.deleteByPrimaryKey(id);
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    @PostMapping("/updateName")
    public int updateName(@RequestParam(value = "name")String name,@RequestParam(value = "id")Integer id) {
        School school = new School();
        school.setId(id);
        school.setName(name);
        return schoolService.updateByPrimaryKeySelective(school);
    }

    @PostMapping("/updateSort")
    public int updateSort(@RequestParam(value = "no")int no,@RequestParam(value = "move")int move,@RequestParam(value = "id")Integer id) {
        int result = schoolMapper.updateBySort(no,no+move);
        if(result==1){
            return schoolMapper.updateById(no+move,id);
        }
        return 0;
    }

    @GetMapping("/findById")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(schoolService.selectByPrimaryKey(id));
    }

    @GetMapping("/findMore")
    public String findMore() {
        return JsonObject.toJson(schoolService.selectMore());
    }

    @GetMapping("/findBySort")
    public String findByValid() {
        return JsonObject.toJson(schoolService.selectMoreBySort());
    }
}
