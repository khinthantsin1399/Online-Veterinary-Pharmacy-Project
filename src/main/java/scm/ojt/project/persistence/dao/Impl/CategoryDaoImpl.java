package scm.ojt.project.persistence.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.persistence.dao.CategoryDao;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.web.form.CategoryForm;

/**
 * <h2>CategoryDaoImpl Class</h2>
 * <p>
 * Process for Displaying CategoryDaoImpl
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Repository
@Transactional
@SuppressWarnings({ "unchecked", "deprecation" })
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public static String SELECT_CATEGORY_LIST_HQL = "FROM Category c " + "WHERE c.deletedAt IS NUll ";

    public static String SELECT_CATEGORY_BY_SEARCH_DATA = "AND (c.category_code like :category_code "
            + "OR c.category_name like :category_name) ";

    /**
     * <h2>dbGetCategoryListPagi</h2>
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
    @SuppressWarnings("rawtypes")
    @Override
    public List<Category> dbGetCategoryListPagi(int currentPage, int noOfCategory, CategoryForm categoryForm) {
        int start = currentPage * noOfCategory - noOfCategory;
        Query queryCategoryList = dbCreateQueryList(categoryForm);
        queryCategoryList.setFirstResult(start);
        queryCategoryList.setMaxResults(noOfCategory);

        List<Category> categoryList = (List<Category>) queryCategoryList.list();

        return categoryList;

    }

    /**
     * <h2>dbSearchCategoryList</h2>
     * <p>
     * method to search category
     * </p>
     *
     * @param categoryForm
     * @return
     * @return List<Category>
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<Category> dbSearchCategoryList(CategoryForm categoryForm) {
        Query queryCategoryList = dbCreateQueryList(categoryForm);

        List<Category> categoryList = (List<Category>) queryCategoryList.list();
        return categoryList;
    }

    /**
     * <h2>doGetCategoryList</h2>
     * <p>
     * method to retrieve category list
     * </p>
     *
     * @return
     * @return List<Category>
     */
    @Override
    public List<Category> doGetCategoryList() {

        @SuppressWarnings("rawtypes")
        Query query = sessionFactory.getCurrentSession().createQuery("from Category");
        return query.getResultList();

    }

    /**
     * <h2>addCategory</h2>
     * <p>
     * method to add category
     * </p>
     *
     * @param category
     * @param currentDate
     * @return void
     */
    @Override
    public void addCategory(Category category, Date currentDate) {
        category.setCreatedAt(currentDate);
        category.setUpdatedAt(currentDate);
        sessionFactory.getCurrentSession().saveOrUpdate(category);
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
    public void deleteCategory(Integer catId) {
        Category category = (Category) sessionFactory.getCurrentSession().load(Category.class, catId);
        if (null != category) {
            this.sessionFactory.getCurrentSession().delete(category);
        }
    }

    /**
     * <h2>getCategoryById</h2>
     * <p>
     * method to get category by id
     * </p>
     *
     * @param catId
     * @return
     * @return Category
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Category getCategoryById(int catId) {
        Query queryCategoryById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Category c where c.category_id = :id");
        queryCategoryById.setParameter("id", catId);
        Category resultCategory = (Category) queryCategoryById.uniqueResult();
        return resultCategory;
    }

    /**
     * <h2>updateCategory</h2>
     * <p>
     * method to update category
     * </p>
     *
     * @param category
     * @return void
     */
    @Override
    public void updateCategory(Category category) {
        this.sessionFactory.getCurrentSession().update(category);
    }

    /**
     * <h2>dbCreateQueryList</h2>
     * <p>
     * method to create search query
     * </p>
     *
     * @param categoryForm
     * @return
     * @return Query
     */
    @SuppressWarnings("rawtypes")
    private Query dbCreateQueryList(CategoryForm categoryForm) {
        StringBuffer query = new StringBuffer(SELECT_CATEGORY_LIST_HQL);

        if (categoryForm != null
                && (categoryForm.getCategory_code() != null || categoryForm.getCategory_name() != null)) {
            query.append(SELECT_CATEGORY_BY_SEARCH_DATA);
        }
        Query queryCategoryList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if (categoryForm != null
                && (categoryForm.getCategory_code() != null || categoryForm.getCategory_name() != null)) {
            queryCategoryList.setParameter("category_code", "%" + categoryForm.getCategory_code() + "%");
            queryCategoryList.setParameter("category_name", "%" + categoryForm.getCategory_name() + "%");

        }
        return queryCategoryList;
    }

    /**
     * <h2>getCategoryName</h2>
     * <p>
     * method to category name
     * </p>
     *
     * @param categoryForm
     * @return
     * @return String
     */
    @SuppressWarnings("rawtypes")
    @Override
    public String getCategoryName(CategoryForm categoryForm) {
        Query queryCategoryName = this.sessionFactory.getCurrentSession().createQuery(
                "SELECT c.category_name FROM Category c JOIN Medicine m where c.category_id = m.category_id");
        queryCategoryName.setParameter("category_name", categoryForm.getCategory_name());
        String resultCategory = (String) queryCategoryName.uniqueResult();
        return resultCategory;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Category isCategoryCodeExist(String catCode) {
        String HqlQuery = "SELECT c FROM Category c where c.category_code = :category_code";
        Query queryisExist = this.sessionFactory.getCurrentSession().createQuery(HqlQuery);
        queryisExist.setParameter("category_code", catCode);
        Category resultCategory = (Category) queryisExist.uniqueResult();

        return resultCategory;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Category isCategoryNameExist(String catName) {
        String HqlQuery = "SELECT c FROM Category c where c.category_name = :category_name";
        Query queryisExist = this.sessionFactory.getCurrentSession().createQuery(HqlQuery);
        queryisExist.setParameter("category_name", catName);
        Category resultCategory = (Category) queryisExist.uniqueResult();

        return resultCategory;
    }
}