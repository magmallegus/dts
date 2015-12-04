package it.dl.engine.app

import it.dl.engine.discount.Coverage
import it.dl.engine.discount.DiscountOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/discounts")
class DiscountDetailController {

  @Autowired
  ConfigurableApplicationContext context

  @RequestMapping(value = "/serviceEnabled", method = RequestMethod.GET)
  def @ResponseBody serviceEnabled() {
    true
  }

  @RequestMapping(value = "/save", method=RequestMethod.POST)
  String addToQueue(@RequestBody DiscountOutput discountOutput) {
    JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class)
    println("Sending a new message.");

    println("discountOutput =====> $discountOutput")

    def notNullCoverages = discountOutput.discounts.map.values().findAll {
      it != null
    }.toSet()

    println("notNullCoverages ======> $notNullCoverages")

    for(Coverage coverage: notNullCoverages) {
      DiscountDetail discountDetail = evalDiscountDetail(coverage, discountOutput)
      println("discountDetail ======> $discountDetail")
      jmsTemplate.convertAndSend(discountDetail)
    }
    "jsonTemplate"
  }

  private DiscountDetail evalDiscountDetail(Coverage coverage, DiscountOutput discountOutput) {
    def coverageCode = coverage.class.simpleName.toLowerCase()
    def discounByCoverage = []
    def idMatchedPromotions = []
    discountOutput.matchedPromotions.each {
      idMatchedPromotions << it.id
      discounByCoverage << it.discount.findAll { prop ->
        prop."${coverageCode}"
      }
    }
    def componentiSconto = []
    discounByCoverage.each {
      componentiSconto << it."${coverageCode}"
    }
    def discountDetail = new DiscountDetail(
      quoteNumber: Long.valueOf(discountOutput.token.replaceAll(/(.)*-/, "")),
      coverageCod: coverageCode.toUpperCase(),
      discountedPercentage: discountOutput.discounts."${coverageCode}".value,
      coverages: componentiSconto.flatten().join(","),
      matchedPromotions: idMatchedPromotions.join(","),
      createdAt: new Date()
    )
    discountDetail
  }
}