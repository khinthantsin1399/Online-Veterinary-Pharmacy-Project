package scm.ojt.project.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.bl.service.CategoryService;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.web.form.CategoryForm;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpSession session;

    /**
     * <h2>getCategoryList</h2>
     * <p>
     * Getting category list
     * </p>
     *
     * @param request
     * @param categoryForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ModelAndView getCategoryList(HttpServletRequest request, CategoryForm categoryForm) throws IOException {

        ModelAndView categoryListView = new ModelAndView("categoryList");

        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(categoryListView, currentPage, recordsPerPage, false, categoryForm);
        return categoryListView;

    }

    /**
     * <h2>newCategory</h2>
     * <p>
     * showing category form
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createCategory", method = RequestMethod.GET)
    public ModelAndView newCategory(ModelAndView model) {
        Category category = new Category();
        ModelAndView createCategory = new ModelAndView("createCategory");
        createCategory.addObject("createCategoryForm", category);
        createCategory.setViewName("createCategory");
        return createCategory;
    }

    /**
     * <h2>insertCategory</h2>
     * <p>
     * confirmimg new added category
     * </p>
     *
     * @param categoryForm
     * @param result
     * @param request
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/createCategoryConfirm", params = "confirmCategory", method = RequestMethod.POST)
    public ModelAndView insertCategory(@ModelAttribute("createCategoryForm") @Valid CategoryForm categoryForm,
            BindingResult result, HttpServletRequest request) throws ParseException {
        ModelAndView ConfirmView = new ModelAndView("createCategoryConfirm");
        if (result.hasErrors()) {
            ConfirmView = new ModelAndView("createCategory");
            ConfirmView.addObject("createCategoryForm", categoryForm);
            ConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0001", null, null));
        } else if (this.categoryService.isCategoryCodeExist(categoryForm.getCategory_code())) {
            ConfirmView = new ModelAndView("createCategory");
            ConfirmView.addObject("createCategoryForm", categoryForm);
            ConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0003", null, null));
        } else if (this.categoryService.isCategoryNameExist(categoryForm.getCategory_name())) {
            ConfirmView = new ModelAndView("createCategory");
            ConfirmView.addObject("createCategoryForm", categoryForm);
            ConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
        } else {
            ConfirmView.addObject("categoryForm", categoryForm);
            ConfirmView.setViewName("createCategoryConfirm");
        }
        return ConfirmView;
    }

    /**
     * <h2>createCategoryConfirm</h2>
     * <p>
     * Inserting new category
     * </p>
     *
     * @param categoryForm
     * @param result
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertCategory", params = "addCategory", method = RequestMethod.POST)
    public ModelAndView createCategoryConfirm(@ModelAttribute("categoryForm") @Valid CategoryForm categoryForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        this.categoryService.addCategory(categoryForm);
        ModelAndView createCategoryView = new ModelAndView("redirect:/categoryList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0005", null, null));
        return createCategoryView;
    }

    /**
     * <h2>cancelCategoryConfirm</h2>
     * <p>
     * Canceling inserted category in form
     * </p>
     *
     * @param categoryForm
     * @param result
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertCategory", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelCategoryConfirm(@ModelAttribute("categoryForm") @Valid CategoryForm categoryForm,
            BindingResult result) throws ParseException {
        ModelAndView createCategoryView = new ModelAndView("createCategory");
        createCategoryView.addObject("createCategoryForm", categoryForm);
        return createCategoryView;
    }

    /**
     * <h2>deleteCategory</h2>
     * <p>
     * Deleting category
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public ModelAndView deleteCategory(HttpServletRequest request) {
        int catId = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteCategory(catId);
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0006", null, null));
        return new ModelAndView("redirect:/categoryList");
    }

    /**
     * <h2>editCategory</h2>
     * <p>
     * Showing update form
     * </p>
     *
     * @param catId
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
    public ModelAndView editCategory(@RequestParam("id") Integer catId, HttpServletRequest request) {
        CategoryForm category = categoryService.getCategoryById(catId);
        ModelAndView model = new ModelAndView("updateCategory");
        model.addObject("category", category);
        model.setViewName("updateCategory");
        return model;
    }

    /**
     * <h2>callUpdateCategoryConfirm</h2>
     * <p>
     * Confirming updated category
     * </p>
     *
     * @param updateCategoryForm
     * @param result
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateCategoryConfirm", method = RequestMethod.POST)
    public ModelAndView callUpdateCategoryConfirm(@ModelAttribute("category") @Valid CategoryForm updateCategoryForm,
            BindingResult result) throws ParseException {
        ModelAndView updateConfirmView = new ModelAndView("updateCategoryConfirm");
        updateConfirmView.addObject("updateCategoryForm", updateCategoryForm);
        if (result.hasErrors()) {
            updateConfirmView = new ModelAndView("updateCategory");
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0002", null, null));
            return updateConfirmView;
        } else if (this.categoryService.isUpdateCategoryCodeExist(updateCategoryForm.getCategory_code(),updateCategoryForm.getCategory_id())) {
            updateConfirmView = new ModelAndView("updateCategory");
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0003", null, null));
            return updateConfirmView;

        } else if (this.categoryService.isUpdateCategoryNameExist(updateCategoryForm.getCategory_name(),updateCategoryForm.getCategory_id())) {
            updateConfirmView = new ModelAndView("updateCategory");
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
            return updateConfirmView;
        }
        return updateConfirmView;
    }

    /**
     * <h2>updateCategory</h2>
     * <p>
     * Updating category
     * </p>
     *
     * @param categoryForm
     * @param result
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editCategory", params = "update", method = RequestMethod.POST)
    public ModelAndView updateCategory(@ModelAttribute("finalConfirmCategoryForm") @Valid CategoryForm categoryForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {

        this.categoryService.updateCategory(categoryForm);
        ModelAndView updateCategoryView = new ModelAndView("redirect:/categoryList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0009", null, null));
        return updateCategoryView;
    }

    /**
     * <h2>cancelupdateCategory</h2>
     * <p>
     * Canceling category update
     * </p>
     *
     * @param categoryForm
     * @param result
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editCategory", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelupdateCategory(
            @ModelAttribute("finalConfirmCategoryForm") @Valid CategoryForm categoryForm, BindingResult result)
            throws ParseException {
        ModelAndView updateCategoryView = new ModelAndView("updateCategory");
        updateCategoryView.addObject("category", categoryForm);
        return updateCategoryView;
    }

    /**
     * <h2>searchCategory</h2>
     * <p>
     * Searching category by category name or code
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchCategory", method = RequestMethod.GET)
    public ModelAndView searchCategory(HttpServletRequest request) {

        CategoryForm categoryForm = new CategoryForm();
        String search_input = request.getParameter("search_input");
        ModelAndView categoryListView = new ModelAndView("categoryList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        if (search_input.isEmpty()) {
            this.getPagination(categoryListView, currentPage, recordsPerPage, false, categoryForm);
        } else {
            categoryForm.setCategory_name(search_input);
            if (categoryForm != null
                    && (categoryForm.getCategory_code() != null || categoryForm.getCategory_name() != null)) {

                this.getPagination(categoryListView, currentPage, recordsPerPage, true, categoryForm);
                categoryListView.addObject("searchData", search_input);
            } else {
                this.getPagination(categoryListView, currentPage, recordsPerPage, true, categoryForm);

                categoryListView.addObject("errorMsg", messageSource.getMessage("M_SC_0011", null, null));

            }
        }
        return categoryListView;
    }

    /**
     * <h2>searchCategory</h2>
     * <p>
     * Searching category
     * </p>
     *
     * @param search_input String
     * @param request      HttpServletRequest
     * @return
     * @return ModelAndView
     */

    @RequestMapping(value = "/searchCategory", params = "searchCategory", method = RequestMethod.POST)
    public ModelAndView searchCategory(@RequestParam("search-input") String search_input, HttpServletRequest requestt) {

        CategoryForm categoryForm = new CategoryForm();
        ModelAndView categoryListView = new ModelAndView("categoryList");

        if (search_input.isEmpty()) {
            this.getPagination(categoryListView, 1, 3, false, categoryForm);
            categoryListView.addObject("errorMsg", messageSource.getMessage("M_SC_0015", null, null));

        } else {
            categoryForm.setCategory_code(search_input);
            categoryForm.setCategory_name(search_input);
            if (categoryForm != null
                    && (categoryForm.getCategory_code() != null || categoryForm.getCategory_name() != null)) {

                this.getPagination(categoryListView, 1, 3, true, categoryForm);
                categoryListView.addObject("searchData", search_input);
            } else {
                this.getPagination(categoryListView, 1, 3, false, categoryForm);

                categoryListView.addObject("errorMsg", messageSource.getMessage("M_SC_0011", null, null));

            }
        }
        return categoryListView;
    }

    /**
     * <h2>getPagination</h2>
     * <p>
     * Method for pagination
     * </p>
     *
     * @param categoryListView
     * @param currentPage
     * @param recordsPerPage
     * @param resultSearch
     * @param categoryForm
     * @return void
     */
    private void getPagination(ModelAndView categoryListView, int currentPage, int recordsPerPage, boolean resultSearch,
            CategoryForm categoryForm) {

        List<CategoryDto> categoryList;

        if (resultSearch == false) {
            categoryList = this.categoryService.doGetCategoryList();
            categoryListView.addObject(messageSource.getMessage("M_SC_0011", null, null));
        } else {
            categoryList = this.categoryService.doSearchCategoryList(categoryForm);
        }

        int rows = categoryList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        List<CategoryDto> searchCategoryList = this.categoryService.doSearchCategoryWithPagi(currentPage,
                recordsPerPage, categoryForm);
        categoryListView.addObject("noOfPages", nOfPages);
        categoryListView.addObject("currentPage", currentPage);
        categoryListView.addObject("recordsPerPage", recordsPerPage);
        categoryListView.addObject("CategoryList", searchCategoryList);
    }

    /**
     * <h2>getCurrentPage</h2>
     * <p>
     * To get current page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getCurrentPage(HttpServletRequest request) {
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        return currentPage;
    }

    /**
     * <h2>getRecordsPerPage</h2>
     * <p>
     * To get records per page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getRecordsPerPage(HttpServletRequest request) {
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 3;
        return recordsPerPage;
    }
}
