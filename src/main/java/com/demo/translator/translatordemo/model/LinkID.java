package com.demo.translator.translatordemo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LinkID implements Serializable{
	private Integer sentenceId;
	private Integer translationId;
}
