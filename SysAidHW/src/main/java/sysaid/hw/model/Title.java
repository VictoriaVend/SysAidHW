package sysaid.hw.model;

import java.io.Serializable;
import java.time.LocalDateTime;


import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "time" })
@Document(collection = "title")
public class Title implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "dd-MM-yyyy'T'hh:mm:ss")
	LocalDateTime time;
	String url;
	String title;
	public Title(String url, String title) {
		time = LocalDateTime.now();
		this.url = url;
		this.title = title;
	}

}
