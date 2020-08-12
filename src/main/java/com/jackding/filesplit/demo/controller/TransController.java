package com.jackding.filesplit.demo.controller;

import com.jackding.filesplit.demo.annotation.Tran;
import com.jackding.filesplit.demo.entity.TranController;
import com.jackding.filesplit.demo.service.TranService;
import com.jackding.filesplit.demo.trans.app001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dh
 * @version 1.0
 * @date created by 2020/8/11 17:29
 * @description
 **/
@RestController
@RequestMapping("/tran")
public class TransController {
    @Autowired
    TranService service;
    @Autowired
    app001 tran;
    @GetMapping("/{tranId}")
    public TranController getTran(@PathVariable String tranId){
        System.out.println(tran);
        return service.selectTranById(tranId);
    }
}
