package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.web.form.CategoryForm;

/**<h2> CategoryService Class</h2>
 * <p>
 * Process for Displaying CategoryService
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CategoryService {
    /**
     * <h2> doSearchCategoryWithPagi</h2>
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
    public List<CategoryDto> doSearchCategoryWithPagi(int currentPage, int recordsPerPage, CategoryForm categoryForm);
    
    /**
     * <h2> doSearchCategoryList</h2>
     * <p>
     * Method to search category
     * </p>
     *
     * @param categoryForm
     * @return
     * @return List<CategoryDto>
     */
    public List<CategoryDto> doSearchCategoryList(CategoryForm categoryForm);
   
    /**
     * <h2> doGetCategoryList</h2>
     * <p>
     * mehtod to get category list
     * </p>
     *
     * @return
     * @return List<CategoryDto>
     */
    public List<CategoryDto> doGetCategoryList();

    /**
     * <h2> addCategory</h2>
     * <p>
     * method to add category
     * </p>
     *
     * @param categoryForm
     * @return void
     */
    public void addCategory(CategoryForm categoryForm);
  
    /**
     * <h2> deleteCategory</h2>
     * <p>
     * method to delete category
     * </p>
     *
     * @param catId
     * @return void
     */
    public void deleteCategory(Integer catId);

    /**
     * <h2> getCategoryById</h2>
     * <p>
     * method to get category by id
     * </p>
     *
     * @param catId
     * @return
     * @return CategoryForm
     */
    public CategoryForm getCategoryById(int catId);

    /**
     * <h2> updateCategory</h2>
     * <p>
     * method to update category
     * </p>
     *
     * @param categoryForm
     * @return void
     */
    public void updateCategory(CategoryForm categoryForm);

     public boolean isCategoryCodeExist(String catCode);
}