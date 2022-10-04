package com.demo.translator.translatordemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sentences")
public class Sentence {
	@Id
	private String id;	
	private String lang;
	private String text;
}
