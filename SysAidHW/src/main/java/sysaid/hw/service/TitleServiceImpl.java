package sysaid.hw.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sysaid.hw.dao.TitleSysRepository;
import sysaid.hw.model.Title;

@Service
public class TitleServiceImpl implements TitleService{
	@Autowired
	TitleSysRepository tr;

	@Override
	public void addLinksByUrl(String url, Integer number) throws URISyntaxException {
		ArrayList<String> list = new ArrayList<>();
		getResponsByUrl(url).getElementsByTag("a").forEach(a -> {
			if (a.attr("href").startsWith("http") && (list.size() < number)) {
				list.add(a.attr("href"));
			}
		});

		for (int i = 0; i < list.size(); i++) {
			String link = list.get(i);
			String title = getResponsByUrl(link).title();

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
		tr.save(titlePage);
		
	}

}
