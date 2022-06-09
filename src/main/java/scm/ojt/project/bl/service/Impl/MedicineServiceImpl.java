package scm.ojt.project.bl.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import scm.ojt.project.bl.dto.MedicineDto;
import scm.ojt.project.bl.service.MedicineService;
import scm.ojt.project.excel.ExcelImport;
import scm.ojt.project.persistence.dao.MedicineDao;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.MedicineForm;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	private MedicineDao medicineDao;

	/**
	 * <h2>doGetMedicineList</h2>
	 * <p>
	 * Method to get list
	 * </p>
	 *
	 * @return
	 * @return List<MedicineDto>
	 */
	@SuppressWarnings("resource")
	@Override
	public List<MedicineDto> doGetMedicineList() throws IOException {
		List<Medicine> medicineForm = (List<Medicine>) this.medicineDao.doGetMedicineList();
		List<MedicineDto> listMedicineDTO = new ArrayList<>();
		for (Medicine medicine : medicineForm) {
			MedicineDto entity2Dto = new MedicineDto(medicine);

			if (entity2Dto.getImage() != null) {
				String medicineImagePath = entity2Dto.getImage();
				File medicineImage = new File(medicineImagePath);
				entity2Dto.setImage(null);
				if (medicineImage.exists()) {
					FileInputStream fis = new FileInputStream(medicineImage);
					byte byteArray[] = new byte[(int) medicineImage.length()];
					fis.read(byteArray);
					String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
					entity2Dto.setImage(imageString);

				}
			}
			listMedicineDTO.add(entity2Dto);
		}
		return listMedicineDTO;
	}

	/**
	 * <h2>getMedicineById</h2>
	 * <p>
	 * method to get medicine by id
	 * </p>
	 *
	 * @param medicineId
	 * @return
	 * @throws IOException
	 * @return MedicineForm
	 */
	@Override
	@SuppressWarnings("resource")
	public MedicineForm getMedicineById(int medicineId) throws IOException {
		Medicine resultMedicine = this.medicineDao.getMedicineById(medicineId);
		if (resultMedicine == null || resultMedicine.equals(null)) {
			throw new NullPointerException();
		}
		MedicineForm resultMedicineForm = new MedicineForm(resultMedicine);
		if (resultMedicineForm.getImage() != null) {
			String medicineImagePath = resultMedicineForm.getImage();
			File medicineImage = new File(medicineImagePath);
			resultMedicineForm.setImage(null);
			if (medicineImage.exists()) {
				FileInputStream fis = new FileInputStream(medicineImage);
				byte byteArray[] = new byte[(int) medicineImage.length()];
				fis.read(byteArray);
				String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
				resultMedicineForm.setImage(imageString);
			}
		}
		return resultMedicineForm;
	}

	/**
	 * <h2>addMedicine</h2>
	 * <p>
	 * method to add medicine
	 * </p>
	 *
	 * @param medicineForm
	 * @param medicineImagePath
	 * @throws IOException
	 * @return void
	 */
	@Override
	public void addMedicine(MedicineForm medicineForm, String medicineImagePath) throws IOException {
		String imageBase64 = medicineForm.getImage();
		if (!imageBase64.isEmpty() && !imageBase64.equals("") && !imageBase64.equals(null)) {
			String[] block = imageBase64.split(",");
			String realData = block[1];
			byte[] data = Base64.decodeBase64(realData);
			try (FileOutputStream stream = new FileOutputStream(medicineImagePath)) {
				stream.write(data);
			}
			medicineForm.setImage(medicineImagePath);
		}

		Date currentDate = new Date();
		Medicine medicine = new Medicine(medicineForm);
		this.medicineDao.addMedicine(medicine, currentDate);

	}

	/**
	 * <h2>deleteMedicine</h2>
	 * <p>
	 * Method to delete medicine
	 * </p>
	 *
	 * @param medicineId
	 * @return void
	 */
	@Override
	public void deleteMedicine(Integer medicineId) {
		medicineDao.deleteMedicine(medicineId);
	}

	/**
	 * <h2>updateMedicine</h2>
	 * <p>
	 * method to update medicine
	 * </p>
	 *
	 * @param medicineForm
	 * @param userProfilePath
	 * @throws IOException
	 * @return void
	 */
	@Override
	public void updateMedicine(MedicineForm medicineForm, String medicineImagePath) throws IOException {
		Medicine medicine = new Medicine(medicineForm);
		Date currentDate = new Date();
		String updateImagePath = medicineForm.getImage();
		if (medicineImagePath.length() > 0) {
			if (updateImagePath.length() > 0 && !updateImagePath.equals(medicineImagePath)) {
				System.out.println(medicineImagePath);
				File deletedOldImage = new File(medicineImagePath);
				deletedOldImage.delete();
				String imageBase64 = medicineForm.getImage();
				String[] block = imageBase64.split(",");
				String realData = block[1];
				byte[] data = Base64.decodeBase64(realData);
				try (FileOutputStream stream = new FileOutputStream(medicineImagePath)) {
					stream.write(data);
				}
			}
		} else {
			medicineImagePath = updateImagePath;
		}
		medicine.setImage(medicineImagePath);
		/*
		 * Medicine medicine = new Medicine();
		 * medicine.setMedicine_id(medicine.getMedicine_id());
		 */
		Medicine updatedMedicine = this.medicineDao.getMedicineById(medicineForm.getId());
		if (updatedMedicine != null) {
			updatedMedicine.setMedicine_code(medicine.getMedicine_code());
			updatedMedicine.setMedicine_name(medicine.getMedicine_name());
			updatedMedicine.setMedicine_description(medicine.getMedicine_description());
			updatedMedicine.setCategory(medicine.getCategory());
			updatedMedicine.setUnit_in_stock(medicine.getUnit_in_stock());
			updatedMedicine.setAmount(medicine.getAmount());
			updatedMedicine.setImage(medicine.getImage());
		}
		this.medicineDao.updateMedicine(updatedMedicine, currentDate);
	}

	/**
	 * <h2>doSearchMedicineWithPagi</h2>
	 * <p>
	 * method to search medicine with pagination
	 * </p>
	 *
	 * @param currentPage
	 * @param recordsPerPage
	 * @param medicineForm
	 * @return
	 * @return List<MedicineDto>
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	@Override
	public List<MedicineDto> doSearchMedicineWithPagi(int currentPage, int recordsPerPage, MedicineForm medicineForm)
	        throws IOException {
		List<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
		List<Medicine> medicineList = this.medicineDao.dbGetMedicineListPagi(currentPage, recordsPerPage, medicineForm);
		for (Medicine medicine : medicineList) {
			MedicineDto medicineDto = new MedicineDto(medicine);

			if (medicineDto.getImage() != null) {
				String medicineImagePath = medicineDto.getImage();
				File medicineImage = new File(medicineImagePath);
				medicineDto.setImage(null);
				if (medicineImage.exists()) {
					FileInputStream fis = new FileInputStream(medicineImage);
					byte byteArray[] = new byte[(int) medicineImage.length()];
					fis.read(byteArray);
					String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
					medicineDto.setImage(imageString);
				}
			}
			medicineDtoList.add(medicineDto);
		}
		return medicineDtoList;
	}

	/**
	 * <h2>doSearchMedicineList</h2>
	 * <p>
	 * method to search medicine
	 * </p>
	 *
	 * @param medicineForm
	 * @return
	 * @return List<MedicineDto>
	 */
	@Override
	public List<MedicineDto> doSearchMedicineList(MedicineForm medicineForm) {

		List<Medicine> medicineList = (List<Medicine>) this.medicineDao.dbSearchMedicineList(medicineForm);
		List<MedicineDto> medicineDtoList = new ArrayList<>();
		for (Medicine medicine : medicineList) {
			MedicineDto Entity2Dto = new MedicineDto(medicine);
			medicineDtoList.add(Entity2Dto);
		}
		return medicineDtoList;
	}

	/**
	 * <h2>load</h2>
	 * <p>
	 * method to load data from database
	 * </p>
	 * 
	 * @return ByteArrayInputStream
	 */
	@Override
	public ByteArrayInputStream load() {
		List<Medicine> theMedicines = medicineDao.doGetMedicineList();
		ByteArrayInputStream in = ExcelImport.tutorialsToExcel(theMedicines);
		return in;

	}

	/**
	 * <h2>save</h2>
	 * <p>
	 * Override method to save excel data to database
	 * </p>
	 *
	 * @param file
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 * @throws IOException 
	 */
	@Override
	public String save(MultipartFile file) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String errorExcelMessage = ValidationExcel(file);
		if (errorExcelMessage != null) {
			return errorExcelMessage;
		}
		try {
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Medicine> theMedicines = new ArrayList<Medicine>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Medicine m = new Medicine();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						m.setMedicine_code(currentCell.getStringCellValue());
						break;
					case 1:
						m.setMedicine_name(currentCell.getStringCellValue());
						break;
					case 2:
						m.setMedicine_description(currentCell.getStringCellValue());
						break;
					case 3:
						Category c = new Category();

						c.setCategory_id((int) currentCell.getNumericCellValue());
						m.setCategory(c);
						// m.category.setCategory_id((int)currentCell.getNumericCellValue());
						// m.setCategory(m.category.getCategory_id(currentCell.getNumericCellValue()));
						break;
					case 4:
						m.setAmount(currentCell.getNumericCellValue());
						break;
					case 5:
						m.setUnit_in_stock((int) currentCell.getNumericCellValue());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				theMedicines.add(m);
			}
			workbook.close();
			for (Medicine MedicineData : theMedicines) {
				this.medicineDao.addMedicine(MedicineData, new Date());
			}
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
		return null;
	}

	/**
	 * <h2>isMedicineCodeExist</h2>
	 * <p>
	 * method to check medicine code exists or not
	 * </p>
	 *
	 * @param medCode
	 * @return
	 * @return boolean
	 */
	@Override
	public boolean isMedicineCodeExist(String medCode) {
		Medicine resultMedicine = this.medicineDao.isMedicineCodeExist(medCode);
		boolean medicineCodeExist = false;
		if (resultMedicine != null) {
			medicineCodeExist = true;
		}

		return medicineCodeExist;
	}

	@Override
	public void doUpdateMedicine(Medicine medicine) {
		this.medicineDao.dbUpdateMedicine(medicine);
	}

	/**
	 * <h2>isMedicineNameExist</h2>
	 * <p>
	 * method to check medicine name exists or not
	 * </p>
	 * 
	 * @param medName
	 * @return
	 */
	@Override
	public boolean isMedicineNameExist(String medName) {
		Medicine resultMedicine = this.medicineDao.isMedicineNameExist(medName);
		boolean medicineNameExist = false;
		if (resultMedicine != null) {
			medicineNameExist = true;
		}

		return medicineNameExist;
	}

	/**
	 * <h2>isUpdateMedicineCodeExist</h2>
	 * <p>
	 * method to check update medicine code exists or not
	 * </p>
	 * 
	 * @param medicineCode
	 * @param medicineId
	 * @return
	 */
	@Override
	public boolean isUpdateMedicineCodeExist(String medicineCode, int medicineId) {
		boolean updateMedicineCodeExist = false;
		List<Medicine> medicineList = medicineDao.dbUpdatedMedicineExistList(medicineCode);
		Medicine medicineById = medicineDao.getMedicineById(medicineId);
		if (medicineList != null) {
			for (Medicine medicine : medicineList) {
				if (medicineById != null) {
					if (medicine.getMedicine_code() != medicineById.getMedicine_code()) {
						updateMedicineCodeExist = true;
					}
				}
			}
		}
		return updateMedicineCodeExist;
	}

	/**
	 * <h2>isUpdateMedicineNameExist</h2>
	 * <p>
	 * method to check update medicine name exists or not
	 * </p>
	 * 
	 * @param medicine_name
	 * @param id
	 * @return
	 */
	@Override
	public boolean isUpdateMedicineNameExist(String medicineName, Integer medicineId) {
		boolean updateMedicineNameExist = false;
		List<Medicine> medicineList = medicineDao.dbUpdatedMedicineNameExistList(medicineName);
		Medicine medicineById = medicineDao.getMedicineById(medicineId);
		if (medicineList != null) {
			for (Medicine medicine : medicineList) {
				if (medicineById != null) {
					if (medicine.getMedicine_name() != medicineById.getMedicine_name()) {
						updateMedicineNameExist = true;
					}
				}
			}
		}
		return updateMedicineNameExist;
	}

	/**
	 * <h2>ValidationExcel</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @return String
	 */
	@SuppressWarnings("resource")
	private String ValidationExcel(MultipartFile file) throws IOException {
		// check file
		if (file.isEmpty()) {
			return "No file is selected!";
		}
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		// check extension
		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			return "File extension Wrong!";
		}
		if (sheet.getFirstRowNum() == 0) {
			return "No Data Not Found";
		}
		// check cell
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					return "Wrong Data Entries!";
				}
			}
		}
		// check cell data
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// Code
			Cell cellCode = row.getCell(0);
			if (cellCode.getCellType() != Cell.CELL_TYPE_STRING) {
				return "Wrong data type in this column one";
			}
			// Name
			Cell cellName = row.getCell(1);
			if (cellName.getCellType() != Cell.CELL_TYPE_STRING) {
				return "Wrong data type in this column two";
			}
			// Description
			Cell cellDescription = row.getCell(2);
			if (cellDescription.getCellType() != Cell.CELL_TYPE_STRING) {
				return "Wrong data type in this column three";
			}
			// Category
			Cell cellCategory = row.getCell(3);
			if (cellCategory.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				return "Wrong data type in this column four";
			}
			// Price
			Cell cellPrice = row.getCell(4);
			if (cellPrice.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				return "Wrong data type in this column five";
			}
			// Unit in stock
			Cell cellUnit = row.getCell(4);
			if (cellUnit.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				return "Wrong data type in this column sive";
			}
		}
		// check file has no data
		if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
			return "No data in the file";
		}
		return null;
	}
}