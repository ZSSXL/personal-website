package com.zss.richtextformatdemo.service.impl;

import com.zss.richtextformatdemo.entity.RichText;
import com.zss.richtextformatdemo.repository.RichTextRepository;
import com.zss.richtextformatdemo.service.RichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2019/8/11 10:41
 * @description
 */
@Service
public class RichTextImpl implements RichService {

    private final RichTextRepository richTextRepository;

    @Autowired
    public RichTextImpl(RichTextRepository richTextRepository) {
        this.richTextRepository = richTextRepository;
    }

    @Override
    public RichText createRichText(RichText richText) {
        return richTextRepository.save(richText);
    }

    @Override
    public RichText getRichText(String richId) {
        return richTextRepository.findById(richId).orElse(null);
    }
}
