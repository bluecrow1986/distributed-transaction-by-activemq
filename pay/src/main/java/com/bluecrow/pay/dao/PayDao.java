package com.bluecrow.pay.dao;

import com.bluecrow.pay.entity.Pay;

public interface PayDao {

    int insert(Pay record);

    Pay selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Pay record);

    int updateStageId(Pay record);
}