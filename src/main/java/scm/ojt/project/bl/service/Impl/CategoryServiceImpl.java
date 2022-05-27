package scm.ojt.project.bl.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.bl.service.CategoryService;
import scm.ojt.project.persistence.dao.CategoryDao;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.web.form.CategoryForm;

/**
 * <h2>CategoryServiceImpl Class</h2>
 * <p>
 * Process for Displaying CategoryServiceImpl
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    /**
     * <h2>doSearchCategoryWithPagi</h2>
     * <p>
     * Method to search category with pagination
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param categoryForm
     * @return
     * @return List<CategoryDto>
     */
    @Override
    public List<CategoryDto> doSearchCategoryWithPagi(int currentPage, int recordsPerPage, CategoryForm categoryForm) {
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        List<Category> categoryList = this.categoryDao.dbGetCategoryListPagi(currentPage, recordsPerPage, categoryForm);
        for (Category category : categoryList) {
            CategoryDto categoryDto = new CategoryDto(category);

            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    /**
     * <h2>doSearchCategoryList</h2>
     * <p>
     * Method to search category
     * </p>
     *
     * @param categoryForm
     * @return
     * @return List<CategoryDto>
     */
    @Override
    public List<CategoryDto> doSearchCategoryList(CategoryForm categoryForm) {
        List<Category> categoryList = (List<Category>) this.categoryDao.dbSearchCategoryList(categoryForm);
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDto Entity2Dto = new CategoryDto(category);
            categoryDtoList.add(Entity2Dto);
        }
        return categoryDtoList;
    }

    /**
     * <h2>doGetCategoryList</h2>
     * <p>
     * mehtod to get category list
     * </p>
     *
     * @return
     * @return List<CategoryDto>
     */
    @Override
    public List<CategoryDto> doGetCategoryList() {
        List<Category> categoryForm = (List<Category>) this.categoryDao.doGetCategoryList();
        List<CategoryDto> listCategoryDTO = new ArrayList<>();
        for (Category category : categoryForm) {
            CategoryDto entity2Dto = new CategoryDto(category);
            listCategoryDTO.add(entity2Dto);
        }
        return listCategoryDTO;
    }

    /**
     * <h2>addCategory</h2>
     * <p>
     * method to add category
     * </p>
     *
     * @param categoryForm
     * @return void
     */
    @Override
    public void addCategory(CategoryForm categoryForm) {
        Date currentDate = new Date();
        Category category = new Category(categoryForm);
        this.categoryDao.addCategory(category, currentDate);
    }

    /**
     * <h2>deleteCategory</h2>
     * <p>
     * method to delete category
     * </p>
     *
     * @param catId
     * @return void
     */
    @Override
    @Transactional
    public void deleteCategory(Integer catId) {
        categoryDao.deleteCategory(catId);
    }

    @Override
    @Transactional
    public CategoryForm getCategoryById(int catId) {
        Category resultCategory = this.categoryDao.getCategoryById(catId);
        CategoryForm resultCategoryform = resultCategory != null ? new CategoryForm(resultCategory) : null;

        return resultCategoryform;
    }

    /**
     * <h2>getCategoryById</h2>
     * <p>
     * method to get category by id
     * </p>
     *
     * @param catId
     * @return
     * @return CategoryForm
     */
    @Override
    public void updateCategory(CategoryForm categoryForm) {
        Category category = new Category(categoryForm);
        Date currentDate = new Date();
        Category updateCategory = this.categoryDao.getCategoryById(category.getCategory_id());
        updateCategory.setCategory_code(categoryForm.getCategory_code());
        updateCategory.setCategory_name(categoryForm.getCategory_name());
        // updateCategory.setCreatedAt(categoryForm.getCreatedAt());
        updateCategory.setUpdatedAt(currentDate);
        this.categoryDao.updateCategory(updateCategory);
    }

    
      @Override public boolean isCategoryCodeExist(String catCode) { Category
      resultCategory = this.categoryDao.isCategoryCodeExist(catCode); boolean
      categoryCodeExist = false; 
      if (resultCategory != null) { 
          categoryCodeExist = true;
     }
     
     return categoryCodeExist;
     }
     
}
