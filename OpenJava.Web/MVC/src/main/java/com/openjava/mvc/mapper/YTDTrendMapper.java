package com.openjava.mvc.mapper;

import com.openjava.mvc.model.YTDTrendModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YTDTrendMapper {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO YTDTrends(Year,Month,ItemName,YTDMoney) VALUES(#{Year},#{Month},#{ItemName},#{YTDMoney})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'YTDTrends')", before = false, keyProperty = "id", resultType = int.class)
    int insert(YTDTrendModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM YTDTrends WHERE id=#{id}")
    YTDTrendModel selectById(int id);


    // 根据 Year和Month 查询
    @Select("SELECT * FROM YTDTrends WHERE Year=#{year} and Month=#{month}")
    YTDTrendModel select(String year,String month);

    // 查询全部
    @Select("SELECT * FROM YTDTrends")
    List<YTDTrendModel> selectAll();

    // 更新 value
    @Update("UPDATE YTDTrends SET ItemName=#{ItemName},YTDMoney=#{YTDMoney},Year=#{Year},Month=#{Month} WHERE id=#{id}")
    int updateValue(YTDTrendModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM YTDTrends WHERE id=#{id}")
    int deleteById(Integer id);

    // 根据 Year和Month 删除
    @Delete("DELETE FROM YTDTrends WHERE Year=#{year} and Month=#{month}")
    int delete(String year,String month);
}
