package es.rabbithol.jemblem.model.skill;

import java.util.Map;

import es.rabbithol.jemblem.calculation.BattleCalculationStep;
import es.rabbithol.jemblem.calculation.BattleCalculationStepLabel;

public interface CalculationSkill extends Skill {
  Map<BattleCalculationStepLabel, BattleCalculationStep> modification();

  @Override
  default Trigger triggers() {
    return Trigger.CALCULATION;
  }
}
