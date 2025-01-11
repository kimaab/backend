package me.hello.backend.repository;

import me.hello.backend.model.PointLimit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PointLimitRepository {

    @Insert("INSERT INTO point_limits (member_id, month_year, total_used_points) VALUES (#{member_id}, #{month_year}, #{total_used_points})")
    void insertPointLimit(PointLimit pointLimit);

    @Select("SELECT * FROM point_limits WHERE limit_id = #{limit_id}")
    PointLimit findPointLimitById(int limit_id);

    @Select("SELECT * FROM point_limits WHERE member_id = #{member_id}")
    List<PointLimit> findPointLimitsByUserId(String member_id);

    @Update("UPDATE point_limits SET month_year = #{pointLimit.month_year}, total_used_points = #{pointLimit.total_used_points} WHERE limit_id = #{limit_id}")
    void updatePointLimit(@Param("limit_id") int limit_id, @Param("pointLimit") PointLimit pointLimit);

    @Delete("DELETE FROM point_limits WHERE limit_id = #{limit_id}")
    void deletePointLimit(int limit_id);
}
