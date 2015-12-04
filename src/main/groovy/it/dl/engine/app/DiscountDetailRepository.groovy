package it.dl.engine.app

import org.springframework.stereotype.Repository

@Repository
interface DiscountDetailRepository {
  List<DiscountDetail> findByQuoteNumber(Long quoteNumber)
  void save(DiscountDetail discountDetail)
}