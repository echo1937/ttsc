package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestPageHelper {

    @Test
    public void testPageHelper() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        TbItemExample example = new TbItemExample();

        PageHelper.startPage(2, 10);
        List<TbItem> list = mapper.selectByExample(example);

        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());

        }

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //这里的 PageInfo<> 的括号是怎么回事?
        long total = pageInfo.getTotal();
        System.out.println("共有商品: " + total);


    }
}
