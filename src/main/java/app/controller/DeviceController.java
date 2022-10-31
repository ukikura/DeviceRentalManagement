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
import org.springframework.web.bind.annotation.SessionAttributes;

import app.entity.Devices;
import app.form.DeviceForm;
import app.form.DeviceSearchForm;
import app.service.DeviceService;
import app.validate.EntryCheckGroup;
import app.validate.UpdateCheckGroup;

@Controller
@SessionAttributes(value = { "typeParams", "memoryParams", "capacityParams" })
public class DeviceController {
	
	/*
	 * 機器管理機能のcontrollerです。
	 */
	
	
	
	@Autowired
	DeviceService deviceService;
	
	@ModelAttribute
	public DeviceForm setUpDeviceForm() {
		return new DeviceForm();
	}
	@ModelAttribute
	public DeviceSearchForm setUpDeviceSearchForm() {
		return new DeviceSearchForm();
	}

	//一覧
	@GetMapping("/device")
	public String device_list(Model model) {
		List<Devices> deviceList = deviceService.findDeviceList();
		model.addAttribute("deviceList",deviceList);
		return "device_list";
	}
	
	//機器検索
	@PostMapping("/device/search")
	public String device_search(DeviceSearchForm deviceSearchForm,
			Model model) {
		model.addAttribute("deviceSearchForm",deviceSearchForm);
		
		List<Devices> deviceList = deviceService.findSearchResult(deviceSearchForm);
		model.addAttribute("deviceList",deviceList);
		return "device_list";
	}
	
	//機器詳細
	@GetMapping("/device/datail/{deviceId}")
	public String device_datail(@PathVariable("deviceId") Integer deviceId, Model model) {
		Devices device = deviceService.findByDeviceId(deviceId);
		model.addAttribute("devicedatail", device);
		return "device_datail";
	}
	
	//機器情報編集
	@GetMapping("/device/edit")
	public String device_edit() {
		return "device_edit";
	}
	
	//機器情報編集
	@GetMapping("/device/entry")
	public String device_entry() {
		return "device_entry";
	}
	
	//機器更新
	@GetMapping("/device/update/{deviceId}")
	public String device_update(@PathVariable("deviceId") Integer deviceId, Model model) {
		Devices device = deviceService.findByDeviceId(deviceId);
		model.addAttribute("deviceForm", device);
		return "device_edit";
	}	
	
	
	//機器入力フォーム（登録）
	@PostMapping("/form/device/entry")
	public String entryDevice(@Validated(EntryCheckGroup.class) DeviceForm deviceForm,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getFieldErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList", errorList);
			return "device_entry";
		}
		deviceService.save(deviceForm);
		return "redirect:/device";
	}

	//機器入力フォーム（編集）
	@PostMapping("/form/device/edit")
	public String updateDevice(@Validated(UpdateCheckGroup.class) DeviceForm deviceForm,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getFieldErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList", errorList);
			return "device_edit";
		}
		deviceService.update(deviceForm, deviceForm.getDeviceId());
		return "redirect:/device";
	}
	
	//機器削除
	@GetMapping("/device/delete/{deviceId}")
	public String device_dalete(@PathVariable("deviceId") Integer deviceId, Model model) {
		Devices device = deviceService.findByDeviceId(deviceId);
		model.addAttribute("devicedatail", device);
		return "device_delete";
	}
	
	//機器削除確定
	@GetMapping("/device/delete/{deviceId}/flag-on")
	public String updateDeviceDeleteFlag(@PathVariable("deviceId") Integer deviceId) {
		deviceService.saveDeleteFlag(deviceId);
		return "redirect:/device";
	}
	
}