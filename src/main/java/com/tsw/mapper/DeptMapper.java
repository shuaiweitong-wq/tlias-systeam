package com.tsw.mapper;

import com.tsw.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> getDeptList();

    @Delete("delete from dept where id=#{id}")
    void deleteDeptById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void updateDept(Dept dept);

    @Select("select * from dept where id= #{id}")
    Dept getDeptById(Integer id);
}
