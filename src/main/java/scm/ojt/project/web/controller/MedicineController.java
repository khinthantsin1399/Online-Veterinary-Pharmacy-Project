package scm.ojt.project.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.bl.dto.MedicineDto;
import scm.ojt.project.bl.service.CartService;
import scm.ojt.project.bl.service.CategoryService;
import scm.ojt.project.bl.service.MedicineService;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.CategoryForm;
import scm.ojt.project.web.form.MedicineForm;

/**
 * <h2>MedicineController Class</h2>
 * <p>
 * Process for Displaying MedicineController
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Controller
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CartService cartService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpSession session;

    /**
     * <h2>getMedicineList</h2>
     * <p>
     * Getting Medicine List
     * </p>
     *
     * @param request
     * @param medicineForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/medicineList", method = RequestMethod.GET)
    public ModelAndView getMedicineList(HttpServletRequest request, MedicineForm medicineForm) throws IOException {
        ModelAndView medicineListView = new ModelAndView("medicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        return medicineListView;
    }

    /**
     * <h2>getUserMedicineList</h2>
     * <p>
     * Getting User Medicine List
     * </p>
     *
     * @param request
     * @param medicineForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/userMedicineList", method = RequestMethod.GET)
    public ModelAndView getUserMedicineList(HttpServletRequest request, MedicineForm medicineForm) throws IOException {
        ModelAndView medicineListView = new ModelAndView("userMedicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        return medicineListView;
    }

    /**
     * <h2>detailMedicine</h2>
     * <p>
     * To get medicine detail
     * </p>
     *
     * @param medicineId
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/detailMedicine", method = RequestMethod.GET)
    public ModelAndView detailMedicine(@RequestParam("id") Integer medicineId, HttpServletRequest request) {
        ModelAndView medicineView = new ModelAndView("detailMedicine");
        MedicineForm detailMedicine = null;
        try {
            detailMedicine = this.medicineService.getMedicineById(medicineId);
        } catch (NullPointerException | IOException e) {
            medicineView = new ModelAndView("redirect:/medicineList");
        }

        medicineView.addObject("detailMedicine", detailMedicine);
        return medicineView;
    }

    /**
     * <h2>newMedicine</h2>
     * <p>
     * To get medicine add form
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createMedicine", method = RequestMethod.GET)
    public ModelAndView newMedicine(ModelAndView model) {
        Medicine medicine = new Medicine();
        ModelAndView createMedicine = new ModelAndView("createMedicine");
        List<CategoryDto> CategoryList = categoryService.doGetCategoryList();
        createMedicine.addObject("CategoryList", CategoryList);
        createMedicine.addObject("createMedicineForm", medicine);
        createMedicine.setViewName("createMedicine");
        return createMedicine;
    }

    /**
     * <h2>insertMedicine</h2>
     * <p>
     * To confirm medicine detail to add
     * </p>
     *
     * @param medicineForm
     * @param result
     * @param request
     * @param imageData
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/createMedicineConfirm", params = "confirmMedicine", method = RequestMethod.POST)
    public ModelAndView insertMedicine(@ModelAttribute("createMedicineForm") @Valid MedicineForm medicineForm,
            BindingResult result, HttpServletRequest request, @RequestParam("imageData") String imageData)
            throws ParseException {
        List<CategoryDto> CategoryList = categoryService.doGetCategoryList();

        ModelAndView ConfirmView = new ModelAndView("createMedicineConfirm");
        if (result.hasErrors()) {
            ConfirmView = new ModelAndView("createMedicine");
            // Medicine medicine = new Medicine();
            ConfirmView.addObject("CategoryList", CategoryList);
            ConfirmView.addObject("createMedicineForm", medicineForm);
            ConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0001", null, null));

        } else if (this.medicineService.isMedicineCodeExist(medicineForm.getMedicine_code())) {
            ConfirmView = new ModelAndView("createMedicine");
            // Medicine medicine = new Medicine();
            ConfirmView.addObject("CategoryList", CategoryList);
            ConfirmView.addObject("createMedicineForm", medicineForm);
            ConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0003", null, null));

        } else {
            ConfirmView.addObject("medicineForm", medicineForm);
            ConfirmView.setViewName("createMedicineConfirm");
        }
        CategoryForm categoryByid = this.categoryService.getCategoryById(medicineForm.getCategory().getCategory_id());

        medicineForm.getCategory().setCategory_name(categoryByid.getCategory_name());

        if (imageData.length() > 0) {
            medicineForm.setImage(imageData);
        }
        return ConfirmView;
    }

    /**
     * <h2>createMedicineConfirm</h2>
     * <p>
     * To add new medicine
     * </p>
     *
     * @param medicineForm
     * @param result
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ParseException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/insertMedicine", params = "addMedicine", method = RequestMethod.POST)
    public ModelAndView createMedicineConfirm(@ModelAttribute("medicineForm") @Valid MedicineForm medicineForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        String medicineImagePath = request.getRealPath("/") + "/resources/images/" + medicineForm.getMedicine_name();
        Path uploadPath = Paths.get(medicineImagePath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        medicineImagePath = uploadPath + "/" + medicineForm.getMedicine_name() + ".png";
        System.out.println(medicineImagePath);
        this.medicineService.addMedicine(medicineForm, medicineImagePath);
        ModelAndView createMedicineView = new ModelAndView("redirect:/medicineList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0005", null, null));
        return createMedicineView;
    }

    /**
     * <h2>cancelMedicineConfirm</h2>
     * <p>
     * Cancel to add new medicine
     * </p>
     *
     * @param medicineForm
     * @param result
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertMedicine", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelMedicineConfirm(@ModelAttribute("medicineForm") @Valid MedicineForm medicineForm,
            BindingResult result) throws ParseException {
        List<CategoryDto> CategoryList = categoryService.doGetCategoryList();

        ModelAndView createMedicineView = new ModelAndView("createMedicine");
        createMedicineView.addObject("CategoryList", CategoryList);
        createMedicineView.addObject("createMedicineForm", medicineForm);
        return createMedicineView;
    }

    /**
     * <h2>deleteMedicine</h2>
     * <p>
     * To delete medicine
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteMedicine", method = RequestMethod.GET)
    public ModelAndView deleteMedicine(HttpServletRequest request) {
        int medicineId = Integer.parseInt(request.getParameter("id"));
        medicineService.deleteMedicine(medicineId);
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0006", null, null));
        return new ModelAndView("redirect:/medicineList");
    }

    /**
     * <h2>editMedicine</h2>
     * <p>
     * To get update form
     * </p>
     *
     * @param medicineId
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateMedicine", method = RequestMethod.GET)
    public ModelAndView editMedicine(@RequestParam("id") Integer medicineId, HttpServletRequest request)
            throws IOException {
        MedicineForm medicine = this.medicineService.getMedicineById(medicineId);
        ModelAndView model = new ModelAndView("updateMedicine");
        List<CategoryDto> CategoryList = categoryService.doGetCategoryList();
        model.addObject("CategoryList", CategoryList);
        model.addObject("medicine", medicine);
        model.setViewName("updateMedicine");
        return model;
    }

    /**
     * <h2>callUpdateMedicineConfirm</h2>
     * <p>
     * To confirm medicine update
     * </p>
     *
     * @param updateMedicineForm
     * @param result
     * @param request
     * @param session
     * @param imageData
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateMedicineConfirm", method = RequestMethod.POST)
    public ModelAndView callUpdateMedicineConfirm(@ModelAttribute("medicine") @Valid MedicineForm updateMedicineForm,
            BindingResult result, HttpServletRequest request, HttpSession session,
            @RequestParam("imageData") String imageData) throws ParseException, IOException {
        ModelAndView updateConfirmView = new ModelAndView("updateMedicineConfirm");
        if (result.hasErrors()) {
            updateConfirmView = new ModelAndView("updateMedicine");
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0002", null, null));
            return updateConfirmView;
        }
        CategoryForm categoryByid = this.categoryService
                .getCategoryById(updateMedicineForm.getCategory().getCategory_id());
        MedicineForm medicineById = this.medicineService.getMedicineById(updateMedicineForm.getId());
        updateConfirmView = new ModelAndView("updateMedicineConfirm");
        updateMedicineForm.setImage(medicineById.getImage());
        updateMedicineForm.getCategory().setCategory_name(categoryByid.getCategory_name());
        if (imageData.length() > 0) {
            updateMedicineForm.setImage(imageData);
        }

        updateConfirmView.addObject("updateMedicineForm", updateMedicineForm);
        updateConfirmView.setViewName("updateMedicineConfirm");
        return updateConfirmView;
    }

    /**
     * <h2>updateMedicine</h2>
     * <p>
     * To update medicine
     * </p>
     *
     * @param medicineForm
     * @param result
     * @param session
     * @param request
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/editMedicine", params = "update", method = RequestMethod.POST)
    public ModelAndView updateMedicine(@ModelAttribute("updateMedicineForm") @Valid MedicineForm medicineForm,
            BindingResult result, HttpSession session, HttpServletRequest request) throws ParseException, IOException {

        Path path = Paths.get(request.getRealPath("/") + "/resources/images/" + medicineForm.getMedicine_name());
        String medicineImagePath = Files.createDirectories(path) + "/" + medicineForm.getMedicine_name() + ".png";
        this.medicineService.updateMedicine(medicineForm, medicineImagePath);
        ModelAndView updateUserView = new ModelAndView("redirect:/medicineList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0009", null, null));
        return updateUserView;
    }

    /**
     * <h2>cancelUpdateMedicine</h2>
     * <p>
     * To cancel medicine update
     * </p>
     *
     * @param medicineForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/editMedicine", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdateMedicine(@ModelAttribute("updateMedicineForm") @Valid MedicineForm medicineForm,
            BindingResult result) {
        List<CategoryDto> CategoryList = categoryService.doGetCategoryList();

        ModelAndView updateMedicineView = new ModelAndView("updateMedicine");
        updateMedicineView.addObject("CategoryList", CategoryList);
        updateMedicineView.addObject("medicine", medicineForm);
        return updateMedicineView;
    }

    /**
     * <h2>searchMedicine</h2>
     * <p>
     * To search medicine
     * </p>
     *
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchMedicine", method = RequestMethod.GET)
    public ModelAndView searchMedicine(HttpServletRequest request) throws IOException {

        MedicineForm medicineForm = new MedicineForm();
        String search_input = request.getParameter("search_input");
        ModelAndView medicineListView = new ModelAndView("medicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        if (search_input.isEmpty()) {
            this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        } else {
            medicineForm.setMedicine_name(search_input);

            this.getPagination(medicineListView, currentPage, recordsPerPage, true, medicineForm);
            medicineListView.addObject("searchData", search_input);
        }
        return medicineListView;
    }

    /**
     * <h2>searchMedicine</h2>
     * <p>
     * Searching medicine
     * </p>
     *
     * @param search_input String
     * @param request      HttpServletRequest
     * @return
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping(value = "/searchMedicine", params = "searchMedicine", method = RequestMethod.POST)
    public ModelAndView searchMedicine(@RequestParam("search-input") String search_input, HttpServletRequest request)
            throws IOException {

        MedicineForm medicineForm = new MedicineForm();
        ModelAndView medicineListView = new ModelAndView("medicineList");
        if (search_input.isEmpty()) {
            this.getPagination(medicineListView, 1, 6, false, medicineForm);
            medicineListView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        } else {
            medicineForm.setMedicine_name(search_input);
            this.getPagination(medicineListView, 1, 6, true, medicineForm);
            medicineListView.addObject("searchData", search_input);
        }
        return medicineListView;
    }

    /**
     * <h2>searchMedicine</h2>
     * <p>
     * Searching medicine
     * </p>
     *
     * @param search_input String
     * @param request      HttpServletRequest
     * @return
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping(value = "/searchMedicineUser", params = "searchMedicine", method = RequestMethod.POST)
    public ModelAndView searchMedicineUser(@RequestParam("search-input") String search_input,
            HttpServletRequest request) throws IOException {

        MedicineForm medicineForm = new MedicineForm();
        ModelAndView medicineListView = new ModelAndView("userMedicineList");
        if (search_input.isEmpty()) {
            this.getPagination(medicineListView, 1, 6, false, medicineForm);
            medicineListView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        } else {
            medicineForm.setMedicine_name(search_input);
            this.getPagination(medicineListView, 1, 6, true, medicineForm);
            medicineListView.addObject("searchData", search_input);
        }
        return medicineListView;
    }

    /**
     * <h2>getPagination</h2>
     * <p>
     * To get Pagination
     * </p>
     *
     * @param medicineListView
     * @param currentPage
     * @param recordsPerPage
     * @param resultSearch
     * @param medicineForm
     * @throws IOException
     * @return void
     */
    private void getPagination(ModelAndView medicineListView, int currentPage, int recordsPerPage, boolean resultSearch,
            MedicineForm medicineForm) throws IOException {
        List<MedicineDto> medicineList;
        if (resultSearch == false) {
            medicineList = this.medicineService.doGetMedicineList();
        } else {
            medicineList = this.medicineService.doSearchMedicineList(medicineForm);
        }

        int rows = medicineList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        List<MedicineDto> searchMedicineList = this.medicineService.doSearchMedicineWithPagi(currentPage,
                recordsPerPage, medicineForm);
        int loginUserId = (int) session.getAttribute("loginUserId");
        boolean isCart = this.cartService.isCreatedUserIdExist(loginUserId);
        
        medicineListView.addObject("noOfPages", nOfPages);
        medicineListView.addObject("currentPage", currentPage);
        medicineListView.addObject("recordsPerPage", recordsPerPage);
        medicineListView.addObject("MedicineList", searchMedicineList);
        medicineListView.addObject("isCart", isCart);
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
                : 6;
        return recordsPerPage;
    }

    /**
     * <h2>getFile</h2>
     * <p>
     * Method to download data from database as excel file
     * </p>
     * 
     * @return
     * @return ResponseEntity<InputStreamResource>
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getFile() {
        String filename = "medicine.xlsx";
        InputStreamResource file = new InputStreamResource(medicineService.load());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);

    }

    /**
     * <h2> getUploadMedicineList</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @param medicineForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.GET)
    public ModelAndView getUploadMedicineList(HttpServletRequest request, MedicineForm medicineForm)
            throws IOException {
        ModelAndView medicineListView = new ModelAndView("medicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        return medicineListView;
    }

    /**
     * <h2>uploadExcel</h2>
     * <p>
     * Method to upload excel file and save to database
     * </p>
     * 
     * @param file
     * @param request
     * @return
     * @return ModelAndView
     * @throws InvalidFormatException
     * @throws EncryptedDocumentException
     * @throws IOException
     */
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public ModelAndView uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        ModelAndView uploadView = new ModelAndView("medicineList");
        MedicineForm medicineForm = new MedicineForm();
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        this.getPagination(uploadView, currentPage, recordsPerPage, false, medicineForm);

        if (file.getSize() == 0) {
            uploadView.addObject("uploadErrorMsg", messageSource.getMessage("M_SC_0010", null, null));
            return uploadView;
        }
        this.medicineService.save(file);
        // uploadView = new ModelAndView("redirect:/medicineList");
        session.setAttribute("completeMsg", messageSource.getMessage("M_SC_0017", null, null));
        return uploadView;
    }
}