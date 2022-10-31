package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import app.entity.Rental;
import app.entity.Users;
import app.form.UserForm;
import app.service.RentalService;
import app.service.UserService;
import app.validate.EntryCheckGroup;
import app.validate.UpdateCheckGroup;

@Controller
@SessionAttributes(value = { "typeParams", "memoryParams", "capacityParams" })
public class UserController {

	/*
	 * 利用者管理機能のcontrollerです。
	 */

	@Autowired
	UserService userService;
	
	@Autowired
	RentalService rentalService;

	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}
	
	@GetMapping("/user")
	public String userlist(Model model) {
		List<Users> userList = userService.findUserList();
		model.addAttribute("userList",userList);
		return "user_list";
	}
	
	
	//利用者検索
	@PostMapping("/user/{search}")
	public String user_search(@RequestParam("search") String value,
			Model model) {
		model.addAttribute("searchvalue",value);
		if(value.isBlank()) {
			return "redirect:/user";
		}
		List<Users> userList = userService.findList(value);
		model.addAttribute("userList",userList);
		return "user_list";
	}
	 
	
	//利用者詳細
	@GetMapping("/user/datail/{userId}")
	public String user_datail(@PathVariable("userId") Integer userId, Model model) {
		Users user = userService.findByUserId(userId);
		model.addAttribute("userdatail", user);
		List<Rental> rentalHistory = rentalService.findTop5ByUserIdIsOrderByRentalIdDesc(userId);
		model.addAttribute("rentalHistory",rentalHistory);
		return "user_datail";
	}
	
	//利用者情報登録
	@GetMapping("/user/entry")
	public String user_entry() {
		return "user_entry";
	}

	
	//利用者更新
	@GetMapping("/user/update/{userId}")
	public String user_update(@PathVariable("userId") Integer userId, Model model) {
		Users user = userService.findByUserId(userId);
		model.addAttribute("userForm", user);
		return "user_edit";
	}
	
	//利用者入力フォーム（登録）
	@PostMapping("/form/user/entry")
	public String entryUser(@Validated(EntryCheckGroup.class) UserForm userForm,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError());
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getFieldErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList", errorList);
			return "user_entry";
		}
		userService.save(userForm);
		return "redirect:/user";
	}

	//利用者入力フォーム（編集）
	@PostMapping("/form/user/edit")
	public String updateUser(@Validated(UpdateCheckGroup.class) UserForm userForm,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getFieldErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList", errorList);
			return "user_edit";
		}
		System.out.println(userForm.getRegistrationDate());
		userService.update(userForm);
		return "redirect:/user";
	}

	
	
	//利用者削除
	@GetMapping("/user/delete/{userId}")
	public String user_dalete(@PathVariable("userId") Integer userId, Model model) {
		Users user = userService.findByUserId(userId);
		model.addAttribute("userdatail", user);
		return "user_delete";
	}	
	
	//利用者削除確定
	@GetMapping("/user/delete/{userId}/flag-on")
	public String updateUserDeleteFlag(@PathVariable("userId") Integer userId) {
		userService.saveDeleteFlag(userId);
		return "redirect:/user";
	}
	










}