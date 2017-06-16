package com.ymall.controller.admin;

import com.ymall.annotation.AdminReqired;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Category;
import com.ymall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zc on 2017/6/14.
 */
@RestController
@RequestMapping("/admin/category")

public class CategoryMangerController {

    @Autowired
    private CategoryService categoryService;

    @AdminReqired
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public ServerResponse addCategory(@RequestParam String categoryName,
                                      @RequestParam(defaultValue = "0") Integer parentId,
                                      String categoryImage) throws IllegalException {
        return categoryService.addCategory(categoryName, parentId,categoryImage);
    }

    /*
    Integer parentId;
    String name;
    Boolean status;
    Integer sortOrder;
    * */
    @AdminReqired
    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.PUT)
    public ServerResponse updateCategory(@PathVariable Integer categoryId, Category category) throws IllegalException {
        return categoryService.updateCategory(categoryId, category);

    }

    /*
    * 删除品类(打标记)
    * */
    @AdminReqired
    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.DELETE)
    public ServerResponse deleteCategory(@PathVariable Integer categoryId) throws IllegalException {
        return categoryService.setCategoryStatus(categoryId,false);
    }

}
