package com.baiyun.devhist.test;

import com.baiyun.devhist.domain.Category;
import com.baiyun.devhist.service.CategoryService;
import com.baiyun.devhist.service.impl.CategoryServiceImpl;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Test
    public void getAllCategory() {
        List<Category> allCategory = categoryService.getAllCategory();
        System.out.println("allCategory = " + allCategory);
    }

    @Test
    public void covert() throws UnsupportedEncodingException {
        String str = new String("老衲".getBytes(), "ISO-8859-1");
        System.out.println(str);
        String newStr = new String(str.getBytes("ISO-8859-1"), "utf-8");
        System.out.println("newStr = " + newStr);
    }
}