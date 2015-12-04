package it.dl.engine.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DiscountDetailReceiver {
  @Autowired
  public DiscountDetailRepository discountDetailRepository

  void receiveMessage(DiscountDetail message) {
    println("Received <$message>")

    save(message)

//    find(message)
  }

  void save(DiscountDetail message) {
    discountDetailRepository.save(message)
  }
  void find(DiscountDetail message) {
    discountDetailRepository.findByQuoteNumber(716113061).each {
      println("From database found =====>$it")
    }
  }
}