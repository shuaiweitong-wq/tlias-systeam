package com.tsw.mapper;

import com.tsw.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询员工数量
     * @return
     */
//    @Select("select count(*) from emp")
//    Long getCount();

    /**
     * 分页查询员工
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> getEmpList(Integer start, Integer pageSize);

//    @Select("select * from emp")
//    List<Emp> getEmpList();

    List<Emp> getEmpList(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    void login(Emp emp);
}
