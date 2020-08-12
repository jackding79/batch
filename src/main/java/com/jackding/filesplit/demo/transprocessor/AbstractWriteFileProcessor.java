package com.jackding.filesplit.demo.transprocessor;

import com.jackding.filesplit.demo.data.BatchData;

/**
 * @author dh
 * @version 1.0
 * @date created by 2020/8/11 17:38
 * @description
 **/
public abstract class AbstractWriteFileProcessor implements TransProcessor {
    private BatchData batchData;
    public String fileName(){
        return "";
    }

}
