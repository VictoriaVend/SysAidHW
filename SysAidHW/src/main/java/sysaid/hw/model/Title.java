package sysaid.hw.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "time" })
@Entity
public class Title implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	LocalDateTime time;
	String url;
	String title;
	public Title(String url, String title) {
		time = LocalDateTime.now();
		this.url = url;
		this.title = title;
	}

}
