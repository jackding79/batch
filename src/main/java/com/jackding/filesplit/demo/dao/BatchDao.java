package com.jackding.filesplit.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;


public class BatchDao {
    @Autowired
    SqlSession sqlSession;
}
