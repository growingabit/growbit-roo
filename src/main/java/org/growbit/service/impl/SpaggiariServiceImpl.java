package org.growbit.service.impl;
import org.growbit.service.api.SpaggiariService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.growbit.domain.Spaggiari;
import org.growbit.repository.SpaggiariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = SpaggiariServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = SpaggiariService.class)
@Service
@Transactional(readOnly = true)
public class SpaggiariServiceImpl implements SpaggiariService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SpaggiariRepository spaggiariRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param spaggiariRepository
     */
    @Autowired
    public SpaggiariServiceImpl(SpaggiariRepository spaggiariRepository) {
        setSpaggiariRepository(spaggiariRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SpaggiariRepository
     */
    public SpaggiariRepository getSpaggiariRepository() {
        return spaggiariRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiariRepository
     */
    public void setSpaggiariRepository(SpaggiariRepository spaggiariRepository) {
        this.spaggiariRepository = spaggiariRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param spaggiari
     */
    @Transactional
    public void delete(Spaggiari spaggiari) {
        getSpaggiariRepository().delete(spaggiari);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Spaggiari> save(Iterable<Spaggiari> entities) {
        return getSpaggiariRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Spaggiari> toDelete = getSpaggiariRepository().findAll(ids);
        getSpaggiariRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Spaggiari
     */
    @Transactional
    public Spaggiari save(Spaggiari entity) {
        return getSpaggiariRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Spaggiari
     */
    public Spaggiari findOne(Long id) {
        return getSpaggiariRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Spaggiari
     */
    public Spaggiari findOneForUpdate(Long id) {
        return getSpaggiariRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Spaggiari> findAll(Iterable<Long> ids) {
        return getSpaggiariRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Spaggiari> findAll() {
        return getSpaggiariRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getSpaggiariRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Spaggiari> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getSpaggiariRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Spaggiari> getEntityType() {
        return Spaggiari.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
