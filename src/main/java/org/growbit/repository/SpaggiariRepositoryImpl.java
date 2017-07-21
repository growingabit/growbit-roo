package org.growbit.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import org.growbit.domain.Spaggiari;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import org.growbit.domain.QSpaggiari;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = SpaggiariRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = SpaggiariRepositoryCustom.class)
@Transactional(readOnly = true)
public class SpaggiariRepositoryImpl extends QueryDslRepositorySupportExt<Spaggiari> implements SpaggiariRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    SpaggiariRepositoryImpl() {
        super(Spaggiari.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UPDATED = "updated";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String GRADE = "grade";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String TOPIC = "topic";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String TOPIC___TYPE = "topic_type";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Spaggiari> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QSpaggiari spaggiari = QSpaggiari.spaggiari;
        JPQLQuery<Spaggiari> query = from(spaggiari);
        Path<?>[] paths = new Path<?>[] { spaggiari.updated, spaggiari.grade, spaggiari.topic, spaggiari.topic_type };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(UPDATED, spaggiari.updated).map(GRADE, spaggiari.grade).map(TOPIC, spaggiari.topic).map(TOPIC___TYPE, spaggiari.topic_type);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, spaggiari);
    }
}
