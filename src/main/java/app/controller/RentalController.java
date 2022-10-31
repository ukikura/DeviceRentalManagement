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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.entity.Devices;
import app.entity.Rental;
import app.form.RentalForm;
import app.service.DeviceService;
import app.service.RentalService;
import app.validate.EntryCheckGroup;

@Controller
@SessionAttributes(value = { "typeParams", "memoryParams", "capacityParams" })
public class RentalController {

	/*
	 * 貸出管理機能のcontrollerです。
	 */

	@Autowired
	RentalService rentalService;

	@Autowired
	DeviceService deviceService;

	@ModelAttribute
	public RentalForm setUpRentalForm() {
		return new RentalForm();
	}

	//貸出中一覧
	@GetMapping("/rental")
	public String rentallist(Model model) {
		int[] countDevices = new int[3];
		for(int i=1; i<=3; i++) {
			countDevices[i-1] = deviceService.countAvailableDevices(i);
		}
		model.addAttribute("count", countDevices);
		List<Rental> rentalList = rentalService.findRentalList();
		model.addAttribute("rentalList",rentalList);
		return "rental_list";
	}
	
	//貸出検索
	@PostMapping("/rental/search")
	public String rental_search(@RequestParam("search") String searchStr, Model model) {
		int[] countDevices = new int[3];
		for(int i=1; i<=3; i++) {
			countDevices[i-1] = deviceService.countAvailableDevices(i);
		}
		model.addAttribute("count", countDevices);
		model.addAttribute("searchvalue",searchStr);
		if(searchStr.isBlank()) {
			return "redirect:/rental";
		}
		List<Rental> rentalList = rentalService.findList(searchStr);
		model.addAttribute("rentalList",rentalList);
		return "rental_list";
	}
	 
	
	//貸出履歴
	@GetMapping("/rental/history")
	public String rentalhistory(Model model) {
		List<Rental> rentalHistory = rentalService.findRentalHistory();
		model.addAttribute("rentalHistory",rentalHistory);
		return "rental_history";
	}
	
	//履歴検索
	@PostMapping("/rental/history/search")
	public String history_search(@RequestParam("search") String searchStr, Model model) {
		model.addAttribute("searchvalue",searchStr);
		if(searchStr.isBlank()) {
			return "redirect:/rental/history";
		}
		List<Rental> rentalHistory = rentalService.findRentalHistory(searchStr);
		model.addAttribute("rentalHistory",rentalHistory);
		return "rental_history";
	}
	
	//貸出詳細
	@GetMapping("/rental/datail/{rentalId}")
	public String rental_datail(@PathVariable("rentalId") Integer rentalId, Model model) {
		Rental rental = rentalService.findByRentalId(rentalId);
		model.addAttribute("rentaldatail", rental);
		return "rental_datail";
	}
	
	
	//利用者履歴
	@GetMapping("/rental/history/{staffId}")
	public String history_user(@PathVariable("staffId") String staffId, Model model) {
		model.addAttribute("searchvalue",staffId);
		List<Rental> rentalHistory = rentalService.findUserHistory(staffId);
		model.addAttribute("rentalHistory",rentalHistory);
		return "rental_history";
	}
	
	
	//貸出申請
	@GetMapping("/rental/entry")
	public String rental_entry(Model model) {
		List<Devices> deviceList = deviceService.findAvailableDevice(0);
		List<String> deviceNameList= new ArrayList<>();
		for(int i=0; i<deviceList.size(); ++i) {
			deviceNameList.add(deviceList.get(i).getDeviceName());
		}
		model.addAttribute("deviceNameList", deviceNameList);
		return "rental_entry";
	}
	
	//貸出可能機器取得
	@GetMapping("/getDeviceNameList")
	@ResponseBody
	public String getDeviceNameList(@RequestParam("type") int type, Model model) {
		List<Devices> deviceList = deviceService.findAvailableDevice(type);
		List<String> deviceNameList= new ArrayList<>();
		for(int i=0; i<deviceList.size(); ++i) {
			deviceNameList.add(deviceList.get(i).getDeviceName());
		}
		model.addAttribute("deviceNameList", deviceNameList);
		return  getJson(deviceNameList);
	}
	
    /**
     * 引数のUserDataオブジェクトをJSON文字列に変換する
     * @param deviceNameList deviceNameのリスト
     * @return 変換後JSON文字列
     */
	private String getJson(List<String> deviceNameList) {
		String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        	json = objectMapper.writeValueAsString(deviceNameList);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return json;	}

	//返却
	@GetMapping("/rental/return/{rentalId}")
	public String rental_return(@PathVariable("rentalId") Integer deviceId, Model model) {
		Rental rental = rentalService.findByRentalId(deviceId);
		model.addAttribute("rentalForm", rental);
		return "rental_return";
	}
	
	//貸出フォーム
	@PostMapping("/form/rental/entry")
	public String entryRental(@Validated(EntryCheckGroup.class) RentalForm rentalForm,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getFieldErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("errorList", errorList);
			return "rental_entry";
		}
		rentalService.save(rentalForm);
		return "redirect:/rental";
	}

	//返却フォーム
	@PostMapping("/form/rental/return")
	public String updateRental(RentalForm rentalForm,
			Model model) {
		
		rentalService.update(rentalForm);
		deviceService.update(rentalForm.getDeviceId(), rentalForm.getDeviceRemarks(), rentalForm.isBrokenFlag());
		
		return "redirect:/rental";
	}

	










}