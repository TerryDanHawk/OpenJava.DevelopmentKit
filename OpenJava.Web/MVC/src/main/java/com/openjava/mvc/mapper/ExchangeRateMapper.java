package com.openjava.mvc.mapper;

import com.openjava.mvc.model.ExchangeRateModel;
import com.openjava.mvc.model.PAndLDataModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExchangeRateMapper {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO ExchangeRates(Year,Month,FmCurr,ToCurr,Category,Rate) VALUES(#{Year},#{Month},#{FmCurr},#{ToCurr},#{Category},#{Rate})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'ExchangeRates')", before = false, keyProperty = "id", resultType = int.class)
    int insert(ExchangeRateModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM ExchangeRates WHERE id=#{id}")
    ExchangeRateModel selectById(int id);


    // 根据 Year和Month 查询
    @Select("SELECT * FROM ExchangeRates WHERE Year=#{year} and Month=#{month}")
    ExchangeRateModel select(String year,String month);

    // 查询全部
    @Select("SELECT * FROM ExchangeRates")
    List<ExchangeRateModel> selectAll();

    // 更新 value
    @Update("UPDATE ExchangeRates SET FmCurr=#{FmCurr},ToCurr=#{ToCurr},Category=#{Category},Rate=#{Rate} WHERE id=#{id}")
    int updateValue(ExchangeRateModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM ExchangeRates WHERE id=#{id}")
    int deleteById(Integer id);

    // 根据 Year和Month 删除
    @Delete("DELETE FROM ExchangeRates WHERE Year=#{year} and Month=#{month}")
    int delete(String year,String month);
}