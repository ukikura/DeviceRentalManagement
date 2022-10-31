package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import app.form.DeviceForm;
import app.service.DeviceService;

@Controller
@SessionAttributes(value = { "typeParams", "memoryParams", "capacityParams" })
public class CommonController {
	
	/*
	 * 機器管理機能のcontrollerです。
	 */
	
	@Autowired
	DeviceService deviceService;
	
	@ModelAttribute
	public DeviceForm setUpDeviceForm() {
		return new DeviceForm();
	}

	@ModelAttribute( value = "typeParams" )
	public String[] setUpTypeParams() {
		String typeParams[] = {"","PC","マウス","モニター"};
		return typeParams;
	}

	@ModelAttribute( value = "memoryParams" )
	public String[] setUpMemoryParams() {
		String memoryParams[] = {"","8GB","16GB","32GB"};
		return memoryParams;
	}

	@ModelAttribute( value = "capacityParams" )
	public String[] setUpCapacityParams() {
		String capacityParams[] = {"","500GB","1TB"};
		return capacityParams;
	}

	//共通パーツ
	@RequestMapping("/common")
	public String common() {
		return "common";
	}

	//トップページ：機器情報
	@RequestMapping("/")
	public String toppage() {
		return "redirect:/rental";
	}
	
}