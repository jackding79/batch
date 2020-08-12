package com.jackding.filesplit.demo.trans;

import com.jackding.filesplit.demo.annotation.Tran;
import com.jackding.filesplit.demo.transprocessor.AbstractWriteFileProcessor;
import org.springframework.stereotype.Component;

@Tran
public class app001 extends AbstractWriteFileProcessor {
    @Override
    public void process() {

    }

    @Override
    public String fileName() {
        return "E:\\out.txt";
    }
}
