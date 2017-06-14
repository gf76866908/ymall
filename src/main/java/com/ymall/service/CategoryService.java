package com.ymall.service;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Category;

import java.util.List;
import java.util.Set;


public interface CategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId,String categoryImage) throws IllegalException;

    ServerResponse updateCategory(Integer categoryId, Category categoryName) throws IllegalException;
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId) throws IllegalException;
    Set<Category> selectAllChildren(Integer parentId) throws IllegalException;
    List<Integer> selectAllChildrenId(Integer parentId) throws IllegalException;

    ServerResponse setCategoryStatus(Integer categoryId,Boolean status) throws IllegalException;
}
