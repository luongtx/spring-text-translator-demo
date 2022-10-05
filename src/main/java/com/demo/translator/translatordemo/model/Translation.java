package com.demo.translator.translatordemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "translation")
public class Translation implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "text")
    private String text;
    @Column(name = "audio_url")
    private String audioUrl;
    @Column(name = "translation_id")
    private String translationId;
    @Column(name = "translate_text")
    private String translateText;
}
