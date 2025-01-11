package me.hello.backend.repository;

import me.hello.backend.model.PointTransaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PointTransactionRepository {

    @Insert("INSERT INTO point_transactions (member_id, transaction_type, points, transaction_date) VALUES (#{member_id}, #{transaction_type}, #{points}, #{transaction_date})")
    void insertTransaction(PointTransaction transaction);

    @Select("SELECT * FROM point_transactions WHERE transaction_id = #{transaction_id}")
    PointTransaction findTransactionById(int id);

    @Select("SELECT * FROM point_transactions WHERE member_id = #{member_id}")
    List<PointTransaction> findTransactionsByUserId(String member_id);
}
