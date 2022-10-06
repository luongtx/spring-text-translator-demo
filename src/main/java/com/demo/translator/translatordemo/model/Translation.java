package com.demo.translator.translatordemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "translation")
@IdClass(TranslationID.class)
public class Translation implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "text")
    private String text;
    @Column(name = "audio_url")
    private String audioUrl;
    @Id
    @Column(name = "translation_id")
    private String translationId;
    @Column(name = "translate_text")
    private String translateText;
}
