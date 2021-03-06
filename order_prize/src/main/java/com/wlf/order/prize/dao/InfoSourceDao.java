package com.wlf.order.prize.dao;

import com.wlf.order.prize.model.InfoSourceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoSourceDao extends JpaRepository<InfoSourceItem, Long> {
    @Query(value = "select * from t_infosource i where i.order_id = ?1 and i.result_code != ?2", nativeQuery = true)
    List<InfoSourceItem> findAllByOrderIdAndResultCode(String orderId, String errorCode);

    @Modifying
    @Query("update InfoSourceItem i set i.resultCode = ?5, i.errorMsg = ?6, i.failedCount = ?7, i.timestamp = ?4 " +
            "where i.orderId = ?2 and i.timestamp = ?3 and i.activityId = ?1")
    void updateResultCodeByOrderIdAndActivityId(String activityId, String orderId, Long oldTimestamp, Long newTimestamp,
                                                String resultCode, String errorMsg, Integer failedCount);
}
