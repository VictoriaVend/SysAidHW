package sysaid.hw.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sysaid.hw.model.Title;

@RestController
public class WebServerController {
	@PersistenceContext
	EntityManager em;

	@GetMapping("/crawl")
	@Transactional
	public void addLinksByUrl(@RequestParam String url, @RequestParam int number) throws Exception {
		addLinks(url, number);

	}

	private void addLinks(String url, int number) throws Exception {
		ArrayList<String> list = new ArrayList<>();
		getResponsByUrl(url).getElementsByTag("a").forEach(a -> {
			if (a.attr("href").startsWith("http") && (list.size() < number)) {
				list.add(a.attr("href"));
			}
		});

		for (int i = 0; i < list.size(); i++) {
			String link = list.get(i);
			String title = getResponsByUrl(link).title();
			System.err.println(link + " -> " + title);
			addTitle(link, title);

		}

	}

	private Document getResponsByUrl(String url) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET, new URI(url));
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		return Jsoup.parse(response.getBody());

	}


	private void addTitle(String url, String title) {
		Title titlePage = new Title(url, title);
		em.persist(titlePage);
		em.close();

	}
}
