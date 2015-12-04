package it.dl.engine.app

import groovy.transform.ToString

import javax.persistence.*

@ToString
@Entity
@Table(name = "PTF_DETTAGLIO_SCONTI")
class DiscountDetail implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id
  @Column(name = "ID_N_ENTITA_RIFERIMENTO")
  Long idRef
  @Column(name = "NUM_N_PREVENTIVO", nullable = false)
  Long quoteNumber
  @Column(name = "COD_X_GARANZIA")
  String coverageCod
  @Column(name = "CUR_N_SCONTO_GARANZIA", nullable = false)
  BigDecimal discountedPercentage
  @Column(name = "COMPONENTI_SCONTO", nullable = false)
  String coverages
  @Column(name = "PROMO_APPLICATE", nullable = false)
  String matchedPromotions
  @Column(name = "SYS_D_INSERIMENTO", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  Date createdAt;
}