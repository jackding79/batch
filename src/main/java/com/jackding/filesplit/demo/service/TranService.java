package com.jackding.filesplit.demo.service;

import com.jackding.filesplit.demo.dao.TranDao;
import com.jackding.filesplit.demo.entity.TranController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dh
 * @version 1.0
 * @date created by 2020/8/11 17:27
 * @description
 **/
@Service
public class TranService {
    @Autowired
    private TranDao dao;
    public TranController selectTranById(String tranId){
        return dao.selectTranById(tranId);
    }
}
