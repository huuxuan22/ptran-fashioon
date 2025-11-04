package com.example.projectc1023i1.service.template;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

/**
 * Service để quản lý các email templates
 * Sử dụng Thymeleaf template engine để render HTML templates
 * 
 * Templates được đặt trong: src/main/resources/templates/email/
 */
@Service
public class EmailTemplateService {
    
    private final TemplateEngine templateEngine;
    
    public EmailTemplateService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    /**
     * Tạo template HTML cho email xác thực OTP
     * Template file: templates/email/verification-code.html
     * 
     * @param code Mã OTP 6 số
     * @return HTML content của email
     */
    public String buildVerificationCodeTemplate(String code) {
        Context context = new Context(Locale.forLanguageTag("vi"));
        context.setVariable("code", code);
        
        return templateEngine.process("email/verification-code", context);
    }
    
    /**
     * Tạo template HTML từ template file với các biến tùy chỉnh
     * 
     * @param templateName Tên template (không có extension .html)
     * @param variables Map chứa các biến để inject vào template
     * @return HTML content của email
     */
    public String buildTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context(Locale.forLanguageTag("vi"));
        
        if (variables != null) {
            variables.forEach(context::setVariable);
        }
        
        return templateEngine.process("email/" + templateName, context);
    }
}

