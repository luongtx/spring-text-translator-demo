package com.demo.translator.translatordemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sentence_audio")
public class SentenceAudio {
	@Id
	private String sentenceId;	
	private String username;
	private String license;
	private String attributionUrl;
}

