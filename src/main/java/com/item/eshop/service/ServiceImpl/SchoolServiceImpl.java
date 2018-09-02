package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.SchoolMapper;
import com.item.eshop.model.School;
import com.item.eshop.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "schoolService")
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return schoolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(School record) {
        School sc = record;
        sc.setId(null);
        return schoolMapper.insert(sc);
    }

    @Override
    public int insertSelective(School record) {
        School sc = record;
        sc.setId(null);
        return schoolMapper.insertSelective(sc);
    }

    @Override
    public School selectByPrimaryKey(Integer id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(School record) {
        School gc = record;
        gc.setSort_no(null);
        return schoolMapper.updateByPrimaryKeySelective(gc);
    }

    @Override
    public int updateByPrimaryKey(School record) {
        return schoolMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<School> selectByProvinceId(Integer id) {
        return schoolMapper.selectByProvinceId(id);
    }

    @Override
    public List<School> selectByName(String id) {
        return schoolMapper.selectByName(id);
    }

    @Override
    public List<School> selectMore() {
        return schoolMapper.selectMore();
    }

    @Override
    public List<School> selectMoreBySort() {
        return schoolMapper.selectMoreBySort();
    }

    @Override
    public int updateSortUp(int id, int min, int max) {
        int result = schoolMapper.updateSortUp(min,max);
        if(result==0)
            return 0;
        School gc = new School();
        gc.setId(id);
        gc.setSort_no(min);
        return schoolMapper.updateByPrimaryKeySelective(gc);
    }

    @Override
    public int updateSortDown(int id, int min, int max) {
        int result = schoolMapper.updateSortDown(min,max);
        if(result==0)
            return 0;
        School gc = new School();
        gc.setId(id);
        gc.setSort_no(max);
        return schoolMapper.updateByPrimaryKeySelective(gc);
    }

    @Override
    public int selectCountNum() {
        return schoolMapper.selectCountNum();
    }

    @Override
    public int updateBySort(int newSort, int oldSort) {
        return schoolMapper.updateBySort(newSort,oldSort);
    }

    @Override
    public int updateById(int sort_no, int id) {
        return schoolMapper.updateById(sort_no,id);
    }
}
