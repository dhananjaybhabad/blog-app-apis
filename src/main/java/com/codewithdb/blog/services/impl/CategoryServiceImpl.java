package com.codewithdb.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithdb.blog.entities.Category;
import com.codewithdb.blog.exceptions.ResourceNotFoundException;
import com.codewithdb.blog.payloads.CategoryDto;
import com.codewithdb.blog.repositories.CategoryRepo;
import com.codewithdb.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		LOGGER.info("inside CategoryServiceImpl:createCategory method taking data from frontend");
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category adddedCat = this.categoryRepo.save(cat);
		LOGGER.trace("inside CategoryServiceImpl: createCategory method returns data to frontend");
		LOGGER.error("createCategory() method executed Successfully");

		return this.modelMapper.map(adddedCat, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		LOGGER.info("inside CategoryServiceImpl: updateCategory method taking data from frontend");
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = this.categoryRepo.save(cat);
		LOGGER.info("inside CategoryServiceImpl: updateCategory method returns data to frontend");
		LOGGER.info("updateCategory() method executed Successfully");

		return this.modelMapper.map(updatedCat, CategoryDto.class);

	}

	@Override
	public void deleteCategory(Integer categoryId) {

		LOGGER.info("inside CategoryServiceImpl: updateCategory method taking data from frontend");
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		LOGGER.info("inside CategoryServiceImpl: deleteCategory method returns data to frontend");
		LOGGER.info("deleteCategory() method executed Successfully");

		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		LOGGER.info("inside CategoryServiceImpl: updateCategory method taking data from frontend");
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
		LOGGER.info("inside CategoryServiceImpl: getCategory method returns data to frontend");
		LOGGER.info("getCategory() method executed Successfully");

		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		LOGGER.info("inside CategoryServiceImpl: getCategories() method taking data from frontend");
		List<Category> categories = this.categoryRepo.findAll();

		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		LOGGER.info("inside CategoryServiceImpl: getCategories() method returns data to frontend");
		LOGGER.info("getCategories() method executed Successfully");

		return catDtos;
	}

}
