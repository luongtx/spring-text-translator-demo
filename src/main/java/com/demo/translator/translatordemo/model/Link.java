package com.demo.translator.translatordemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "links")
@IdClass(LinkID.class)
public class Link {
	@Id
	private Integer sentenceId;
	@Id
	private Integer translationId;
}
