package com.codewithdurgesh.blog.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createdCat = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto updatedCat = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId){
		CategoryDto cat = this.categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	@GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
    	List<CategoryDto> categories = this.categoryService.getallCategories();
    	return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
    }
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<>(Map.of("message","category deleted successfully"),HttpStatus.OK);
	}

}
