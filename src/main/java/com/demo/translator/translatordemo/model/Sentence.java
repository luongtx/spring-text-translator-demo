package com.demo.translator.translatordemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sentences")
public class Sentence {
	@Id
	private Integer id;
	@Column(columnDefinition = "VARCHAR(5)")
	private String lang;
	@Column(columnDefinition = "TEXT")
	private String text;
}
