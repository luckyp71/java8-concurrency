package com.example.java8_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example1 {

	public static void main(String[] args) {

		ExecutorService ex = Executors.newWorkStealingPool();

		// Example 1 (ExecutorSerivce and Callable):
		List<Callable<Elements>> callables = Arrays.asList(() -> getUrls("https://www.oreilly.com"),
				() -> getUrls("https://o7planning.org"), () -> getUrls("https://deeplearning4j.org"),
				() -> getUrls("https://neo4j.com"), () -> getUrls("https://httpd.apache.org"),
				() -> getUrls("https://www.google.com"));
		try {
			ex.invokeAll(callables).stream().map(future -> {
				Elements result = null;
				try {
					result = future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				return result;
			}).forEach(elements -> {
				for(Element e: elements) {
					System.out.println(e.attr("href"));
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Example 1 (ExecutorService and Callable): Define getUrls method to fetch listed urls on target
	public static Elements getUrls(String url) {
		Elements urls = null;
		try {
			Document doc = Jsoup.connect(url).get();
			urls = doc.select("a[href]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urls;
	}
}
