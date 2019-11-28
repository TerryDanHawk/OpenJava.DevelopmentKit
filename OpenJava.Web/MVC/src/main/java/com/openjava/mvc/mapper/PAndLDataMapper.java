package com.openjava.mvc.mapper;

import com.openjava.mvc.model.PAndLDataModel;
import com.openjava.mvc.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PAndLDataMapper {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO PAndLDatas(Year,Month,AccountDescription,MonthActual,YTDActual) VALUES(#{Year},#{Month},#{AccountDescription},#{MonthActual},#{YTDActual})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'PAndLDatas')", before = false, keyProperty = "id", resultType = int.class)
    int insert(PAndLDataModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM PAndLDatas WHERE id=#{id}")
    PAndLDataModel selectById(int id);


    // 根据 Year和Month 查询
    @Select("SELECT * FROM PAndLDatas WHERE Year=#{year} and Month=#{month}")
    List<PAndLDataModel> select(String year,String month);

    @Select("SELECT * FROM PAndLDatas WHERE Year=#{year} and Month=#{month} and AccountDescription=#{accountdescription} limit 1")
    PAndLDataModel GetItem(String year,String month,String accountdescription);

    // 查询全部
    @Select("SELECT * FROM PAndLDatas")
    List<PAndLDataModel> selectAll();

    // 更新 value
    @Update("UPDATE PAndLDatas SET AccountDescription=#{AccountDescription},MonthActual=#{MonthActual},YTDActual=#{YTDActual} WHERE id=#{id}")
    int updateValue(PAndLDataModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM PAndLDatas WHERE id=#{id}")
    int deleteById(Integer id);


    // 根据 Year和Month 删除
    @Delete("DELETE FROM PAndLDatas WHERE Year=#{year} and Month=#{month}")
    int delete(String year,String month);
}
