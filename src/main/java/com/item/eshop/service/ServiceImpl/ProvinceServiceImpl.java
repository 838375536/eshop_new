package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.ProvinceMapper;
import com.item.eshop.model.Province;
import com.item.eshop.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return provinceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Province province) {
        Province gc = province;
        gc.setId(null);
        return provinceMapper.insert(gc);
    }

    @Override
    public int insertSelective(Province province) {
        Province gc = province;
        gc.setId(null);
        return provinceMapper.insertSelective(gc);
    }

    @Override
    public Province selectByPrimaryKey(Integer id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Province province) {
        Province gc = province;
        gc.setSort_no(null);
        return provinceMapper.updateByPrimaryKeySelective(province);
    }

    @Override
    public int updateByPrimaryKey(Province province) {
        return provinceMapper.updateByPrimaryKey(province);
    }

    @Override
    public List<Province> selectAllProvince() {
        return provinceMapper.selectMore();
    }

    @Override
    public List<Province> selectMore() {
        return provinceMapper.selectMore();
    }

    @Override
    public List<Province> selectMoreBySort() {
        return provinceMapper.selectMoreBySort();
    }

    @Override
    public int updateSortUp(int id, int min, int max) {
        int result = provinceMapper.updateSortUp(min,max);
        if(result==0)
            return 0;
        Province gc = new Province();
        gc.setId(id);
        gc.setSort_no(min);
        return provinceMapper.updateByPrimaryKeySelective(gc);
    }

    @Override
    public int updateSortDown(int id, int min, int max) {
        int result = provinceMapper.updateSortDown(min,max);
        if(result==0)
            return 0;
        Province gc = new Province();
        gc.setId(id);
        gc.setSort_no(max);
        return provinceMapper.updateByPrimaryKeySelective(gc);
    }

}
