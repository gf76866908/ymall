package until;

import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.pojo.Category;
import com.ymall.service.CategoryService;
import com.ymall.service.impl.CategoryServiceImpl;

import java.util.List;

/**
 * Created by zc on 2017/6/14.
 */
public class TestCategory {
    public static void main(String[] args) throws IllegalException {
        CategoryService categoryService=new CategoryServiceImpl();
        ServerResponse<List<Category>> childrenParallelCategory = categoryService.getChildrenParallelCategory(1);
        System.out.println(childrenParallelCategory.getData().size());
    }

}
