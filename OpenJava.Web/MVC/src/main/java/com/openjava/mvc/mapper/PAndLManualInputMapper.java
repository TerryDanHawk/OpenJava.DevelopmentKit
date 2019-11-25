package com.openjava.mvc.mapper;

import com.openjava.mvc.model.PAndLManualInputModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PAndLManualInputMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO PAndLManualInputs(Year,Month,Item,Value) VALUES(#{Year},#{Month},#{Item},#{Value})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'PAndLManualInputs')", before = false, keyProperty = "id", resultType = int.class)
    int insert(PAndLManualInputModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM PAndLManualInputs WHERE id=#{id}")
    PAndLManualInputModel selectById(int id);


    // 根据 Year和Month 查询
    @Select("SELECT * FROM PAndLManualInputs WHERE Year=#{year} and Month=#{month}")
    PAndLManualInputModel select(String year,String month);

    // 查询全部
    @Select("SELECT * FROM PAndLManualInputs")
    List<PAndLManualInputModel> selectAll();

    // 更新 value
    @Update("UPDATE PAndLManualInputs SET Item=#{Item},Value=#{Value},Year=#{Year},Month=#{Month} WHERE id=#{id}")
    int updateValue(PAndLManualInputModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM PAndLManualInputs WHERE id=#{id}")
    int deleteById(Integer id);

    // 根据 Year和Month 删除
    @Delete("DELETE FROM PAndLManualInputs WHERE Year=#{year} and Month=#{month}")
    int delete(String year,String month);

}
