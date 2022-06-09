package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.web.form.CategoryForm;

/**
 * <h2>CategoryService Class</h2>
 * <p>
 * Process for Displaying CategoryService
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CategoryService {
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
	public List<CategoryDto> doSearchCategoryWithPagi(int currentPage, int recordsPerPage, CategoryForm categoryForm);

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
	public List<CategoryDto> doSearchCategoryList(CategoryForm categoryForm);

	/**
	 * <h2>doGetCategoryList</h2>
	 * <p>
	 * mehtod to get category list
	 * </p>
	 *
	 * @return
	 * @return List<CategoryDto>
	 */
	public List<CategoryDto> doGetCategoryList();

	/**
	 * <h2>addCategory</h2>
	 * <p>
	 * method to add category
	 * </p>
	 *
	 * @param categoryForm
	 * @return void
	 */
	public void addCategory(CategoryForm categoryForm);

	/**
	 * <h2>deleteCategory</h2>
	 * <p>
	 * method to delete category
	 * </p>
	 *
	 * @param catId
	 * @return void
	 */
	public void deleteCategory(Integer catId);

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
	public CategoryForm getCategoryById(int catId);

	/**
	 * <h2>updateCategory</h2>
	 * <p>
	 * method to update category
	 * </p>
	 *
	 * @param categoryForm
	 * @return void
	 */
	public void updateCategory(CategoryForm categoryForm);

	/**
	 * <h2>isCategoryCodeExist</h2>
	 * <p>
	 *  method to check category code exists or not
	 * </p>
	 *
	 * @param catCode
	 * @return
	 * @return boolean
	 */
	public boolean isCategoryCodeExist(String catCode);
	/**
     * <h2>isCategoryNameExist</h2>
     * <p>
     * method to check category name exists or not
     * </p>
     * 
     * @param catName
     * @return
     */
    boolean isCategoryNameExist(String catName);
    
    /**
     * <h2>dbGetCategoryCount</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return long
     */
    public long doGetCategoryCount();

    /**
     * <h2> isUpdateCategoryCodeExist </h2>
     * <p>
     * method to check update category code exists or not
     * </p>
     * 
     * @param categoryCode
     * @param categoryId
     * @return
     */
    boolean isUpdateCategoryCodeExist(String categoryCode, int categoryId);

    /**
     * <h2> isUpdateCategoryNameExist </h2>
     * <p>
     * method to check update category name exists or not
     * </p>
     * 
     * @param category_name
     * @param id
     * @return
     */
    boolean isUpdateCategoryNameExist(String categoryName, Integer categoryId);
}