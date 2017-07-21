package org.growbit.web;
import org.growbit.domain.Spaggiari;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import java.util.Arrays;
import java.util.Locale;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.growbit.Topic;
import org.growbit.TopicType;
import org.growbit.service.api.SpaggiariService;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

/**
 * = SpaggiarisItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Spaggiari.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/spaggiaris/{spaggiari}", name = "SpaggiarisItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class SpaggiarisItemThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SpaggiariService spaggiariService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<SpaggiarisItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param spaggiariService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public SpaggiarisItemThymeleafController(SpaggiariService spaggiariService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setSpaggiariService(spaggiariService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(SpaggiarisItemThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SpaggiariService
     */
    public SpaggiariService getSpaggiariService() {
        return spaggiariService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiariService
     */
    public void setSpaggiariService(SpaggiariService spaggiariService) {
        this.spaggiariService = spaggiariService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<SpaggiarisItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<SpaggiarisItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Spaggiari
     */
    @ModelAttribute
    public Spaggiari getSpaggiari(@PathVariable("spaggiari") Long id, Locale locale, HttpMethod method) {
        Spaggiari spaggiari = null;
        if (HttpMethod.PUT.equals(method)) {
            spaggiari = spaggiariService.findOneForUpdate(id);
        } else {
            spaggiari = spaggiariService.findOne(id);
        }
        if (spaggiari == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Spaggiari", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return spaggiari;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Spaggiari spaggiari, Model model) {
        model.addAttribute("spaggiari", spaggiari);
        return new ModelAndView("spaggiaris/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Spaggiari spaggiari, Model model) {
        model.addAttribute("spaggiari", spaggiari);
        return new ModelAndView("spaggiaris/showInline :: inline-content");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dataBinder
     */
    @InitBinder("spaggiari")
    public void initSpaggiariBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("updated_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
        model.addAttribute("topic", Arrays.asList(Topic.values()));
        model.addAttribute("topic_type", Arrays.asList(TopicType.values()));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Spaggiari spaggiari, Model model) {
        populateForm(model);
        model.addAttribute("spaggiari", spaggiari);
        return new ModelAndView("spaggiaris/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @param version
     * @param concurrencyControl
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Spaggiari spaggiari, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, BindingResult result, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView("spaggiaris/edit");
        }
        // Concurrency control
        Spaggiari existingSpaggiari = getSpaggiariService().findOne(spaggiari.getId());
        if (spaggiari.getVersion() != existingSpaggiari.getVersion() && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            model.addAttribute("spaggiari", spaggiari);
            model.addAttribute("concurrency", true);
            return new ModelAndView("spaggiaris/edit");
        } else if (spaggiari.getVersion() != existingSpaggiari.getVersion() && "discard".equals(concurrencyControl)) {
            populateForm(model);
            model.addAttribute("spaggiari", existingSpaggiari);
            model.addAttribute("concurrency", false);
            return new ModelAndView("spaggiaris/edit");
        } else if (spaggiari.getVersion() != existingSpaggiari.getVersion() && "apply".equals(concurrencyControl)) {
            // Update the version field to be able to override the existing values
            spaggiari.setVersion(existingSpaggiari.getVersion());
        }
        Spaggiari savedSpaggiari = getSpaggiariService().save(spaggiari);
        UriComponents showURI = getItemLink().to(SpaggiarisItemThymeleafLinkFactory.SHOW).with("spaggiari", savedSpaggiari.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Spaggiari spaggiari) {
        getSpaggiariService().delete(spaggiari);
        return ResponseEntity.ok().build();
    }
}
