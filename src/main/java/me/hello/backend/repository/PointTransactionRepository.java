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

    @Select("SELECT * FROM point_transactions")
    List<PointTransaction> findTransaction();

    @Select("SELECT * FROM point_transactions WHERE member_id = #{member_id}")
    List<PointTransaction> findTransactionsByUserId(String member_id);

    @Select("SELECT\n" +
            "\tCASE WHEN (SELECT sum(total_used_points) FROM point_limits pl where pl.member_id ='kimaab' and month_year ='2025-01' ) \n" +
            "\t - COALESCE(SUM(points), 0) - #{points} > 0 THEN 'N' ELSE 'Y' END AS OVER_YN\n" +
            "FROM\n" +
            "\tpoint_transactions\n" +
            "WHERE\n" +
            "\tmember_id = #{member_id}\n" +
            "\tAND transaction_type = 'use'\n" +
            "\tAND DATE_FORMAT(transaction_date, '%Y-%m') = '2025-01'")
    String checkLimitOver(PointTransaction transaction);

    @Select("" +
            "SELECT 'Y' as use_yn\n" +
            "FROM point_transactions\n" +
            "WHERE member_id = #{member_id} AND transaction_type = 'use' AND DATE_FORMAT(transaction_date, '%Y-%m') = '2025-01'\n" +
            "FOR UPDATE;\n")
    String selectPointTransactionForUpdate(PointTransaction transaction);
}
