package com.jackding.filesplit.demo.controller;

import com.jackding.filesplit.demo.entity.TranController;
import com.jackding.filesplit.demo.service.TranService;
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
    @GetMapping("/{tranId}")
    public TranController getTran(@PathVariable String tranId){
        return service.selectTranById(tranId);
    }
}
