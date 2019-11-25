package com.openjava.mvc.mapper;

import com.openjava.mvc.model.MonthTrendModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MonthTrendMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO MonthTrends(Year,Month,ItemName,MonthMoney) VALUES(#{Year},#{Month},#{ItemName},#{MonthMoney})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'MonthTrends')", before = false, keyProperty = "id", resultType = int.class)
    int insert(MonthTrendModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM MonthTrends WHERE id=#{id}")
    MonthTrendModel selectById(int id);


    // 根据 Year和Month 查询
    @Select("SELECT * FROM MonthTrends WHERE Year=#{year} and Month=#{month}")
    MonthTrendModel select(String year,String month);

    // 查询全部
    @Select("SELECT * FROM MonthTrends")
    List<MonthTrendModel> selectAll();

    // 更新 value
    @Update("UPDATE MonthTrends SET ItemName=#{ItemName},MonthMoney=#{MonthMoney},Year=#{Year},Month=#{Month} WHERE id=#{id}")
    int updateValue(MonthTrendModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM MonthTrends WHERE id=#{id}")
    int deleteById(Integer id);

    // 根据 Year和Month 删除
    @Delete("DELETE FROM MonthTrends WHERE Year=#{year} and Month=#{month}")
    int delete(String year,String month);
}
