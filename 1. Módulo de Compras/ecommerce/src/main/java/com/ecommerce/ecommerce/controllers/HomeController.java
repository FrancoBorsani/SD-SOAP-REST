package com.ecommerce.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.ecommerce.helpers.ViewRouteHelpers;

@RequestMapping("/")
@Controller
public class HomeController {
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
		
		return mAV;
	}
}