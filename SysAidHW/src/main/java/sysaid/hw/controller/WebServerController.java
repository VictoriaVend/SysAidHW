package sysaid.hw.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sysaid.hw.service.TitleService;

@RestController
public class WebServerController {
	@Autowired
	TitleService ts;

	@GetMapping("/crawl")
	public void addLinksByUrl(@RequestParam String url, @RequestParam Integer number) throws Exception {
		ts.addLinksByUrl(url, number);

	}

	
}
