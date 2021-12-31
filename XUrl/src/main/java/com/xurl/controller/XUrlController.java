package com.xurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping("/register/{longUrl}")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Register and get the short url for the given long url.")
	public String registerUrl(@PathVariable("longUrl") String longUrl) {
		String shortUrl = xurl.registerNewUrl(longUrl);
		return shortUrl;
	}

	@PostMapping("/register/{longUrl}/{shortUrl}")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Replace the short url for the given long url with new short url.")
	public String replaceUrl(@PathVariable("longUrl") String longUrl, @PathVariable("shortUrl") String shortUrl) {
		String newShortUrl = xurl.registerNewUrl(longUrl, shortUrl);
		return newShortUrl;
	}

	@GetMapping("/getLongUrl/{shortUrl}")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Get the long url for the given short url.")
	public String getUrl(@PathVariable("shortUrl") String shortUrl) {
		String longUrl = xurl.registerNewUrl(shortUrl);
		return longUrl;
	}

	@PostMapping("/delete/{longUrl}")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Delete both short and long url for given long url.")
	public String deleteUrl(@PathVariable("longUrl") String longUrl) {
		String shortUrl = xurl.delete(longUrl);
		return shortUrl;
	}

	@GetMapping("/getCount/{longUrl}")
	@ApiImplicitParam(name = "USER", required = true, dataTypeClass = String.class, paramType = "header")
	@ApiOperation("Get the hit count for given long url.")
	public Integer getCount(@PathVariable("longUrl") String longUrl) {
		Integer count = xurl.getHitCount(longUrl);
		return count;
	}
	
}
