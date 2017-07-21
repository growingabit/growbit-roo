package org.growbit.config.jackson;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDomainModelModule;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.growbit.domain.Spaggiari;
import org.growbit.web.SpaggiariJsonMixin;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = DomainModelModule
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDomainModelModule
@JsonComponent
public class DomainModelModule extends SimpleModule {

    /**
     * TODO Auto-generated constructor documentation
     *
     */
    public DomainModelModule() {
        // Mixin registration
        setMixInAnnotation(Spaggiari.class, SpaggiariJsonMixin.class);
    }
}
