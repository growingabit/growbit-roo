package org.growbit.web;
import org.growbit.domain.Spaggiari;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.growbit.service.api.SpaggiariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = SpaggiarisCollectionJsonController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Spaggiari.class, pathPrefix = "/api", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/api/spaggiaris", name = "SpaggiarisCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpaggiarisCollectionJsonController {

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
    public SpaggiarisCollectionJsonController(SpaggiariService spaggiariService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<Spaggiari>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<Spaggiari> spaggiaris = getSpaggiariService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(spaggiaris);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(SpaggiarisCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody Spaggiari spaggiari, BindingResult result) {
        if (spaggiari.getId() != null || spaggiari.getVersion() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        Spaggiari newSpaggiari = getSpaggiariService().save(spaggiari);
        UriComponents showURI = SpaggiarisItemJsonController.showURI(newSpaggiari);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiaris
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<Spaggiari> spaggiaris, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getSpaggiariService().save(spaggiaris);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiaris
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<Spaggiari> spaggiaris, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getSpaggiariService().save(spaggiaris);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getSpaggiariService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
