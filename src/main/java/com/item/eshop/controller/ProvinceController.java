package com.item.eshop.controller;

import com.item.eshop.mapper.ProvinceMapper;
import com.item.eshop.model.Province;
import com.item.eshop.model.Province;
import com.item.eshop.service.ProvinceService;
import com.item.eshop.util.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    ProvinceMapper provinceMapper;


    @PostMapping("/add")
    public int add(@RequestParam(value = "name")String name) {
        Province province = new Province();
        int sort_no = provinceMapper.selectCountNum();
        province.setId(sort_no+1);
        province.setSort_no(sort_no+1);
        province.setName(name);
        return provinceService.insert(province);
    }

    @PostMapping("/delete")
    public int delete(@RequestParam(value = "id")Integer id) {
        return provinceService.deleteByPrimaryKey(id);
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    @PostMapping("/updateName")
    public int updateName(@RequestParam(value = "name")String name,@RequestParam(value = "id")Integer id) {
        Province province = new Province();
        province.setId(id);
        province.setName(name);
        return provinceService.updateByPrimaryKeySelective(province);
    }

    @PostMapping("/updateSort")
    public int updateSort(@RequestParam(value = "no")int no,@RequestParam(value = "move")int move,@RequestParam(value = "id")Integer id) {
        int result = provinceMapper.updateBySort(no,no+move);
        if(result==1){
            return provinceMapper.updateById(no+move,id);
        }
        return 0;
    }

    @PostMapping("/findById")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(provinceService.selectByPrimaryKey(id));
    }

    @PostMapping("/findMore")
    public String findMore() {
        return JsonObject.toJson(provinceService.selectMore());
    }

    @PostMapping("/findBySort")
    public String findByValid() {
        return JsonObject.toJson(provinceService.selectMoreBySort());
    }
}
