package it.dl.engine.app

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * Created by fallicr on 03/12/2015.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
class DiscountDetailRepositoryImpl implements DiscountDetailRepository{

 @PersistenceContext
 EntityManager entityManager

  @Override
  List<DiscountDetail> findByQuoteNumber(Long quoteNumber) {
    entityManager.find(DiscountDetail.class, quoteNumber)
  }

  @Override
  void save(DiscountDetail discountDetail) {
    println("Saving ======> $discountDetail")
    try {
      entityManager.persist(discountDetail)
    } catch (Exception e) {
      println("Error Catched ======> $e")
    }
  }
}
