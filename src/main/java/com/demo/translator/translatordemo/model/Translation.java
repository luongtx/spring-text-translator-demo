package com.demo.translator.translatordemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "translation")
public class Translation {

	@Id
	private String id;
	private String text;
	private String audioUrl;
	private String translationId;
	private String translateText;
}
