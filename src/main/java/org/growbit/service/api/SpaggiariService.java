package org.growbit.service.api;
import org.growbit.domain.Spaggiari;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = SpaggiariService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Spaggiari.class)
public interface SpaggiariService extends EntityResolver<Spaggiari, Long> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Spaggiari
     */
    public abstract Spaggiari findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     */
    public abstract void delete(Spaggiari spaggiari);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Spaggiari> save(Iterable<Spaggiari> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Spaggiari
     */
    public abstract Spaggiari save(Spaggiari entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Spaggiari
     */
    public abstract Spaggiari findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Spaggiari> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Spaggiari> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Spaggiari> findAll(GlobalSearch globalSearch, Pageable pageable);
}
