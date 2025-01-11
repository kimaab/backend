package me.hello.backend.repository;

import me.hello.backend.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {

    @Insert("INSERT INTO member (id, pwd, name, email) VALUES (#{id}, #{pwd}, #{name}, #{email})")
    void insertUser(User user);

    @Select("SELECT * FROM member WHERE id = #{id}")
    User findUserById(String id);

    @Select("SELECT * FROM member")
    List<User> findAllUsers();

    @Update("UPDATE member SET pwd = #{user.pwd}, name = #{user.name}, email = #{user.email} WHERE id = #{id}")
    void updateUser(@Param("id") String id, @Param("user") User user);

    @Delete("DELETE FROM member WHERE id = #{id}")
    void deleteUser(String id);
}
