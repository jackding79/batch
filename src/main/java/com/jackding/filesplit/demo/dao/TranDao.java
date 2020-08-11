package com.jackding.filesplit.demo.dao;

import com.jackding.filesplit.demo.entity.TranController;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dh
 * @version 1.0
 * @date created by 2020/8/11 17:17
 * @description
 **/
@Mapper
public interface TranDao {
    @Select("select tranId,isFile,splitKey,splitMode,maxThread from tran_controller where tranId=#{tranId}")
    TranController selectTranById(String tranId);
}
