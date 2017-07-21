package org.growbit.web;
import org.growbit.domain.Spaggiari;
import org.growbit.service.api.SpaggiariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = SpaggiariDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Spaggiari.class)
@JsonComponent
public class SpaggiariDeserializer extends JsonObjectDeserializer<Spaggiari> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SpaggiariService spaggiariService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param spaggiariService
     * @param conversionService
     */
    @Autowired
    public SpaggiariDeserializer(@Lazy SpaggiariService spaggiariService, ConversionService conversionService) {
        this.spaggiariService = spaggiariService;
        this.conversionService = conversionService;
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
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Spaggiari
     * @throws IOException
     */
    public Spaggiari deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Spaggiari spaggiari = spaggiariService.findOne(id);
        if (spaggiari == null) {
            throw new NotFoundException("Spaggiari not found");
        }
        return spaggiari;
    }
}
