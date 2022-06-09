package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.web.form.CategoryForm;

/**<h2> CategoryDao Class</h2>
 * <p>
 * Process for Displaying CategoryDao
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CategoryDao {
    /**
     * <h2> dbGetCategoryListPagi</h2>
     * <p>
     * method to get catagory list from database with pagination
     * </p>
     *
     * @param currentPage
     * @param noOfCategory
     * @param categoryForm
     * @return
     * @return List<Category>
     */
    public List<Category> dbGetCategoryListPagi(int currentPage, int noOfCategory, CategoryForm categoryForm);
    
    /**
     * <h2> dbSearchCategoryList</h2>
     * <p>
     * method to search category
     * </p>
     *
     * @param categoryForm
     * @return
     * @return List<Category>
     */
   
    public List<Category> dbSearchCategoryList(CategoryForm categoryForm);
    
    /**
     * <h2> doGetCategoryList</h2>
     * <p>
     * method to retrieve category list
     * </p>
     *
     * @return
     * @return List<Category>
     */
    public List<Category> doGetCategoryList();

    /**
     * <h2> addCategory</h2>
     * <p>
     * method to add category
     * </p>
     *
     * @param category
     * @param currentDate
     * @return void
     */
    public void addCategory(Category category, Date currentDate);

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
     * @return Category
     */
    public Category getCategoryById(int catId);

    /**
     * <h2> updateCategory</h2>
     * <p>
     * method to update category
     * </p>
     *
     * @param category
     * @return void
     */
    public void updateCategory(Category category);
    
    /**
     * <h2> getCategoryName</h2>
     * <p>
     * method to category name
     * </p>
     *
     * @param categoryForm
     * @return
     * @return String
     */
    public String getCategoryName(CategoryForm categoryForm);

     /**
     * <h2> isCategoryCodeExist</h2>
     * <p>
     *  method to check category code exists or not
     * </p>
     *
     * @param catCode
     * @return
     * @return Category
     */
    public Category isCategoryCodeExist(String catCode);

    /**
     * <h2> isCategoryNameExist</h2>
     * <p>
     *  method to check category name exists or not
     * </p>
     *
     * @param catName
     * @return
     * @return Category
     */
    public Category isCategoryNameExist(String catName);
    
    /**
     * <h2>dbGetCategoryCount</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return long
     */
    public long dbGetCategoryCount();
}