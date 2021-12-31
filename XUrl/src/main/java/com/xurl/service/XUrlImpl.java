package com.xurl.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class XUrlImpl implements XUrl {
    
	private HashMap<String, String> keyMap;
	private HashMap<String, String> valueMap;
	private HashMap<String, Integer> countMap;
	private String domain;
	private char[] characterArray;
	private Random random;
	private int keyLength;

	XUrlImpl() {
		keyMap = new HashMap<String, String>();
        valueMap = new HashMap<String, String>();
        countMap = new HashMap<String, Integer>();
		characterArray = new char[64];
		keyLength = 8;
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			characterArray     [i] = (char) j;
		}
		domain = "http://short.url";
		random = new Random();
	}

	@Override
	public String registerNewUrl(String longUrl) {
		String shortURL = "";
		if (valueMap.containsKey(longUrl)) {
			shortURL = valueMap.get(longUrl);
		} else {
			shortURL = getKey(longUrl);
		}
		return shortURL;
	}

	@Override
	public String registerNewUrl(String longUrl, String shortUrl) {
		if (keyMap.containsKey(shortUrl)) {
			return null;
		}
		if (valueMap.containsKey(shortUrl)) {
			return null;
		}
		keyMap.put(shortUrl, longUrl);
		valueMap.put(longUrl, shortUrl);
		return shortUrl;
	}

	@Override
	public String getUrl(String shortUrl) {
		if (keyMap.containsKey(shortUrl)) {
			String longUrl = keyMap.get(shortUrl);
			countMap.put(longUrl, countMap.getOrDefault(longUrl, 0)+1);
			return longUrl;
		}
		return null;
	}

	@Override
	public Integer getHitCount(String longUrl) {
		if (countMap.containsKey(longUrl)) {
			return countMap.get(longUrl);
		}
		return 0;
	}

	@Override
	public String delete(String longUrl) {
		if (valueMap.containsKey(longUrl)) {
			String shortUrl = valueMap.get(longUrl);
			valueMap.remove(longUrl);
			keyMap.remove(shortUrl);
		}
		return null;
	}

	private String getKey(String longURL) {
		String key;
		key = generateKey();
		keyMap.put(key, longURL);
		valueMap.put(longURL, key);
		return key;
	}

	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += characterArray[random.nextInt(62)];
			}
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return domain + "/" + key;
	}

}