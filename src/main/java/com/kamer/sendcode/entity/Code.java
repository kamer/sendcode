package com.kamer.sendcode.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created on December, 2019
 *
 * @author kamer
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "code")
public class Code {

	@Id
	private String path;

	@Column(columnDefinition = "TEXT")
	private String content;

	private String language;

	@CreationTimestamp
	private LocalDateTime createDate;

}
