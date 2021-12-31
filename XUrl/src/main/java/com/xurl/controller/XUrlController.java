package com.xurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xurl.service.XUrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
@Api(tags = "XUrl")
public class XUrlController {

	@Autowired
	private XUrl xurl;

	@PostMapping("/register")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Get a short url for a long url.")
	public String viewHomePage(@PathVariable("longUrl") String longUrl) {
		String shortUrl = xurl.registerNewUrl(longUrl);
		return shortUrl;
	}
}
