package com.openjava.mvc.mapper;
import com.openjava.mvc.model.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO Users(UserName,Password,RealName) VALUES(#{UserName}, #{Password},#{RealName})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'Users')", before = false, keyProperty = "id", resultType = int.class)
    int insert(UserModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM Users WHERE id=#{id}")
    UserModel select(int id);

    // 查询全部
    @Select("SELECT * FROM Users")
    List<UserModel> selectAll();

    // 更新 value
    @Update("UPDATE Users SET RealName=#{RealName} WHERE id=#{id}")
    int updateValue(UserModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM Users WHERE id=#{id}")
    int delete(Integer id);
}
