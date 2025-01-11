DELIMITER $$

CREATE TRIGGER check_monthly_limit
BEFORE INSERT ON point_transactions
FOR EACH ROW
BEGIN
    DECLARE total_used INT;
    DECLARE current_month VARCHAR(7);
    DECLARE point_limit INT;

    -- 현재 연월 계산
    SET current_month = DATE_FORMAT(NEW.transaction_date, '%Y-%m');

    -- 해당 사용자의 현재 월 사용 포인트 합계 계산
    SELECT COALESCE(SUM(points), 0) INTO total_used
    FROM point_transactions
    WHERE member_id = NEW.member_id AND transaction_type = 'use' AND DATE_FORMAT(transaction_date, '%Y-%m') = current_month;

    -- 포인트 사용 한도 가져오기
    SELECT COALESCE(total_used_points, 0) INTO point_limit
    FROM point_limits
    WHERE member_id = NEW.member_id AND month_year = current_month;

    -- 한도를 초과하지 않는지 확인
    IF (total_used + NEW.points) > point_limit THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Monthly point usage limit exceeded';
    END IF;
END$$

DELIMITER ;
