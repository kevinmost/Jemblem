package es.rabbithol.jemblem.calculation;

public interface BattleCalculationStep {
  /**
   * @return true if no further processing should be done in this step
   */
  boolean calculate(BattleCalculatorInfo me, BattleCalculatorInfo them);
}
