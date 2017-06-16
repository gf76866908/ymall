package com.ymall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.dao.CategoryMapper;
import com.ymall.pojo.Category;
import com.ymall.service.CategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId,String categoryImage) throws IllegalException {

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setCategoryImage(categoryImage);
        category.setStatus(true);//这个分类是可用的

        int rowCount = categoryMapper.insertSelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        throw new IllegalException("添加品类失败");
    }


    public ServerResponse updateCategory(Integer categoryId,Category category) throws IllegalException {
        category.setId(categoryId);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("更新品类成功");
        }
        throw new IllegalException("更新品类失败");
    }

    //获取一层子分类(不递归)
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId) throws IllegalException {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(parentId);
        if(CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到当前分类的子分类");
            throw new IllegalException("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }


    /**
     * 递归查询本节点的id及孩子节点
     * @param categoryId
     * @return
     */
    public ServerResponse<Set<Category>> selectAllChildren(Integer categoryId) throws IllegalException {

        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        if(categorySet.isEmpty()){
            logger.info("未找到当前分类的递归子分类");
        }
        return ServerResponse.createBySuccess(categorySet);
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    public List<Integer> selectAllChildrenId(Integer categoryId) throws IllegalException {

        Set<Category> categorySet=selectAllChildren(categoryId).getData();

        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return categoryIdList;
    }


    public ServerResponse setCategoryStatus(Integer categoryId,Boolean status) throws IllegalException {
        Category category=new Category();
        category.setId(categoryId);
        category.setStatus(status);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更改品类状态成功");
        }
        throw new IllegalException("更爱品类状态失败");
    }


    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet ,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }






}
