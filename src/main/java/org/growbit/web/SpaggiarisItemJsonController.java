package org.growbit.web;
import org.growbit.domain.Spaggiari;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.growbit.service.api.SpaggiariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = SpaggiarisItemJsonController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Spaggiari.class, pathPrefix = "/api", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/api/spaggiaris/{spaggiari}", name = "SpaggiarisItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpaggiarisItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SpaggiariService spaggiariService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param spaggiariService
     */
    @Autowired
    public SpaggiarisItemJsonController(SpaggiariService spaggiariService) {
        this.spaggiariService = spaggiariService;
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
     * @param id
     * @return Spaggiari
     */
    @ModelAttribute
    public Spaggiari getSpaggiari(@PathVariable("spaggiari") Long id) {
        Spaggiari spaggiari = spaggiariService.findOne(id);
        if (spaggiari == null) {
            throw new NotFoundException(String.format("Spaggiari with identifier '%s' not found", id));
        }
        return spaggiari;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Spaggiari spaggiari) {
        return ResponseEntity.ok(spaggiari);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @return UriComponents
     */
    public static UriComponents showURI(Spaggiari spaggiari) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(SpaggiarisItemJsonController.class).show(spaggiari)).buildAndExpand(spaggiari.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedSpaggiari
     * @param spaggiari
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Spaggiari storedSpaggiari, @Valid @RequestBody Spaggiari spaggiari, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        spaggiari.setId(storedSpaggiari.getId());
        getSpaggiariService().save(spaggiari);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Spaggiari spaggiari) {
        getSpaggiariService().delete(spaggiari);
        return ResponseEntity.ok().build();
    }
}
