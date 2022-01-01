package com.xurl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.xurl.service.XUrl;
import com.xurl.service.XUrlImpl;

class XUrlTests {

	@Test
	void test() {
		XUrl xUrl = new XUrlImpl();
		String url = xUrl.registerNewUrl("http://abc.com");
		assertNotNull(url);
		String url1 = xUrl.registerNewUrl("http://abc1.com");
		assertNotNull(url1);
		String url2 = xUrl.registerNewUrl("http://abc2.com");
		assertNotNull(url2);
		String url3 = xUrl.registerNewUrl("http://abc3.com");
		assertNotNull(url3);
		String url4 = xUrl.registerNewUrl("http://abc2.com");  // url4 should be the same as url2
		assertNotNull(url4);

		// Update new URL mapping to a custom short URL
		String url5 = xUrl.registerNewUrl("http://abc5.com", "http://short.url/test1");
		String url6 = xUrl.registerNewUrl("http://abc6.com", "http://short.url/test2");
		// Try to update new URL to map to existing short URL, should return null
		String urlNull = xUrl.registerNewUrl("http://abc7.com", url3);
		assertNull(urlNull);

		// Test out longURL lookup based on the shortURL input
		assertEquals(xUrl.getUrl(url),("http://abc.com"));
		assertEquals(xUrl.getUrl(url2),(xUrl.getUrl(url4)));
		assertEquals(xUrl.getUrl(url5),("http://abc5.com"));

		// Test out getHitCount() for a given long URL. 
		// Here the same long URL has been looked up 2 times as part of url2 & url4
		assertEquals(xUrl.getHitCount("http://abc2.com"),(2));
		// Try to fetch hit count for a non existent long URL, should return 0 
		assertEquals(xUrl.getHitCount("http://abcn.com"),(0));

		// From the short URL url1, remove the common section (http://short.url/) and remove any non alphanumeric character
		String choppedUrl = url1.replace("http://short.url/", "").replaceAll("[^A-Za-z0-9]", "");
		// The result should have only alphanumeric characters and be 9 characters long
		assertEquals(choppedUrl.length(), 9);

		// Delete mapping for the long URL and confirm that the short URL lookup for that long URL returns null
		xUrl.delete("http://abc6.com");
		assertEquals(xUrl.getUrl(url6), null);

	}

}
