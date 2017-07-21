package org.growbit.repository;
import org.growbit.domain.Spaggiari;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = SpaggiariRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Spaggiari.class)
@Transactional(readOnly = true)
public interface SpaggiariRepository extends DetachableJpaRepository<Spaggiari, Long>, SpaggiariRepositoryCustom {
}
